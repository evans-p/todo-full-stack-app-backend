# TODO Full Stack App Backend

## TODOS

1. Add API call cashing with Redis (https://medium.com/simform-engineering/spring-boot-caching-with-redis-1a36f719309f)
2. Elastic + Kibana
3. Integrate gmail SMTP server (Reports on what the user did per week).
4. Send an email every Monday for the the tasks ahead
5. Add Authorization?
6. Add image attachment functionality to the Todo
7. More tests with Spok

## Class Diagram

```mermaid
classDiagram
    class TodoList {
        -Long: userId
        -String: todoListId
        -String: title
        -LocalDateTime: created
        -LocalDateTime: lastModified
        -Set~Todo~: todos
    }
    class Todo {
        -Long: todoId
        -Long: todoListId
        -String: userId
        -String: title
        -String: body
        -LocalDateTime: created
        -LocalDateTime: lastModified
        -Boolean: favourite
    }
    TodoList --> Todo: One-to-Many
```

## Authentication Flow

```mermaid
sequenceDiagram
    participant User Agent
    participant Client Application
    participant Authorization Server
    participant Resource Server
    User Agent ->> Client Application: Requests Resource
    Client Application ->> Authorization Server: Auth Token Request
    Authorization Server ->> User Agent: Login Page
    User Agent ->> Authorization Server: User Login
    Authorization Server -->> Client Application: Access Token
    Client Application ->> Resource Server: API Call With Access Token
    Resource Server -->> Authorization Server: Validate Token
    Resource Server ->> Client Application: API Response With Resource, If Token Is Valid
```
