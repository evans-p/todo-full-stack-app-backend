package gr.evansp.todofullstackappbackend.models.todos;

import gr.evansp.todofullstackappbackend.base.BaseTest;
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
 * Unit tests for Todo1.
 */
class TestTodo extends BaseTest {
  /**
   * Subject under test.
   */
  Todo todo;

  /**
   * Initialization.
   */
  @BeforeEach
  public void setup() {
    todo = new Todo();
  }

  @Test
  void testRequiredArgsConstructor() {
    Todo todo = new Todo(1L, "title", "userId");
    assertNotNull(todo);
  }

  @Test
  void testSetTodoListId() {
    todo.setTodoListId(1L);
    assertEquals(1L, todo.getTodoListId());
  }

  @Test
  void testSetTodoId() {
    todo.setTodoId(1L);
    assertEquals(1L, todo.getTodoId());
  }

  @Test
  void testSetTitle() {
    todo.setTitle("title");
    assertEquals("title", todo.getTitle());
  }

  @Test
  void testSetBody() {
    todo.setBody("body");
    assertEquals("body", todo.getBody());
  }

  @Test
  void testSetCreated() {
    LocalDateTime dateTime = LocalDateTime.now();
    todo.setCreated(dateTime);
    assertEquals(dateTime, todo.getCreated());
  }

  @Test
  void testSetLastModified() {
    LocalDateTime dateTime = LocalDateTime.now();
    todo.setLastModified(dateTime);
    assertEquals(dateTime, todo.getLastModified());
  }

  @Test
  void testSetFavourite() {
    todo.setFavourite(true);
    assertEquals(true, todo.getFavourite());
  }

  @Test
  void testSetUserId() {
    todo.setUserId("123");
    assertEquals("123", todo.getUserId());
  }

  @Test
  void testToString() {
    assertNotNull(todo.toString());
  }

  @Test
  void testEquals_false() {
    Todo todo1 = new Todo();
    todo1.setTodoId(1L);

    Todo todo2 = new Todo();
    todo2.setTodoId(2L);

    assertNotEquals(todo1, todo2);
  }

  @Test
  void testEquals_true() {
    Todo todo1 = new Todo();
    todo1.setTodoId(1L);

    Todo todo2 = new Todo();
    todo2.setTodoId(1L);

    assertEquals(todo1, todo2);
  }

  @Test
  void testValidateListId_null() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      todo.setTodoId(1L);
      todo.setTitle("title");
      todo.setUserId("Body");

      Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(VALIDATION_MESSAGES.getString("id.null")));
    }
  }

  @Test
  void testValidateListId_zero() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      todo.setTodoId(1L);
      todo.setTodoListId(0L);
      todo.setTitle("title");
      todo.setUserId("Body");

      Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(VALIDATION_MESSAGES.getString("id.min")));
    }
  }

  @Test
  void testValidateListId_negative() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      todo.setTodoId(1L);
      todo.setTodoListId(-1L);
      todo.setTitle("title");
      todo.setUserId("Body");

      Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(VALIDATION_MESSAGES.getString("id.min")));
    }
  }

  @Test
  void testValidateTodoId_zero() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      todo.setTodoId(0L);
      todo.setTodoListId(1L);
      todo.setTitle("title");
      todo.setUserId("UserId");

      Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(VALIDATION_MESSAGES.getString("id.min")));
    }
  }

  @Test
  void testValidateTodoId_negative() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      todo.setTodoId(-1L);
      todo.setTodoListId(1L);
      todo.setTitle("title");
      todo.setUserId("UserId");

      Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(VALIDATION_MESSAGES.getString("id.min")));
    }
  }
}