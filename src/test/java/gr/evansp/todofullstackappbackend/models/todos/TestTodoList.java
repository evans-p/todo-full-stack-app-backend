package gr.evansp.todofullstackappbackend.models.todos;

import gr.evansp.todofullstackappbackend.base.BaseTest;
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
 * Unit tests for {@link TodoList}.
 */
class TestTodoList extends BaseTest {

  /**
   * Subject under test.
   */
  TodoList list;

  /**
   * Initialization.
   */
  @BeforeEach
  public void setup() {
    list = new TodoList();
  }

  @Test
  void testRequiredArgsConstructor() {
    TodoList list = new TodoList("1", "sample list");
    assertNotNull(list);
  }

  @Test
  void testSetUserId() {
    list.setUserId("1");
    assertEquals("1", list.getUserId());
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
    TodoList list1 = new TodoList();
    list1.setTodoListId(1L);

    TodoList list2 = new TodoList();
    list2.setTodoListId(2L);

    assertNotEquals(list1, list2);
  }

  @Test
  void testEquals_true() {
    TodoList list1 = new TodoList();
    list1.setTodoListId(1L);

    TodoList list2 = new TodoList();
    list2.setTodoListId(1L);

    assertEquals(list1, list2);
  }

  @Test
  void testValidate_nullListId() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list = new TodoList("1", "sample list");

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(0, violations.size());
    }
  }

  @Test
  void testValidate_zeroListId() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list = new TodoList("1", "sample list");
      list.setTodoListId(0L);

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(VALIDATION_MESSAGES.getString("id.min")));
    }
  }

  @Test
  void testValidate_negativeListId() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list = new TodoList("1", "sample list");
      list.setTodoListId(-1L);

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(VALIDATION_MESSAGES.getString("id.min")));
    }
  }

  @Test
  void testValidate_nullUserId() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list = new TodoList();
      list.setTitle("sample list");

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(VALIDATION_MESSAGES.getString("user.id.null")));
    }
  }

  @Test
  void testValidate_EmptyUserId() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list = new TodoList("", "sample list");


      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(VALIDATION_MESSAGES.getString("user.id.null")));
    }
  }

  @Test
  void testValidate_nullTitle() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list = new TodoList();
      list.setUserId("sample list");

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(VALIDATION_MESSAGES.getString("title.empty")));
    }
  }


  @Test
  void testValidate_emptyTitle() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list = new TodoList("id", "");

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(VALIDATION_MESSAGES.getString("title.empty")));
    }
  }

  @Test
  void testValidate_nullCreated() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list = new TodoList("id", "sample list");
      list.setCreated(null);

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(VALIDATION_MESSAGES.getString("create.date.null")));
    }
  }


  @Test
  void testValidate_nullLastModified() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();

      list = new TodoList("id", "sample list");
      list.setLastModified(null);

      Set<ConstraintViolation<TodoList>> violations = validator.validate(list);
      assertEquals(1, violations.size());
      assertTrue(violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet())
          .contains(VALIDATION_MESSAGES.getString("last.modified.date.null")));
    }
  }
}
