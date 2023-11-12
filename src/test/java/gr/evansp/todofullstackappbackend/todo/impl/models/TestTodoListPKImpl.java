package gr.evansp.todofullstackappbackend.todo.impl.models;

import gr.evansp.todofullstackappbackend.todo.def.models.TodoListPK;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link TodoListPKImpl}
 */
class TestTodoListPKImpl {

  /**
   * Subject under test.
   */
  TodoListPKImpl pk;

  /**
   * Initialization.
   */
  @BeforeEach
  public void setup() {
    pk = new TodoListPKImpl();
  }


  @Test
  void testRequiredArgsConstructor() {
    TodoListPK list = new TodoListPKImpl(1L, 1L);
    assertNotNull(list);
  }

  @Test
  void testSetUserId() {
    pk.setUserId(1L);
    assertEquals(1L, pk.getUserId());
  }

  @Test
  void testSetTodoListId() {
    pk.setTodoListId(1L);
    assertEquals(1L, pk.getTodoListId());
  }


  @Test
  void testToString() {
    assertNotNull(pk.toString());
  }

  @Test
  void testEquals_false() {
    TodoListPK pk1 = new TodoListPKImpl();
    pk1.setUserId(1L);
    pk1.setTodoListId(1L);

    TodoListPK pk2 = new TodoListPKImpl();
    pk2.setUserId(1L);
    pk2.setTodoListId(2L);

    assertNotEquals(pk1, pk2);

    pk1.setUserId(1L);
    pk1.setTodoListId(2L);

    pk2.setUserId(1L);
    pk2.setTodoListId(1L);

    assertNotEquals(pk1, pk2);
  }

  @Test
  void testEquals_true() {
    TodoListPK pk1 = new TodoListPKImpl();
    pk1.setUserId(1L);
    pk1.setTodoListId(1L);

    TodoListPK pk2 = new TodoListPKImpl();
    pk2.setUserId(1L);
    pk2.setTodoListId(1L);

    assertEquals(pk1, pk2);

  }
}