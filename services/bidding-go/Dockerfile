FROM golang:latest
WORKDIR /app

# Copy the local files we just fixed
COPY go.mod go.sum ./
RUN go mod download

COPY . .

# Build the binary
RUN go build -o main .

EXPOSE 8080
CMD ["./main"]