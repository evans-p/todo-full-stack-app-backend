package gr.evansp.todofullstackappbackend.repositories.todos;

import gr.evansp.todofullstackappbackend.base.BaseITTest;
import gr.evansp.todofullstackappbackend.models.todos.Todo;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.samples.Samples;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link TodoRepository}
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class TestTodoRepositoryIT extends BaseITTest {


  /**
   * User ID.
   */
  public static final String userId = "1";

  /**
   * Subject under test.
   */
  @Autowired
  TodoRepository repository;

  /**
   * Subject under test.
   */
  @Autowired
  TodoListRepository listRepository;


  /**
   * Todo1.
   */
  Todo todo;

  /**
   * TodoList.
   */
  TodoList list;

  /**
   * Cleanup.
   */
  @AfterEach
  void cleanup() {
    if (list != null) {
      listRepository.delete(list);
    }

    if (todo != null) {
      repository.delete(todo);
    }
  }

  /**
   * test for {@link TodoRepository#findById(Object)}.
   */
  @Test
  void testFindById_notFound() {
    Optional<Todo> result = repository.findById(0L);
    assertNull(result.orElse(null));
  }

  /**
   * test for {@link TodoRepository#findById(Object)}.
   */
  @Test
  void testFindById_ok() {
    list = listRepository.save(Samples.createSampleTodoList(userId));
    todo = repository.save(Samples.createSampleTodo(list.getTodoListId(), userId));

    Optional<Todo> result = repository.findById(todo.getTodoId());
    assertTrue(result.isPresent());
  }

  /**
   * test for {@link TodoRepository#save(Object)}.
   */
  @Test
  void testSave_ok() {
    list = listRepository.save(Samples.createSampleTodoList(userId));
    todo = repository.save(Samples.createSampleTodo(list.getTodoListId(), userId));

    Optional<Todo> result = repository.findById(todo.getTodoId());
    assertTrue(result.isPresent());
  }

  /**
   * test for {@link TodoRepository#delete(Object)}.
   */
  @Test
  void testDelete_ok() {
    list = listRepository.save(Samples.createSampleTodoList(userId));
    todo = repository.save(Samples.createSampleTodo(list.getTodoListId(), userId));

    repository.delete(todo);
    assertTrue(repository.findById(todo.getTodoId()).isEmpty());
  }

  /**
   * test for {@link TodoRepository#findByUserId(String)}.
   */
  @Test
  void testFindByUserId_ok() {
    list = listRepository.save(Samples.createSampleTodoList(userId));
    todo = repository.save(Samples.createSampleTodo(list.getTodoListId(), userId));

    List<Todo> result = repository.findByUserId(userId);
    assertEquals(1, result.size());
  }
}