package gr.evansp.todofullstackappbackend.samples;

import gr.evansp.todofullstackappbackend.models.todos.Todo;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.utils.StringUtils;

/**
 * Sample creation
 */
public class Samples {

  /**
   * Private NoArgConstructor
   */
  private Samples() {
    //EMPTY
  }

  /**
   * Creates sample {@link TodoList}.
   *
   * @param userId userId
   * @return TodoList
   */
  public static TodoList createSampleTodoList(String userId) {
    return new TodoList(userId, StringUtils.generateRandomString(10));
  }

  /**
   * Creates sample Todo1.
   *
   * @param listId listId
   * @return Todo1.
   */
  public static Todo createSampleTodo(Long listId, String userId) {
    return new Todo(listId, StringUtils.generateRandomString(10),
        userId);
  }
}
