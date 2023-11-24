package gr.evansp.todofullstackappbackend.todo.impl.models;

import gr.evansp.todofullstackappbackend.common.constants.ValidationConstants;
import gr.evansp.todofullstackappbackend.todo.models.def.Todo;
import gr.evansp.todofullstackappbackend.todo.models.impl.TodoImpl;
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
 * Unit tests for {@link TodoImpl}.
 */
class TestTodoImpl {
  /**
   * Subject under test.
   */
  Todo todo;

  /**
   * Initialization.
   */
  @BeforeEach
  public void setup() {
    todo = new TodoImpl();
  }

  @Test
  void testRequiredArgsConstructor() {
    Todo todo = new TodoImpl(1L, 1L, "title", "body");
    assertNotNull(todo);
  }

  @Test
  void testSetUserId() {
    todo.setUserId(1L);
    assertEquals(1L, todo.getUserId());
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
  void testToString() {
    assertNotNull(todo.toString());
  }

  @Test
  void testEquals_false() {
    Todo todo1 = new TodoImpl();
    todo1.setUserId(1L);
    todo1.setTodoListId(1L);
    todo1.setTodoId(1L);

    Todo todo2 = new TodoImpl();
    todo2.setUserId(1L);
    todo2.setTodoListId(2L);
    todo2.setTodoId(1L);

    assertNotEquals(todo1, todo2);
  }

  @Test
  void testEquals_true() {
    Todo todo1 = new TodoImpl();
    todo1.setUserId(1L);
    todo1.setTodoListId(1L);
    todo1.setTodoId(1L);

    Todo todo2 = new TodoImpl();
    todo2.setUserId(1L);
    todo2.setTodoListId(1L);
    todo2.setTodoId(1L);

    assertEquals(todo1, todo2);
  }

  @Test
  void testValidateUserId_null() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      todo.setTodoId(1L);
      todo.setTodoListId(1L);
      todo.setTitle("title");
      todo.setBody("Body");

      Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.ID_NULL));
    }
  }

  @Test
  void testValidateUserId_zero() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      todo.setUserId(0L);
      todo.setTodoId(1L);
      todo.setTodoListId(1L);
      todo.setTitle("title");
      todo.setBody("Body");

      Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.ID_GREATER_THAN_ZERO));
    }
  }

  @Test
  void testValidateUserId_negative() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      todo.setUserId(-1L);
      todo.setTodoId(1L);
      todo.setTodoListId(1L);
      todo.setTitle("title");
      todo.setBody("Body");

      Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.ID_GREATER_THAN_ZERO));
    }
  }

  @Test
  void testValidateListId_null() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      todo.setTodoId(1L);
      todo.setUserId(1L);
      todo.setTitle("title");
      todo.setBody("Body");

      Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.ID_NULL));
    }
  }

  @Test
  void testValidateListId_zero() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      todo.setUserId(1L);
      todo.setTodoId(1L);
      todo.setTodoListId(0L);
      todo.setTitle("title");
      todo.setBody("Body");

      Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.ID_GREATER_THAN_ZERO));
    }
  }

  @Test
  void testValidateListId_negative() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      todo.setUserId(1L);
      todo.setTodoId(1L);
      todo.setTodoListId(-1L);
      todo.setTitle("title");
      todo.setBody("Body");

      Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.ID_GREATER_THAN_ZERO));
    }
  }


  @Test
  void testValidateTodoId_null() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      todo.setTodoListId(1L);
      todo.setUserId(1L);
      todo.setTitle("title");
      todo.setBody("Body");

      Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.ID_NULL));
    }
  }

  @Test
  void testValidateTodoId_zero() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      todo.setUserId(1L);
      todo.setTodoId(0L);
      todo.setTodoListId(1L);
      todo.setTitle("title");
      todo.setBody("Body");

      Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.ID_GREATER_THAN_ZERO));
    }
  }

  @Test
  void testValidateTodoId_negative() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      todo.setUserId(1L);
      todo.setTodoId(-1L);
      todo.setTodoListId(1L);
      todo.setTitle("title");
      todo.setBody("Body");

      Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.ID_GREATER_THAN_ZERO));
    }
  }
}