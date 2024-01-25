package gr.evansp.todofullstackappbackend.services.todos;

import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Service for CRUD operations regarding {@link TodoList}.
 */
public interface TodoListService {

  /**
   * Returns all {@link TodoList} of the authenticated User.
   *
   * @return List<TodoList>
   */
  List<TodoList> getAll();

  /**
   * A method to find a {@link TodoList} by its ID (primary key).
   *
   * @param id TodoList ID
   * @return TodoList
   */
  TodoList find(@NotNull(message = "{id.null}") Long id);

  /**
   * Stores a new {@link TodoList} to the DB.
   *
   * @param todoList todoList to store.
   * @return newly stored todoList.
   */
  TodoList store(@NotNull(message = "{todo.list.null}") @Valid TodoList todoList);

  /**
   * Updates an existing {@link TodoList}.
   *
   * @param todoList todoList
   * @return Updated TodoList
   */
  TodoList update(@NotNull(message = "{todo.list.null}") @Valid TodoList todoList);

  /**
   * Deletes an existing {@link TodoList}.
   *
   * @param todoList todoList to delete.
   */
  void delete(@NotNull(message = "{todo.list.null}") @Valid TodoList todoList);
}
