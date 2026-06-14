# Nexus Inventory Service

Microserviço de gerenciamento de inventário do sistema NexusERP.

## Tecnologias

- Java 21
- Spring Boot 3.3.0
- PostgreSQL
- RabbitMQ
- Spring Data JPA
- Flyway (Migrações de banco de dados)
- Springdoc OpenAPI (Swagger)
- Spring Boot Actuator

## Estrutura do Projeto

O projeto segue uma arquitetura orientada a domínios e funcionalidades:

- `application`: Camada de aplicação (Use Cases, DTOs).
- `domain`: Camada de domínio (Entidades, Repositórios, Objetos de Valor).
- `infrastructure`: Configurações e implementações técnicas.
- `presentation`: Controladores REST e tratamento de exceções.
- `shared`: Utilitários e configurações compartilhadas.
- `feature/Products`: Funcionalidades específicas de Produtos.
- `feature/Categories`: Funcionalidades específicas de Categorias.

## Como Executar

### Pré-requisitos

- Docker e Docker Compose

### Passos

1. Navegue até o diretório `INVENTORY`.
2. Execute o comando:
   ```bash
   docker-compose up -d
   ```
3. O serviço estará disponível em `http://localhost:8082`.
4. Documentação Swagger: `http://localhost:8082/swagger-ui.html`.

## Configuração de Desenvolvimento

O serviço utiliza a porta 8082. O banco de dados PostgreSQL está configurado para a porta 5433 no host para evitar conflitos.
