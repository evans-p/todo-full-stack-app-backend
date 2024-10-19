package gr.evansp.todofullstackappbackend.base;

import java.util.Locale;
import java.util.ResourceBundle;

/** Base class for tests. */
@SuppressWarnings({"nls", "unused"})
public abstract class BaseTest {
  /** Validation Messages. */
  public static final ResourceBundle ERROR_MESSAGES =
      ResourceBundle.getBundle("messages", Locale.getDefault());

  /** Error Messages. */
  public static ResourceBundle VALIDATION_MESSAGES =
      ResourceBundle.getBundle("ValidationMessages", Locale.getDefault());
}
