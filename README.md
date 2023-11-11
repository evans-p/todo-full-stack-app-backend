# TODO Full Stack App Backend


## Class Diagram

```mermaid
classDiagram
    class User {
        -Long userId
        -String email
        -String password
        -LocalDate created
        -Set~Todo~ favoriteTodos
        -Set~TodoList~ todoLists
    }
    class TodoList {
        -Long userId
        -Long todoListId
        -String title
        -LocalDate created
        -LocalDate lastModified
        -Set~Todo~ todos
    }
    class Todo {
        -Long userId
        -Long todoListId
        -Long todoListId
        -String title
        -String body
        -LocalDate created
        -LocalDate lastModified
    }
    User --> TodoList
    User --> Todo
    TodoList --> Todo
```

