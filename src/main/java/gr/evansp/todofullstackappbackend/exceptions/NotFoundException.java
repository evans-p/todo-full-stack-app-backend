package gr.evansp.todofullstackappbackend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exception to be thrown when an entity is not found.
 */
@Getter
@AllArgsConstructor
public class NotFoundException extends RuntimeException {
  private final String message;
  private final transient Object[] args;
}
