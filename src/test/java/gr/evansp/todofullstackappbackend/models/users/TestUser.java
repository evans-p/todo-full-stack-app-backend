package gr.evansp.todofullstackappbackend.models.users;

import gr.evansp.todofullstackappbackend.base.BaseTest;
import gr.evansp.todofullstackappbackend.constants.StringConstants;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link User}.
 */
class TestUser extends BaseTest {

  User user;

  @BeforeEach
  public void setup() {
    user = new User();
    user.setUserId(1L);
  }

  @Test
  void testRequiredArgsConstructor() {
    user = new User("example@example.com", "123");
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
  void testSetTodoList() {
    Set<TodoList> todoLists = new HashSet<>();
    user.setTodoLists(todoLists);
    assertSame(todoLists, user.getTodoLists());
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
          .contains(validationMessages.getString("email.empty")));
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
          .contains(validationMessages.getString("email.empty")));
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
          .contains(validationMessages.getString("email.invalid")));
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
          .contains(validationMessages.getString("password.empty")));
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
          .contains(validationMessages.getString("password.too.short")));
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
          .contains(validationMessages.getString("password.too.short")));
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
    User user1 = new User();

    user1.setUserId(1L);
    user1.setPassword("1234567891");
    user1.setEmail("example1@example.com");
    user1.setCreated(LocalDateTime.now());

    User user2 = new User();

    user2.setUserId(1L);
    user2.setPassword("1234567892");
    user2.setEmail("example2@example.com");
    user2.setCreated(LocalDateTime.now());

    assertEquals(user1, user2);
  }

  @Test
  void testEquals_false() {
    User user1 = new User();

    user1.setUserId(1L);
    user1.setPassword("1234567891");
    user1.setEmail("example1@example.com");
    user1.setCreated(LocalDateTime.now());

    User user2 = new User();

    user2.setUserId(2L);
    user2.setPassword("1234567891");
    user2.setEmail("example1@example.com");
    user2.setCreated(LocalDateTime.now());

    assertNotEquals(user1, user2);
  }
}