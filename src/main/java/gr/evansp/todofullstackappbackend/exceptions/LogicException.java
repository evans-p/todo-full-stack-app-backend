package gr.evansp.todofullstackappbackend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** Exception to be thrown when a logic error arises. */
@Getter
@AllArgsConstructor
public class LogicException extends RuntimeException {

  public static final String DOES_NOT_EXIST = "does.not.exist";

  public static final String ALREADY_EXISTS = "already.exists";

  private final String message;
  private final transient Object[] args;
}
