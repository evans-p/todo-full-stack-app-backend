package gr.evansp.todofullstackappbackend.todo.impl.models;

import gr.evansp.todofullstackappbackend.todo.models.def.TodoPK;
import gr.evansp.todofullstackappbackend.todo.models.impl.TodoPKImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for {@link TodoPKImpl}.
 */
class TestTodoPKImpl {

  /**
   * Subject under test.
   */
  TodoPK pk;

  /**
   * Initialization.
   */
  @BeforeEach
  public void setup() {
    pk = new TodoPKImpl();
  }


  @Test
  void testRequiredArgsConstructor() {
    TodoPK list = new TodoPKImpl(1L, 1L, 1L);
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
  void testSetTodoId() {
    pk.setTodoId(1L);
    assertEquals(1L, pk.getTodoId());
  }


  @Test
  void testToString() {
    assertNotNull(pk.toString());
  }

  @Test
  void testEquals_false() {
    TodoPK pk1 = new TodoPKImpl();
    pk1.setUserId(1L);
    pk1.setTodoListId(1L);
    pk1.setTodoId(1L);

    TodoPK pk2 = new TodoPKImpl();
    pk2.setUserId(1L);
    pk2.setTodoListId(2L);
    pk2.setTodoId(1L);

    assertNotEquals(pk1, pk2);
  }

  @Test
  void testEquals_true() {
    TodoPK pk1 = new TodoPKImpl();
    pk1.setUserId(1L);
    pk1.setTodoListId(1L);
    pk1.setTodoId(1L);

    TodoPK pk2 = new TodoPKImpl();
    pk2.setUserId(1L);
    pk2.setTodoListId(1L);
    pk2.setTodoId(1L);

    assertEquals(pk1, pk2);
  }
}