# TODO Full Stack App Backend

## TODOS

1. Add Maven Profiling for integration tests.
2. Complete App Internationalization.
3. Internationalize Exceptions.
4. Add Custom Exceptions.
5. Add Global REST Exception Handler.
6. Check for potentially problematic behaviour when you call update or delete, and TodoList ID is null.

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

