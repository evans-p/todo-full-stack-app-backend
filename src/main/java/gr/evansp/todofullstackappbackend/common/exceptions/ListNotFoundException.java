package gr.evansp.todofullstackappbackend.common.exceptions;

import gr.evansp.todofullstackappbackend.models.todos.TodoList;

/**
 * Exception to be thrown when no {@link TodoList} is found.
 */
public class ListNotFoundException extends Exception {

  /**
   * Constructor with just a message.
   *
   * @param message message.
   */
  public ListNotFoundException(String message) {
    super(message);
  }
}
