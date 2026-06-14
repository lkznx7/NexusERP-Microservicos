# Nexus CRM Microservice

Este é o microsserviço de CRM (Customer Relationship Management) do ecossistema NexusERP.

## Tecnologias
- Java 21
- Spring Boot 3.3.0
- PostgreSQL
- RabbitMQ
- Spring Data JPA
- Docker

## Como executar

### Pré-requisitos
- Docker e Docker Compose
- Java 21
- Maven 3.9+

### Via Docker Compose
```bash
docker-compose up -d
```

### Via Maven
```bash
mvn spring-boot:run
```

## Estrutura do Projeto
O projeto segue uma arquitetura modularizada:
- `application`: Camada de aplicação e casos de uso.
- `domain`: Modelos de domínio e regras de negócio.
- `infrastructure`: Configurações de banco de dados, mensageria e adapters.
- `presentation`: Controladores REST e DTOs.
- `shared`: Códigos compartilhados entre camadas.
- `feature`: Funcionalidades específicas como Leads e Opportunities.

## Documentação API
A documentação Swagger pode ser acessada em: `http://localhost:8081/swagger-ui.html` quando o serviço estiver em execução.
