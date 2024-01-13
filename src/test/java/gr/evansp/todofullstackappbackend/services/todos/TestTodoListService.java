package gr.evansp.todofullstackappbackend.services.todos;

import gr.evansp.todofullstackappbackend.base.BaseTest;
import gr.evansp.todofullstackappbackend.exceptions.NotFoundException;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.samples.Samples;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for {@link TodoListService}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class TestTodoListService extends BaseTest {

  public static final Long userId = 1L;
  private final List<TodoList> todoLists = new ArrayList<>();
  @Autowired
  TodoListService service;

  /**
   * Service Cleanup.
   */
  @AfterEach
  public void cleanup() {
    for (TodoList list : todoLists) {
      service.delete(list);
    }
  }

  /**
   * Test for {@link TodoListService#store(TodoList)} null input.
   */
  @Test
  void testStore_nullInput() {
    ConstraintViolationException exception =
        assertThrows(ConstraintViolationException.class, () -> service.store(null));
    assertTrue(exception.getMessage().contains(validationMessages.getString("todo.list.null")));
  }

  /**
   * Test for {@link TodoListService#store(TodoList)} invalid Input.
   */
  @Test
  void testStore_InvalidInput() {
    TodoList list = new TodoList();
    ConstraintViolationException exception =
        assertThrows(ConstraintViolationException.class, () -> service.store(list));
    assertTrue(exception.getMessage().contains(validationMessages.getString("id.null")));
    assertTrue(exception.getMessage().contains(validationMessages.getString("title.empty")));
  }

  /**
   * Test for {@link TodoListService#store(TodoList)} ok.
   */
  @Test
  void testStore_ok() {
    TodoList list = service.store(Samples.createSampleTodoList(userId));
    assertNotNull(list);
    assertTrue(list.getTodoListId() > 0);

    todoLists.add(list);
  }

  /**
   * Test for {@link TodoListService#find(Long)} null Input.
   */
  @Test
  void testFind_nullInput() {
    ConstraintViolationException exception =
        assertThrows(ConstraintViolationException.class, () -> service.find(null));
    assertTrue(exception.getMessage().contains(validationMessages.getString("id.null")));
  }

  /**
   * Test for {@link TodoListService#find(Long)} ok.
   */
  @Test
  void testFind_notFound() {
    NotFoundException exception = assertThrows(NotFoundException.class, () -> service.find(-1L));
    assertEquals(NotFoundException.LIST_NOT_FOUND, exception.getMessage());
  }


  /**
   * Test for {@link TodoListService#find(Long)} ok.
   */
  @Test
  void testFind_ok() {
    TodoList list = service.store(Samples.createSampleTodoList(userId));
    todoLists.add(list);

    assertEquals(list, service.find(list.getTodoListId()));
  }


  /**
   * Test for {@link TodoListService#update(TodoList)} (TodoList)} null input.
   */
  @Test
  void testUpdate_nullInput() {
    ConstraintViolationException exception =
        assertThrows(ConstraintViolationException.class, () -> service.update(null));
    assertTrue(exception.getMessage().contains(validationMessages.getString("todo.list.null")));
  }

  /**
   * Test for {@link TodoListService#update(TodoList)} invalid Input.
   */
  @Test
  void testUpdate_InvalidInput() {
    TodoList list = new TodoList();
    ConstraintViolationException exception =
        assertThrows(ConstraintViolationException.class, () -> service.update(list));
    assertTrue(exception.getMessage().contains(validationMessages.getString("id.null")));
    assertTrue(exception.getMessage().contains(validationMessages.getString("title.empty")));
  }

  /**
   * Test for {@link TodoListService#update(TodoList)} ok.
   */
  @Test
  void testUpdate_ok() {
    TodoList list = service.store(Samples.createSampleTodoList(userId));
    todoLists.add(list);

    list.setTitle("LALALA");
    service.update(list);
    assertEquals("LALALA", service.find(list.getTodoListId()).getTitle());
  }

  /**
   * Test for {@link TodoListService#delete(TodoList)} null input.
   */
  @Test
  void testDelete_nullInput() {
    ConstraintViolationException exception =
        assertThrows(ConstraintViolationException.class, () -> service.delete(null));
    assertTrue(exception.getMessage().contains(validationMessages.getString("todo.list.null")));
  }

  /**
   * Test for {@link TodoListService#delete(TodoList)} ok.
   */
  @Test
  void testDelete_ok() {
    TodoList list = service.store(Samples.createSampleTodoList(userId));
    service.delete(list);

    Long id = list.getTodoListId();

    assertThrows(NotFoundException.class, () -> service.find(id));
  }
}

