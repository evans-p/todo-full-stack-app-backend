package gr.evansp.todofullstackappbackend.samples;

import gr.evansp.todofullstackappbackend.models.todos.Todo;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.models.users.User;
import gr.evansp.todofullstackappbackend.utils.NumberUtils;
import gr.evansp.todofullstackappbackend.utils.StringUtils;

/**
 * Sample creation
 */
public class Samples {

  private Samples() {
    //Private NoArgConstructor
  }

  /**
   * Create Sample {@link User}.
   *
   * @return User.
   */
  public static User createSampleUser() {
    User user = new User();

    user.setEmail(String.format("example%d@example.com", NumberUtils.generateRandomLong(0L, 1000000L)));
    user.setPassword("12345678");

    return user;
  }

  /**
   * Creates sample {@link TodoList}.
   *
   * @param userId userId
   * @return TodoList
   */
  public static TodoList createSampleTodoList(Long userId) {
    return new TodoList(userId, StringUtils.generateRandomString(10));
  }

  /**
   * Creates sample Todo1.
   *
   * @param listId listId
   * @return Todo1.
   */
  public static Todo createSampleTodo(Long listId) {
    return new Todo(listId, StringUtils.generateRandomString(10),
        StringUtils.generateRandomString(200));
  }
}
