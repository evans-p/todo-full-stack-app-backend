package gr.evansp.todofullstackappbackend.services.todos;

import gr.evansp.todofullstackappbackend.base.BaseITTest;
import gr.evansp.todofullstackappbackend.exceptions.LogicException;
import gr.evansp.todofullstackappbackend.exceptions.NotFoundException;
import gr.evansp.todofullstackappbackend.exceptions.UnauthorizedException;
import gr.evansp.todofullstackappbackend.models.todos.Todo;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.repositories.todos.TodoListRepository;
import gr.evansp.todofullstackappbackend.repositories.todos.TodoRepository;
import gr.evansp.todofullstackappbackend.samples.Samples;
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
 * Integration tests for {@link TodoService}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class TestTodoServiceIT extends BaseITTest {
  /**
   * {@link TodoService}.
   */
  @Autowired
  TodoService todoService;

  /**
   * {@link TodoRepository}.
   */
  @Autowired
  TodoRepository todoRepository;

  /**
   * {@link TodoListRepository}.
   */
  @Autowired
  TodoListRepository todoListRepository;

  /**
   * Todo1
   */
  Todo todo;

  /**
   * {@link TodoList}.
   */
  TodoList list;

  /**
   * Cleanup
   */
  @AfterEach
  void cleanup() {
    if (list != null) {
      todoListRepository.delete(list);
    }
  }

  /**
   * Test for {@link TodoService#getAll()}
   */
  @Test
  void testGetAll_unauthorized() {
    SecurityContextHolder.clearContext();
    UnauthorizedException e = assertThrows(UnauthorizedException.class, () -> todoService.getAll());

    assertEquals(UnauthorizedException.UNAUTHORIZED, e.getMessage());
  }

  /**
   * Test for {@link TodoService#getAll()}
   */
  @Test
  void testGetAll_empty() {
    List<Todo> result = todoService.getAll();

    assertTrue(result.isEmpty());
  }

  /**
   * Test for {@link TodoService#getAll()}
   */
  @Test
  void testGetAll_ok() {
    list = todoListRepository.save(Samples.createSampleTodoList(SUB));
    todo = todoRepository.save(Samples.createSampleTodo(list.getTodoListId(), SUB));

    List<Todo> result = todoService.getAll();
    assertEquals(1, result.size());

    list = todoListRepository.findById(list.getTodoListId()).orElseThrow();

    assertTrue(list.getTodos().contains(todo));
  }

  /**
   * Test for {@link TodoService#getAll()}
   */
  @Test
  void testDelete_ok() {
    list = todoListRepository.save(Samples.createSampleTodoList(SUB));
    todo = todoService.store(Samples.createSampleTodo(list.getTodoListId(), SUB));

    todoService.delete(todo.getTodoId());

    TodoList returned = todoListRepository.findAll().get(0);
    assertTrue(returned.getTodos().isEmpty());
  }

  /**
   * Test for {@link TodoService#find(Long)}
   */
  @Test
  void testFind_notFound() {
    assertThrows(NotFoundException.class, () -> todoService.find(-1L));
  }

  /**
   * Test for {@link TodoService#find(Long)}
   */
  @Test
  void testFind_ok() {
    list = todoListRepository.save(Samples.createSampleTodoList(SUB));
    todo = todoService.store(Samples.createSampleTodo(list.getTodoListId(), SUB));

    assertEquals(todo, todoService.find(todo.getTodoId()));
  }

  /**
   * Test for {@link TodoService#store(Todo)}
   */
  @Test
  void testStore_alreadyExists() {
    Todo todo = Samples.createSampleTodo(1L, "Sub");
    todo.setTodoId(1L);
    assertThrows(LogicException.class, () -> todoService.store(todo));
  }

  /**
   * Test for {@link TodoService#store(Todo)}
   */
  @Test
  void testStore_ok() {
    list = todoListRepository.save(Samples.createSampleTodoList(SUB));
    todo = todoService.store(Samples.createSampleTodo(list.getTodoListId(), SUB));
    list = todoListRepository.findAll().get(0);

    assertTrue(list.getTodos().contains(todo));
  }

  /**
   * Test for {@link TodoService#update(Todo)}
   */
  @Test
  void testUpdate_doesNotExist() {
    Todo todo = Samples.createSampleTodo(1L, "Sub");
    assertThrows(LogicException.class, () -> todoService.update(todo));
  }

  /**
   * Test for {@link TodoService#update(Todo)} (Todo)}
   */
  @Test
  void testUpdate_ok() {
    list = todoListRepository.save(Samples.createSampleTodoList(SUB));
    todo = todoService.store(Samples.createSampleTodo(list.getTodoListId(), SUB));

    todo.setTitle("LALALA");
    todo = todoService.update(todo);

    assertEquals("LALALA", todo.getTitle());
  }

}