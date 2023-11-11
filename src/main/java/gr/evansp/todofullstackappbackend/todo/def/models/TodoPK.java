package gr.evansp.todofullstackappbackend.todo.def.models;

/**
 * Primary key of a todo1.
 */
@SuppressWarnings("unused")
public interface TodoPK extends TodoListPK {
  /**
   * getter for todoId
   *
   * @return todoId
   */
  Long getTodoId();

  /**
   * setter for todoId
   *
   * @param todoId todoId
   */
  void setTodoId(Long todoId);
}
