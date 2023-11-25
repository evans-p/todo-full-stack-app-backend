package gr.evansp.todofullstackappbackend.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for {@link StringUtils}
 */
class TestStringUtils {

  @Test
  void TestGenerateRandomString_negativeLength() {
    assertThrows(IllegalArgumentException.class, () -> StringUtils.generateRandomString(-1));
  }

  @Test
  void TestGenerateRandomString_zeroLength() {
    assertThrows(IllegalArgumentException.class, () -> StringUtils.generateRandomString(0));
  }

  @Test
  void TestGenerateRandomString_lengthTooBig() {
    assertThrows(IllegalArgumentException.class, () -> StringUtils.generateRandomString(10000));
  }

  @Test
  void TestGenerateRandomString_ok() {
    assertNotNull(StringUtils.generateRandomString(1));
  }
}