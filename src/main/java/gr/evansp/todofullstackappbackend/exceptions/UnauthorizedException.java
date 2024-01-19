package gr.evansp.todofullstackappbackend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UnauthorizedException extends RuntimeException {

  public static final String DIFFERENT_OWNER = "different.owner";

  public static final String UNAUTHORIZED = "unauthorized";

  private final String message;
  private final transient Object[] args;
}
