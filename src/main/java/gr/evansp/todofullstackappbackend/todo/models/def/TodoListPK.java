package gr.evansp.todofullstackappbackend.todo.models.def;

import gr.evansp.todofullstackappbackend.user.models.def.UserPK;

import java.io.Serializable;

/**
 * {@link TodoList} Primary Key.
 */
@SuppressWarnings("unused")
public interface TodoListPK extends UserPK, Serializable {
  /**
   * getter for todoListId
   *
   * @return todoListId
   */
  Long getTodoListId();

  /**
   * setter for todoListId
   *
   * @param todoListId todoListId
   */
  void setTodoListId(Long todoListId);
}
