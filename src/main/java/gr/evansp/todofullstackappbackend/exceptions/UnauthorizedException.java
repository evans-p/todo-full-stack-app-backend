package gr.evansp.todofullstackappbackend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** {@link RuntimeException} to be thrown on Unauthorized actions. */
@Getter
@AllArgsConstructor
public class UnauthorizedException extends RuntimeException {

  public static final String UNAUTHORIZED = "unauthorized";

  private final String message;
  private final transient Object[] args;
}
