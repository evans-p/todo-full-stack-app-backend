# TODO Full Stack App Backend

## TODOS

1. Add Maven Profiling for integration tests.
2. Complete App Internationalization.
3. Check for potentially problematic behaviour when you call update or delete, and TodoList ID is null.
4. Integrate Log4j.
5. Intgrate gmail SMTP server.
6. Add OAUTH2 Login/registration.

## Class Diagram

```mermaid
classDiagram
    class User {
        -Long: userId
        -String: email
        -String: password
        -LocalDateTime: created
        -Set~Todo~: favoriteTodos
        -Set~TodoList~: todoLists
    }
    class TodoList {
        -Long: userId
        -Long: todoListId
        -String: title
        -LocalDateTime: created
        -LocalDateTime: lastModified
        -Set~Todo~: todos
    }
    class Todo {
        -Long: todoId
        -Long: todoListId
        -String: title
        -String: body
        -LocalDateTime: created
        -LocalDateTime: lastModified
        -Boolean: favourite
    }
    User --> TodoList
    TodoList --> Todo
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