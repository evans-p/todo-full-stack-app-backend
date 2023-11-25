package gr.evansp.todofullstackappbackend.utils;

import java.util.Random;

/**
 * Utilities regarding {@link String}.
 */
@SuppressWarnings("unused")
public class StringUtils {

  private static final Random rnd = new Random();
  /**
   * Available characters.
   */
  private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".repeat(20);

  private StringUtils() {
    // Private NoArgs Constructor
  }

  /**
   * Generates a random string of specified length. Length must be greater than zero and smaller than 512.
   * (0 < length <= 512).
   *
   * @param length length
   * @return String
   */
  public static String generateRandomString(int length) {
    if (length <= 0) {
      throw new IllegalArgumentException("Length must be positive");
    }
    if (length >= 512) {
      throw new IllegalArgumentException("Length must be less than 512");
    }

    StringBuilder salt = new StringBuilder();

    while (salt.length() < length) { // length of the random string.
      int index = rnd.nextInt(0, length);
      salt.append(CHARACTERS.charAt(index));
    }
    return salt.toString();
  }
}
