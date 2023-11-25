package gr.evansp.todofullstackappbackend.utils;

import java.util.Random;

/**
 * Utilities regarding {@link Number}.
 */
public class NumberUtils {

  private static final Random random = new Random();

  private NumberUtils() {
    // Private NoArgs Constructor
  }

  public static Long generateRandomLong(long min, long max) {
    if (max <= min) {
      throw new IllegalArgumentException("max must be greater than min.");
    }
    return random.nextLong(min, max);
  }
}
