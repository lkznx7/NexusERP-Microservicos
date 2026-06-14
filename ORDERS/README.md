# Nexus-Orders Microservice

Orders microservice for NexusERP.

## Requirements
- Java 21
- Maven 3.9+
- PostgreSQL
- RabbitMQ

## Configuration
The service runs on port `8089`.
Database name: `nexus_orders`.

## Getting Started
To run the service locally:
```bash
./mvnw spring-boot:run
```

Or using Docker:
```bash
docker-compose up -d
```
