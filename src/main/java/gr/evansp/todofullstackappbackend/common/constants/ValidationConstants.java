package gr.evansp.todofullstackappbackend.common.constants;

public class ValidationConstants {
  /**
   * User Id null
   */
  public static final String USER_ID_NULL = "User id should not be null.";

  /**
   * Created date null.
   */
  public static final String USER_ID_GREATER_THAN_ZERO = "User Id must be positive.";

  /**
   * Email empty.
   */
  public static final String EMAIL_EMPTY = "Email should not be empty.";

  /**
   * Email invalid.
   */
  public static final String EMAIL_VALID = "Email should be valid.";

  /**
   * Password null or empty.
   */
  public static final String PASSWORD_NULL = "Password should not be empty.";

  /**
   * Password too short.
   */
  public static final String PASSWORD_SHORT = "Password is too short.";

  /**
   * Created date null.
   */
  public static final String CREATE_DATE_NULL = "Created date should not be null.";

  private ValidationConstants() {
    //Private No args constructor.
  }
}
