package gr.evansp.todofullstackappbackend.todo.impl.models;

import gr.evansp.todofullstackappbackend.common.constants.StringConstants;
import gr.evansp.todofullstackappbackend.common.constants.ValidationConstants;
import gr.evansp.todofullstackappbackend.todo.def.models.Todo;
import gr.evansp.todofullstackappbackend.todo.def.models.TodoList;
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
 * Unit tests for {@link TodoListImpl}.
 */
class TestTodoListImpl {

  /**
   * Subject under test.
   */
  TodoList list;

  /**
   * Initialization.
   */
  @BeforeEach
  public void setup() {
    list = new TodoListImpl();
  }

  @Test
  void testRequiredArgsConstructor() {
    TodoList list = new TodoListImpl(1L, "sample list");
    assertNotNull(list);
  }

  @Test
  void testSetUserId() {
    list.setUserId(1L);
    assertEquals(1L, list.getUserId());
  }

  @Test
  void testSetTodoListId() {
    list.setTodoListId(1L);
    assertEquals(1L, list.getTodoListId());
  }

  @Test
  void testSetTitle() {
    list.setTitle("title");
    assertEquals("title", list.getTitle());
  }

  @Test
  void testSetCreated() {
    LocalDateTime dateTime = LocalDateTime.now();
    list.setCreated(dateTime);
    assertEquals(dateTime, list.getCreated());
  }

  @Test
  void testSetLastModified() {
    LocalDateTime dateTime = LocalDateTime.now();
    list.setLastModified(dateTime);
    assertEquals(dateTime, list.getLastModified());
  }

  @Test
  void testSetTodos() {
    Set<Todo> todos = new HashSet<>();
    list.setTodos(todos);
    assertEquals(todos, list.getTodos());
  }

  @Test
  void testToString() {
    assertNotNull(list.toString());
  }

  @Test
  void testEquals_false() {
    TodoList list1 = new TodoListImpl();
    list1.setUserId(1L);
    list1.setTodoListId(1L);

    TodoList list2 = new TodoListImpl();
    list2.setUserId(1L);
    list2.setTodoListId(2L);

    assertNotEquals(list1, list2);

    list1.setUserId(1L);
    list1.setTodoListId(2L);

    list2.setUserId(1L);
    list2.setTodoListId(1L);

    assertNotEquals(list1, list2);
  }

  @Test
  void testEquals_true() {
    TodoList list1 = new TodoListImpl();
    list1.setUserId(1L);
    list1.setTodoListId(1L);

    TodoList list2 = new TodoListImpl();
    list2.setUserId(1L);
    list2.setTodoListId(1L);

    assertEquals(list1, list2);
  }

  @Test
  void testValidateUserId_null() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list.setTodoListId(1L);
      list.setTitle("title");

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
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

      list.setUserId(0L);
      list.setTodoListId(1L);
      list.setTitle("title");

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
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

      list.setUserId(-1L);
      list.setTodoListId(1L);
      list.setTitle("title");

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.ID_GREATER_THAN_ZERO));
    }
  }

  @Test
  void testValidateTodoListId_null() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list.setUserId(1L);
      list.setTitle("title");

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.ID_NULL));
    }
  }

  @Test
  void testValidateTodoListId_zero() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list.setTodoListId(0L);
      list.setUserId(1L);
      list.setTitle("title");

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.ID_GREATER_THAN_ZERO));
    }
  }

  @Test
  void testValidateTodoListId_negative() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list.setTodoListId(-1L);
      list.setUserId(1L);
      list.setTitle("title");

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.ID_GREATER_THAN_ZERO));
    }
  }

  @Test
  void testValidateTitle_null() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list.setTodoListId(1L);
      list.setUserId(1L);

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.TITLE_EMPTY));
    }
  }

  @Test
  void testValidateTitle_empty() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list.setTodoListId(1L);
      list.setUserId(1L);
      list.setTitle(StringConstants.EMPTY);

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.TITLE_EMPTY));
    }
  }


  @Test
  void testValidateCreated_null() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list.setTodoListId(1L);
      list.setUserId(1L);
      list.setTitle("title");
      list.setCreated(null);

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.CREATE_DATE_NULL));
    }
  }

  @Test
  void testValidateLastModified_null() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list.setTodoListId(1L);
      list.setUserId(1L);
      list.setTitle("title");
      list.setCreated(null);

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(ValidationConstants.CREATE_DATE_NULL));
    }
  }

  @Test
  void testValidate_ok() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list.setTodoListId(1L);
      list.setUserId(1L);
      list.setTitle("title");

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(0, violations.size());
    }
  }
}
