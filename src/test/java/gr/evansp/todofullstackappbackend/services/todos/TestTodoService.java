package gr.evansp.todofullstackappbackend.services.todos;

import gr.evansp.todofullstackappbackend.base.BaseTest;
import gr.evansp.todofullstackappbackend.common.exceptions.NotFoundException;
import gr.evansp.todofullstackappbackend.common.exceptions.messages.ExceptionMessages;
import gr.evansp.todofullstackappbackend.models.todos.Todo;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.models.users.User;
import gr.evansp.todofullstackappbackend.repositories.todos.TodoListRepository;
import gr.evansp.todofullstackappbackend.repositories.users.UserRepository;
import gr.evansp.todofullstackappbackend.samples.Samples;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for {@link TestTodoService}
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class TestTodoService extends BaseTest {

  private final List<Todo> todos = new ArrayList<>();

  @Autowired
  TodoService service;

  @Autowired
  UserRepository userRepository;

  @Autowired
  TodoListRepository todoListRepository;

  private User user;

  private TodoList list;

  /**
   * Service setup.
   */
  @BeforeEach
  public void setup() {
    if (user == null) {
      user = userRepository.save(Samples.createSampleUser());
    }
    if (list == null) {
      list = todoListRepository.save(Samples.createSampleTodoList(user.getUserId()));
    }
  }

  /**
   * Service Cleanup.
   */
  @AfterEach
  public void cleanup() {
    userRepository.delete(user);
    todoListRepository.delete(list);

    for (Todo todo : todos) {
      service.delete(todo);
    }
  }

  /**
   * Test for {@link TodoService#store(Todo)} null input.
   */
  @Test
  void testStore_nullInput() {
    ConstraintViolationException exception =
        assertThrows(ConstraintViolationException.class, () -> service.store(null));
    assertTrue(exception.getMessage().contains(validationMessages.getString("todo.null")));
  }

  /**
   * Test for {@link TodoService#store(Todo)} invalid Input.
   */
  @Test
  void testStore_InvalidInput() {
    Todo todo = new Todo();
    ConstraintViolationException exception =
        assertThrows(ConstraintViolationException.class, () -> service.store(todo));
    assertTrue(exception.getMessage().contains(validationMessages.getString("id.null")));
    assertTrue(exception.getMessage().contains(validationMessages.getString("title.empty")));
  }


  /**
   * Test for {@link TodoService#store(Todo)} ok.
   */
  @Test
  void testStore_ok() {
    Todo todo = service.store(Samples.createSampleTodo(list.getTodoListId()));
    assertNotNull(todo);
    assertTrue(todo.getTodoId() > 0);

    todos.add(todo);
  }

  /**
   * Test for {@link TodoService#find(Long)} null Input.
   */
  @Test
  void testFind_nullInput() {
    ConstraintViolationException exception =
        assertThrows(ConstraintViolationException.class, () -> service.find(null));
    assertTrue(exception.getMessage().contains(validationMessages.getString("id.null")));
  }


  /**
   * Test for {@link TodoService#find(Long)} ok.
   */
  @Test
  void testFind_notFound() {
    NotFoundException exception = assertThrows(NotFoundException.class, () -> service.find(-1L));
    assertEquals(ExceptionMessages.TODO_NOT_FOUND, exception.getMessage());
  }

  /**
   * Test for {@link TodoListService#find(Long)} ok.
   */
  @Test
  void testFind_ok() {
    Todo todo = service.store(Samples.createSampleTodo(list.getTodoListId()));
    todos.add(todo);

    assertEquals(todo, service.find(todo.getTodoId()));
  }


  /**
   * Test for {@link TodoService#update(Todo)} null input.
   */
  @Test
  void testUpdate_nullInput() {
    ConstraintViolationException exception =
        assertThrows(ConstraintViolationException.class, () -> service.update(null));
    assertTrue(exception.getMessage().contains(validationMessages.getString("todo.null")));
  }

  /**
   * Test for {@link TodoService#update(Todo)} invalid Input.
   */
  @Test
  void testUpdate_InvalidInput() {
    Todo todo = new Todo();
    ConstraintViolationException exception =
        assertThrows(ConstraintViolationException.class, () -> service.update(todo));
    assertTrue(exception.getMessage().contains(validationMessages.getString("id.null")));
    assertTrue(exception.getMessage().contains(validationMessages.getString("title.empty")));
  }


  /**
   * Test for {@link TodoService#update(Todo)} ok.
   */
  @Test
  void testUpdate_ok() {
    Todo todo = service.store(Samples.createSampleTodo(list.getTodoListId()));
    todos.add(todo);

    todo.setTitle("LALALA");
    service.update(todo);
    assertEquals("LALALA", service.find(todo.getTodoId()).getTitle());
  }


  /**
   * Test for {@link TodoService#delete(Todo)} null input.
   */
  @Test
  void testDelete_nullInput() {
    ConstraintViolationException exception =
        assertThrows(ConstraintViolationException.class, () -> service.delete(null));
    assertTrue(exception.getMessage().contains(validationMessages.getString("todo.null")));
  }

  /**
   * Test for {@link TodoListService#delete(TodoList)} ok.
   */
  @Test
  void testDelete_ok() {
    Todo todo = service.store(Samples.createSampleTodo(list.getTodoListId()));
    todos.add(todo);

    Long id = todo.getTodoListId();

    assertThrows(NotFoundException.class, () -> service.find(id));
  }
}