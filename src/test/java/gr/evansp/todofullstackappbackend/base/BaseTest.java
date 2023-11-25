package gr.evansp.todofullstackappbackend.base;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Base class for tests.
 */
public abstract class BaseTest {
  public static ResourceBundle validationMessages =
      ResourceBundle.getBundle("ValidationMessages", new Locale("el", "EL"));
}
