package gr.evansp.todofullstackappbackend.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;

/**
 * Tests for {@link NumberUtils}.
 */
class TestNumberUtils {

  @Test
  void testGenerateRandomLong_minEqualsMax() {
    assertThrows(IllegalArgumentException.class, () -> NumberUtils.generateRandomLong(10, 10));
  }

  @Test
  void testGenerateRandomLong_minGreaterThanMax() {
    assertThrows(IllegalArgumentException.class, () -> NumberUtils.generateRandomLong(100, 10));
  }

  @Test
  void testGenerateRandomLong_ok() {
    Assertions.assertNotNull(NumberUtils.generateRandomLong(-100, -10));
  }
}