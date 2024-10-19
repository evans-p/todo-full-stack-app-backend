package gr.evansp.todofullstackappbackend.services.todos;

import gr.evansp.todofullstackappbackend.models.todos.Todo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/** Service for CRUD operations regarding Todo1. */
public interface TodoService {

  /**
   * A method to find all Todos of the authenticated user.
   *
   * @return List Todos
   */
  List<Todo> getAll();

  /**
   * A method to find a Todo1 by its ID (primary key).
   *
   * @param id TodoList ID
   * @return TodoList
   */
  Todo find(@NotNull(message = "{id.null}") Long id);

  /**
   * Stores a new Todo1 to the DB.
   *
   * @param todo todo1 to store.
   * @return newly stored todoList.
   */
  Todo store(@NotNull(message = "{todo.null}") @Valid Todo todo);

  /**
   * Updates an existing todo1.
   *
   * @param todo todo1 to store.
   * @return newly stored todoList.
   */
  Todo update(@NotNull(message = "{todo.null}") @Valid Todo todo);

  /**
   * Deletes an existing Todo1.
   *
   * @param id todo1 to delete.
   */
  void delete(@NotNull(message = "{id.null}") Long id);
}
