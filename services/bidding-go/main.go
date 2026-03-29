package main

import (
	"context"
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"sync"
	"time"

	pb "bidding-go/proto"
	"github.com/gorilla/websocket"
	"github.com/redis/go-redis/v9"
	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
)

type SocketMessage struct {
	Type         string  `json:"type"`
	Username     string  `json:"username,omitempty"`
	Password     string  `json:"password,omitempty"`
	ProductID    string  `json:"productID,omitempty"`
	Amount       float64 `json:"amount,omitempty"`
	CurrentPrice float64 `json:"currentPrice,omitempty"`
	Balance      float64 `json:"balance,omitempty"`
	Message      string  `json:"message,omitempty"`
	Success      bool    `json:"success,omitempty"`
}

type Hub struct {
	clients    map[*websocket.Conn]bool
	broadcast  chan []byte
	register   chan *websocket.Conn
	unregister chan *websocket.Conn
	mu         sync.Mutex
}

func (h *Hub) run() {
	for {
		select {
		case conn := <-h.register:
			h.mu.Lock()
			h.clients[conn] = true
			h.mu.Unlock()
		case conn := <-h.unregister:
			h.mu.Lock()
			delete(h.clients, conn)
			h.mu.Unlock()
		case message := <-h.broadcast:
			h.mu.Lock()
			for client := range h.clients {
				if err := client.WriteMessage(websocket.TextMessage, message); err != nil {
					client.Close()
					delete(h.clients, client)
				}
			}
			h.mu.Unlock()
		}
	}
}

var (
	ctx      = context.Background()
	upgrader = websocket.Upgrader{CheckOrigin: func(r *http.Request) bool { return true }}
	hub      = &Hub{
		clients:    make(map[*websocket.Conn]bool),
		broadcast:  make(chan []byte),
		register:   make(chan *websocket.Conn),
		unregister: make(chan *websocket.Conn),
	}
)

func getGrpcClient() (pb.IdentityServiceClient, *grpc.ClientConn, error) {
	conn, err := grpc.Dial("identity-service:9090", grpc.WithTransportCredentials(insecure.NewCredentials()))
	return pb.NewIdentityServiceClient(conn), conn, err
}

func main() {
	go hub.run()
	rdb := redis.NewClient(&redis.Options{Addr: "auction-redis:6379"})

	products := []string{"S-701", "S-402", "S-109", "S-882", "S-551"}
	initials := map[string]float64{"S-701": 1200, "S-402": 5000, "S-109": 8500, "S-882": 2100, "S-551": 3400}
	for id, price := range initials {
		rdb.SetNX(ctx, "price:"+id, price, 0)
	}

	go func() {
		ticker := time.NewTicker(3 * time.Second)
		for range ticker.C {
			prices := make(map[string]float64)
			leaders := make(map[string]string)
			for _, id := range products {
				p, _ := rdb.Get(ctx, "price:"+id).Result()
				l, _ := rdb.Get(ctx, "leader:"+id).Result()
				var val float64
				fmt.Sscanf(p, "%f", &val)
				prices[id] = val
				leaders[id] = l
			}
			msg, _ := json.Marshal(map[string]interface{}{"type": "SYNC_STATE", "prices": prices, "leaders": leaders})
			hub.broadcast <- msg
		}
	}()

	http.HandleFunc("/ws", func(w http.ResponseWriter, r *http.Request) {
		conn, err := upgrader.Upgrade(w, r, nil)
		if err != nil { return }
		hub.register <- conn
		defer func() { hub.unregister <- conn; conn.Close() }()

		for {
			_, msg, err := conn.ReadMessage()
			if err != nil { break }
			var in SocketMessage
			json.Unmarshal(msg, &in)
			client, grpcConn, err := getGrpcClient()
			if err != nil { continue }

			switch in.Type {
			case "SIGNUP":
				res, err := client.Signup(ctx, &pb.SignupRequest{Username: in.Username, Password: in.Password, InitialBalance: 1000.0})
				if err == nil {
					out, _ := json.Marshal(SocketMessage{Type: "AUTH_RES", Success: true, Balance: res.Balance, Username: res.Username, Message: "Created"})
					conn.WriteMessage(websocket.TextMessage, out)
				}
			case "LOGIN":
				res, err := client.Login(ctx, &pb.LoginRequest{Username: in.Username, Password: in.Password})
				if err == nil {
					out, _ := json.Marshal(SocketMessage{Type: "AUTH_RES", Success: true, Balance: res.Balance, Username: res.Username, Message: "Logged In"})
					conn.WriteMessage(websocket.TextMessage, out)
				}
			case "ADD_FUNDS":
				res, err := client.UpdateBalance(ctx, &pb.UpdateBalanceRequest{Username: in.Username, Amount: in.Amount})
				if err == nil {
					out, _ := json.Marshal(SocketMessage{Type: "BALANCE_UPDATE", Username: in.Username, Balance: res.Balance, Message: fmt.Sprintf("Added $%.2f", in.Amount)})
					conn.WriteMessage(websocket.TextMessage, out)
				}
			case "BID":
				currP, _ := rdb.Get(ctx, "price:"+in.ProductID).Result()
				lastL, _ := rdb.Get(ctx, "leader:"+in.ProductID).Result()
				var price float64
				fmt.Sscanf(currP, "%f", &price)

				// 1. Money First: Attempt to deduct bid from wallet
				res, err := client.UpdateBalance(ctx, &pb.UpdateBalanceRequest{Username: in.Username, Amount: -in.Amount})
				if err != nil {
					out, _ := json.Marshal(SocketMessage{Type: "ERROR", Message: "Java Service: Insufficient Funds!"})
					conn.WriteMessage(websocket.TextMessage, out)
					grpcConn.Close()
					continue
				}

				// 2. Deduction Success: Refund the previous leader
				if lastL != "" && lastL != in.Username {
					ref, _ := client.UpdateBalance(ctx, &pb.UpdateBalanceRequest{Username: lastL, Amount: price})
					refundMsg, _ := json.Marshal(SocketMessage{Type: "BALANCE_UPDATE", Username: lastL, Balance: ref.Balance, Message: "Outbid Refund!"})
					hub.broadcast <- refundMsg
				}

				// 3. Update Redis
				rdb.Set(ctx, "price:"+in.ProductID, in.Amount, 0)
				rdb.Set(ctx, "leader:"+in.ProductID, in.Username, 0)

				// 4. Update UI
				selfMsg, _ := json.Marshal(SocketMessage{Type: "BALANCE_UPDATE", Username: in.Username, Balance: res.Balance})
				conn.WriteMessage(websocket.TextMessage, selfMsg)

				allMsg, _ := json.Marshal(SocketMessage{Type: "NEW_BID", ProductID: in.ProductID, CurrentPrice: in.Amount, Username: in.Username, Message: in.Username + " bid $" + fmt.Sprintf("%.0f", in.Amount)})
				hub.broadcast <- allMsg
			}
			grpcConn.Close()
		}
	})
	log.Fatal(http.ListenAndServe(":8080", nil))
}