package gr.evansp.todofullstackappbackend.user.impl.models;

import gr.evansp.todofullstackappbackend.common.constants.StringConstants;
import gr.evansp.todofullstackappbackend.common.constants.ValidationConstants;
import gr.evansp.todofullstackappbackend.user.def.models.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link UserImpl}.
 */
class TestUserImpl {

  User user;

  @BeforeEach
  public void setup() {
    user = new UserImpl();
    user.setUserId(1L);
  }

  @Test
  void testAllArgsConstructor() {
    user = new UserImpl("example@example.com", "123");
    assertNotNull(user);
  }

  @Test
  void testSetEmail() {
    user.setEmail("example@example.com");
    assertEquals("example@example.com", user.getEmail());
  }

  @Test
  void testSetUserId() {
    user.setUserId(1L);
    assertEquals(1L, user.getUserId());
  }

  @Test
  void testSetCreated() {
    LocalDateTime dateTime = LocalDateTime.now();
    user.setCreated(dateTime);
    assertEquals(dateTime, user.getCreated());
  }

  @Test
  void testSetPassword() {
    user.setPassword("123");
    assertEquals("123", user.getPassword());
  }

  @Test
  void testValidateEmail_nullEmail() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();
      user.setPassword("123456789");

      Set<ConstraintViolation<User>> violations = validator.validate(user);

      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.EMAIL_EMPTY));
    }
  }

  @Test
  void testValidateEmail_emptyEmail() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      user.setPassword("123456789");

      Set<ConstraintViolation<User>> violations = validator.validate(user);

      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.EMAIL_EMPTY));
    }
  }

  @Test
  void testValidateEmail_invalidEmail() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      user.setPassword("123456789");
      user.setEmail("sadASD");

      Set<ConstraintViolation<User>> violations = validator.validate(user);

      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.EMAIL_VALID));
    }
  }

  @Test
  void testValidateEmail_ok() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      user.setPassword("123456789");
      user.setEmail("example@example.com");

      Set<ConstraintViolation<User>> violations = validator.validate(user);

      assertEquals(0, violations.size());
    }
  }

  @Test
  void testValidatePassword_nullPassword() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      user.setEmail("example@example.com");

      Set<ConstraintViolation<User>> violations = validator.validate(user);

      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.PASSWORD_NULL));
    }
  }

  @Test
  void testValidatePassword_emptyPassword() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      user.setEmail("example@example.com");
      user.setPassword(StringConstants.EMPTY);

      Set<ConstraintViolation<User>> violations = validator.validate(user);

      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.PASSWORD_SHORT));
    }
  }

  @Test
  void testValidatePassword_shortPassword() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      user.setPassword("123");
      user.setEmail("example@example.com");

      Set<ConstraintViolation<User>> violations = validator.validate(user);

      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.PASSWORD_SHORT));
    }
  }

  @Test
  void testValidatePassword_ok() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      user.setPassword("123456789");
      user.setEmail("example@example.com");

      Set<ConstraintViolation<User>> violations = validator.validate(user);

      assertEquals(0, violations.size());
    }
  }

  @Test
  void testToString() {
    assertNotNull(user.toString());
  }

  @Test
  void testEquals_true() {
    User user1 = new UserImpl();

    user1.setUserId(1L);
    user1.setPassword("1234567891");
    user1.setEmail("example1@example.com");
    user1.setCreated(LocalDateTime.now());

    User user2 = new UserImpl();

    user2.setUserId(1L);
    user2.setPassword("1234567892");
    user2.setEmail("example2@example.com");
    user2.setCreated(LocalDateTime.now());

    assertEquals(user1, user2);
  }

  @Test
  void testEquals_false() {
    User user1 = new UserImpl();

    user1.setUserId(1L);
    user1.setPassword("1234567891");
    user1.setEmail("example1@example.com");
    user1.setCreated(LocalDateTime.now());

    User user2 = new UserImpl();

    user2.setUserId(2L);
    user2.setPassword("1234567891");
    user2.setEmail("example1@example.com");
    user2.setCreated(LocalDateTime.now());

    assertNotEquals(user1, user2);
  }
}