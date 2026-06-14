# Products Microservice

Products Microservice for NexusERP.

## Technologies
- Java 21
- Spring Boot 3.3.0
- Spring Data JPA
- PostgreSQL
- RabbitMQ
- Flyway
- SpringDoc OpenAPI

## How to run
1. Navigate to the `PRODUCTS` directory.
2. Run `docker-compose up -d` to start the database and RabbitMQ.
3. Run `./mvnw spring-boot:run` or use your IDE.

## API Documentation
Once the application is running, you can access the Swagger UI at:
`http://localhost:8090/swagger-ui.html`
