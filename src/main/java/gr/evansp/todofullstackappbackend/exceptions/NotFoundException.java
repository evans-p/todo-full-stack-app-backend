package gr.evansp.todofullstackappbackend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exception to be thrown when an entity is not found.
 */
@Getter
@AllArgsConstructor
public class NotFoundException extends RuntimeException {
  /**
   * List not found.
   */
  public static final String LIST_NOT_FOUND = "list.not.found";

  /**
   * List not found.
   */
  public static final String TODO_NOT_FOUND = "todo.not.found";

  private final String message;
  private final transient Object[] args;
}
