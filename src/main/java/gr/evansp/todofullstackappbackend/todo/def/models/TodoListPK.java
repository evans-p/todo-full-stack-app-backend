package gr.evansp.todofullstackappbackend.todo.def.models;

import gr.evansp.todofullstackappbackend.user.def.models.UserPK;

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
