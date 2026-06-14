# NexusERP

O NexusERP é um ERP modular desenvolvido para estudo e prática de conceitos avançados de arquitetura de software.

O projeto tem como objetivo explorar:

* Java 21
* Spring Boot
* PostgreSQL
* RabbitMQ
* Clean Architecture
* Domain-Driven Design (DDD)
* Event Driven Architecture (EDA)
* Microsserviços
* Docker
* Git Flow

---

## Objetivo do Projeto

Este projeto está sendo desenvolvido do zero com foco em aprendizado prático.

O objetivo não é criar apenas um ERP funcional, mas compreender profundamente:

* Arquitetura de Microsserviços
* Modelagem de Domínio
* Comunicação Assíncrona
* Mensageria
* Boas Práticas de Desenvolvimento
* Escalabilidade
* Observabilidade
* Segurança

---

## Arquitetura

```text
NexusERP
│
├── AUTH
├── CRM
├── CUSTOMERS
├── FINANCE
├── INVENTORY
├── NOTIFICATIONS
├── PRODUCTS
├── REPORTS
├── SALES
└── SHARED
```

### Responsabilidades

| Módulo        | Objetivo                   |
| ------------- | -------------------------- |
| AUTH          | Autenticação e autorização |
| CRM           | Leads e oportunidades      |
| CUSTOMERS     | Gestão de clientes         |
| FINANCE       | Gestão financeira          |
| INVENTORY     | Controle de estoque        |
| NOTIFICATIONS | Notificações               |
| PRODUCTS      | Catálogo de produtos       |
| REPORTS       | Relatórios                 |
| SALES         | Gestão de vendas           |
| SHARED        | Biblioteca compartilhada   |

---

## Tecnologias

### Backend

* Java 21
* Spring Boot 3
* Spring Security
* Spring Data JPA
* Maven

### Banco de Dados

* PostgreSQL

### Mensageria

* RabbitMQ

### Infraestrutura

* Docker
* Docker Compose

### Arquitetura

* Clean Architecture
* DDD
* Event Driven Architecture
* Microsserviços

---

## Status do Projeto

```text
🚧 Em desenvolvimento
```

Atualmente o projeto encontra-se na fase de fundação arquitetural e estruturação dos microsserviços.

---

## Sobre o Uso de IA

Este projeto utiliza Inteligência Artificial apenas como ferramenta de apoio para:

* geração de código repetitivo
* automação de tarefas
* criação de estruturas iniciais

Toda modelagem de domínio, decisões arquiteturais, regras de negócio e evolução do sistema são conduzidas manualmente como parte do processo de aprendizado.

---

## Autor

Desenvolvido por Lucas (LK).

---

## Licença

Projeto destinado exclusivamente para fins de estudo e aprendizado.
