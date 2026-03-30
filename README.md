# 🛰️ RouteMaster

**High-Frequency Distributed Bidding Terminal**

RouteMaster is a high-performance, polyglot microservices platform designed for real-time auctioning and bidding. It utilizes a distributed architecture to separate concerns between real-time state management (**Go**), persistent identity/wallet services (**Java**), and a low-latency reactive interface (**React**).

---

## 🏗️ System Architecture

The project is built on a Microservices Mesh connected via gRPC and WebSockets:

```mermaid
graph TD
    subgraph Client_Side [Frontend - Vite/React]
        UI[App.jsx Terminal UI]
        WS_Client[WebSocket Client]
    end

    subgraph Docker_Network [Internal Docker Network]
        
        subgraph Bidding_Service [Go Bidding Engine - Port 8080]
            Go_Server[main.go - Gorilla/WS]
            Hub[WebSocket Hub]
            gRPC_Client[gRPC Client]
        end

        subgraph Identity_Service [Java Identity Service - Port 9090]
            Spring_Boot[Spring Boot / Hibernate]
            gRPC_Server[gRPC Server]
        end

        subgraph Storage_Layer [Persistence & Cache]
            Redis[(Auction-Redis: 6379)]
            Postgres[(Auction-DB: 5432)]
        end
    end

    %% Interactions
    UI <-->|JSON over WS| Hub
    Hub <-->|Get/Set Prices| Redis
    gRPC_Client <-->|Protobuf| gRPC_Server
    Spring_Boot <-->|JDBC/JPA| Postgres
    
    %% Styles
    style UI fill:#1e293b,stroke:#3b82f6,color:#fff
    style Go_Server fill:#00add8,stroke:#000,color:#fff
    style Spring_Boot fill:#6db33f,stroke:#000,color:#fff
    style Redis fill:#dc382d,stroke:#000,color:#fff
    style Postgres fill:#336791,stroke:#000,color:#fff