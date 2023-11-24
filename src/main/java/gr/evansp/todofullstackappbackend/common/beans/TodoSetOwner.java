package gr.evansp.todofullstackappbackend.common.beans;

import gr.evansp.todofullstackappbackend.todo.models.def.Todo;

import java.util.Set;

/**
 * Something with a {@link Set} for todos.
 */
@SuppressWarnings("unused")
public interface TodoSetOwner {
  /**
   * Getter of todos.
   *
   * @return todos.
   */
  Set<Todo> getTodos();

  /**
   * Setter of todos.
   *
   * @param todos todos.
   */
  void setTodos(Set<Todo> todos);
}
