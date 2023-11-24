package gr.evansp.todofullstackappbackend.todo.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for {@link TodoListPK}
 */
class TestTodoListPK {

  /**
   * Subject under test.
   */
  TodoListPK pk;

  /**
   * Initialization.
   */
  @BeforeEach
  public void setup() {
    pk = new TodoListPK();
  }


  @Test
  void testRequiredArgsConstructor() {
    TodoListPK list = new TodoListPK(1L, 1L);
    assertNotNull(list);
  }

  @Test
  void testSetUserId() {
    pk.setUserId(1L);
    Assertions.assertEquals(1L, (long) pk.getUserId());
  }

  @Test
  void testSetTodoListId() {
    pk.setTodoListId(1L);
    Assertions.assertEquals(1L, (long) pk.getTodoListId());
  }


  @Test
  void testToString() {
    assertNotNull(pk.toString());
  }

  @Test
  void testEquals_false() {
    TodoListPK pk1 = new TodoListPK();
    pk1.setUserId(1L);
    pk1.setTodoListId(1L);

    TodoListPK pk2 = new TodoListPK();
    pk2.setUserId(1L);
    pk2.setTodoListId(2L);

    Assertions.assertNotEquals(pk1, pk2);

    pk1.setUserId(1L);
    pk1.setTodoListId(2L);

    pk2.setUserId(1L);
    pk2.setTodoListId(1L);

    Assertions.assertNotEquals(pk1, pk2);
  }

  @Test
  void testEquals_true() {
    TodoListPK pk1 = new TodoListPK();
    pk1.setUserId(1L);
    pk1.setTodoListId(1L);

    TodoListPK pk2 = new TodoListPK();
    pk2.setUserId(1L);
    pk2.setTodoListId(1L);

    Assertions.assertEquals(pk1, pk2);
  }
}