# 📚 API de Gerenciamento de Catálogo e Aluguel de Livros

## 📖 Descrição
Essa API RESTful foi desenvolvida utilizando **Spring Boot** para o gerenciamento de um catálogo de livros, permitindo o cadastro, edição, remoção, busca e aluguel de livros. O projeto segue os princípios **DDD, Clean Architecture e SOLID**.

Principais funcionalidades:
- **Versionamento de API** (V1)
- **Gerenciamento de Usuários e Livros**
- **Mensageria com RabbitMQ**
- **Envio de Email na Confirmação do Aluguel de um livro**
- **Testes Unitários e de Integração**
- **Documentação com Swagger**
- **Containerização com Docker**

---

## 🚀 Tecnologias Utilizadas
- **Java 21**
- **Spring Boot 3.4.2**
- **Spring Data JPA + H2 Database**
- **RabbitMQ** (Mensageria)
- **Spring Mail (SMTP)**
- **Swagger (Springdoc OpenAPI)**
- **JUnit & Mockito**
- **Maven**
- **Docker**

---

## 📂 Estrutura do Projeto

```
├── src
│   ├── main
│   │   ├── java/com/example/livraria
│   │   │   ├── config Email & Swagger)
│   │   │   ├── controller (Endpoints da API)
│   │   │   ├── dto (Data Transfer Objects)
│   │   │   ├── exception (Tratamento de exceptions)
│   │   │   ├── model (Entidades JPA)
│   │   │   ├── repository (Camada de acesso a dados)
│   │   │   ├── service (Regras de negócio)
│   │   ├── resources
│   │   │   ├── application.properties (Configuração do Spring Boot)
│   ├── test (Testes unitários e de integração)
```

---

## 🛠️ Requisitos para Execução
Antes de rodar a aplicação, certifique-se de ter instalado:
- **JDK 21**
- **Maven**
- **Docker e Docker Compose** (Opcional)
- **RabbitMQ** (Local ou via Docker)

---

## 🚀 Como Rodar a Aplicação

### 🐳 1. Subindo via Docker Compose
Para rodar toda a infraestrutura necessária, incluindo RabbitMQ e a aplicação:
```sh
docker-compose up --build
```

### 🏗️ 2. Rodando Localmente (Sem Docker)
Caso queira rodar manualmente:
```sh
# Subir RabbitMQ
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management

# Rodar a aplicação
mvn spring-boot:run
```

---

### 1️⃣ Criar um usuário
```sh
POST /v1/users/create
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "123456"
}
```

## 📌 Exemplos de Endpoints

### 📚 **Gerenciamento de Livros**

✅ **Criar um Livro**
```sh
POST /v1/books
{
  "titulo": "1984",
  "autor": "George Orwell"
}
```

✅ **Listar Livros com Paginação**
```sh
GET /v1/books?page=0&size=5
```

✅ **Buscar Livro por Título e/ou Autor**
```sh
GET /v1/books/search?title=1984&author=Orwell
```

✅ **Alugar um Livro**
```sh
PATCH /v1/books/1/borrow?email=test@test.com
```
✉️ **Email enviado ao usuário na confirmação do aluguel**

✅ **Excluir um Livro**
```sh
DELETE /v1/books/1
```

---

## 📜 **Swagger (Documentação da API)**
A API está documentada com Swagger e pode ser acessada em:
```sh
http://localhost:8080/swagger-ui/index.html
```

---

## 🧪 **Rodando os Testes**
Os testes unitários e de integração foram implementados com **JUnit** e **Mockito**. Para executá-los:
```sh
mvn test
```

---

## 📦 **Dockerfile** (Caso precise rodar a aplicação em container)
```Dockerfile
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/challenge-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

## 🤝 Contribuição
Se quiser contribuir, fique à vontade para abrir um **Pull Request** ou **Issue**! 😃

---


