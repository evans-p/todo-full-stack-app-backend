package gr.evansp.todofullstackappbackend.utils;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * Utilities regarding {@link String}.
 */
@SuppressWarnings("unused")
public class StringUtils {

  /**
   * Random.
   */
  private static final Random random = new Random();

  private StringUtils() {
    // Private NoArgs Constructor
  }

  /**
   * Generates a random string of specified length.
   *
   * @param length length
   * @return String
   */
  public static String generateRandomString(int length) {
    if (length <= 0) {
      throw new IllegalArgumentException("Length must be positive");
    }
    byte[] bytes = new byte[length];

    random.nextBytes(bytes);
    return new String(bytes, StandardCharsets.UTF_8);
  }
}
