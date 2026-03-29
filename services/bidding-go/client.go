package main

import (
	"context"
	"log"
	"time"

	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
	pb "bidding-go/proto" // Update this if your module name is different
)

// GetUserBalance connects to the Java service and asks for a user's wallet balance
func GetUserBalance(username string) (float64, bool) {
	// 1. Dial the Java Service (Running on localhost:9090)
	conn, err := grpc.Dial("localhost:9090", grpc.WithTransportCredentials(insecure.NewCredentials()))
	if err != nil {
		log.Printf("Failed to connect to Identity Service: %v", err)
		return 0, false
	}
	defer conn.Close()

	client := pb.NewIdentityServiceClient(conn)

	// 2. Set a 2-second timeout so the Go app doesn't hang if Java is down
	ctx, cancel := context.WithTimeout(context.Background(), 2*time.Second)
	defer cancel()

	// 3. Send the request
	resp, err := client.GetBalance(ctx, &pb.BalanceRequest{Username: username})
	if err != nil {
		log.Printf("Error during gRPC call: %v", err)
		return 0, false
	}

	return resp.GetBalance(), resp.GetExists()
}