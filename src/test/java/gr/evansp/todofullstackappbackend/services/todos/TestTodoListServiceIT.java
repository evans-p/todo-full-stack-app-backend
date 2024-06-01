package gr.evansp.todofullstackappbackend.services.todos;

import gr.evansp.todofullstackappbackend.base.BaseITTest;
import gr.evansp.todofullstackappbackend.exceptions.LogicException;
import gr.evansp.todofullstackappbackend.exceptions.NotFoundException;
import gr.evansp.todofullstackappbackend.exceptions.UnauthorizedException;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.repositories.todos.TodoListRepository;
import gr.evansp.todofullstackappbackend.samples.Samples;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for{@link TodoListService}
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class TestTodoListServiceIT extends BaseITTest {

  /**
   * Sut.
   */
  @Autowired
  TodoListService service;

  /**
   * Repository.
   */
  @Autowired
  TodoListRepository repository;

  /**
   * TodoList.
   */
  TodoList list;


  @AfterEach
  void cleanup() {
    if (list != null) {
      repository.delete(list);
    }
  }

  /**
   * Test for {@link TodoListService#getAll()}
   */
  @Test
  void testGetAll_unauthorized() {
    SecurityContextHolder.clearContext();
    UnauthorizedException e = assertThrows(UnauthorizedException.class, () -> service.getAll());

    assertEquals(UnauthorizedException.UNAUTHORIZED, e.getMessage());
  }

  /**
   * Test for {@link TodoListService#getAll()}
   */
  @Test
  void testGetAll_empty() {
    List<TodoList> result = service.getAll();

    assertFalse(result.isEmpty());
  }

  /**
   * Test for {@link TodoListService#getAll()}
   */
  @Test
  void testGetAll_ok() {
    list = repository.save(Samples.createSampleTodoList(SUB));

    List<TodoList> result = service.getAll();

      assertFalse(result.isEmpty());
  }

  /**
   * Test for {@link TodoListService#find(Long)}.
   */
  @Test
  void testFind_nullInput() {
    ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> service.find(null));
    assertTrue(e.getMessage().contains(VALIDATION_MESSAGES.getString("id.null")));
  }

  /**
   * Test for {@link TodoListService#find(Long)}.
   */
  @Test
  void testFind_notFound() {
    NotFoundException e = assertThrows(NotFoundException.class, () -> service.find(-1L));
    assertEquals(NotFoundException.LIST_NOT_FOUND, e.getMessage());
  }

  /**
   * Test for {@link TodoListService#find(Long)}.
   */
  @Test
  void testFind_ok() {
    list = repository.save(Samples.createSampleTodoList(SUB));
    TodoList result = service.find(list.getTodoListId());

    assertEquals(list, result);
  }

  /**
   * Test for {@link TodoListService#store(TodoList)}
   */
  @Test
  void testStore_nullInput() {
    ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> service.store(null));
    assertTrue(e.getMessage().contains(VALIDATION_MESSAGES.getString("todo.list.null")));
  }

  /**
   * Test for {@link TodoListService#store(TodoList)}
   */
  @Test
  void testStore_invalidInput() {
    TodoList todoList = new TodoList();
    todoList.setUserId(SUB);
    ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> service.store(todoList));
    assertTrue(e.getMessage().contains(VALIDATION_MESSAGES.getString("title.empty")));
  }

  /**
   * Test for {@link TodoListService#store(TodoList)}
   */
  @Test
  void testStore_AlreadyExists() {
    TodoList todoList = new TodoList(SUB, "sample list");
    todoList.setTodoListId(1L);
    LogicException e = assertThrows(LogicException.class, () -> service.store(todoList));
    assertEquals(LogicException.ALREADY_EXISTS, e.getMessage());
  }

  /**
   * Test for {@link TodoListService#store(TodoList)}
   */
  @Test
  void testStore_unauthorized() {
    SecurityContextHolder.clearContext();
    TodoList todoList = new TodoList(SUB, "sample list");
    UnauthorizedException e = assertThrows(UnauthorizedException.class, () -> service.store(todoList));
    assertEquals(UnauthorizedException.UNAUTHORIZED, e.getMessage());
  }

  /**
   * Test for {@link TodoListService#store(TodoList)}
   */
  @Test
  void testStore_notTheOwner() {
    TodoList todoList = new TodoList("dummy", "sample list");
    UnauthorizedException e = assertThrows(UnauthorizedException.class, () -> service.store(todoList));
    assertEquals(UnauthorizedException.UNAUTHORIZED, e.getMessage());
  }

  /**
   * Test for {@link TodoListService#store(TodoList)}
   */
  @Test
  void testStore_ok() {
    list = service.store(Samples.createSampleTodoList(SUB));
    assertNotNull(list);
    assertNotNull(list.getTodoListId());
  }


  /**
   * Test for {@link TodoListService#update(TodoList)}
   */
  @Test
  void testUpdate_nullInput() {
    ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> service.update(null));
    assertTrue(e.getMessage().contains(VALIDATION_MESSAGES.getString("todo.list.null")));
  }

  /**
   * Test for {@link TodoListService#update(TodoList)}
   */
  @Test
  void testUpdate_invalidInput() {
    TodoList todoList = new TodoList();
    todoList.setUserId(SUB);
    ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> service.update(todoList));
    assertTrue(e.getMessage().contains(VALIDATION_MESSAGES.getString("title.empty")));
  }

  /**
   * Test for {@link TodoListService#update(TodoList)}
   */
  @Test
  void testUpdate_doesNotExist() {
    TodoList todoList = new TodoList(SUB, "sample list");
    LogicException e = assertThrows(LogicException.class, () -> service.update(todoList));
    assertEquals(LogicException.DOES_NOT_EXIST, e.getMessage());
  }

  /**
   * Test for {@link TodoListService#update(TodoList)}
   */
  @Test
  void testUpdate_ok() {
    list = service.store(new TodoList(SUB, "sample list"));
    list.setTitle("UPDATED");
    TodoList result = service.update(list);

    assertEquals(list, result);
    assertNotEquals("sample list", result.getTitle());
  }

  /**
   * Test for {@link TodoListService#delete(Long)}
   */
  @Test
  void testDelete_ok() {
    TodoList list = service.store(new TodoList(SUB, "sample list"));
    service.delete(list.getTodoListId());
    assertFalse(service.getAll().contains(list));
  }

}