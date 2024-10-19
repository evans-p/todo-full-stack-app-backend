package gr.evansp.todofullstackappbackend.repositories.todos;

import static org.junit.Assert.*;

import gr.evansp.todofullstackappbackend.base.BaseITTest;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.samples.Samples;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/** Integration tests for {@link TodoListRepository}. */
@RunWith(SpringRunner.class)
@SpringBootTest
class TestTodoListRepositoryIT extends BaseITTest {

  /** User ID. */
  public static final String userId = "1";

  /** Subject under test. */
  @Autowired TodoListRepository repository;

  /** TodoList. */
  TodoList list;

  /** Cleanup. */
  @AfterEach
  void cleanup() {
    if (list != null) {
      repository.delete(list);
    }
  }

  /** test for {@link TodoListRepository#findById(Object)}. */
  @Test
  void testFindById_notFound() {
    Optional<TodoList> result = repository.findById(0L);
    assertNull(result.orElse(null));
  }

  /** test for {@link TodoListRepository#findById(Object)}. */
  @Test
  void testFindById_ok() {
    list = repository.save(Samples.createSampleTodoList(userId));

    Optional<TodoList> result = repository.findById(list.getTodoListId());
    assertTrue(result.isPresent());
  }

  /** test for {@link TodoListRepository#findByUserId(String)}. */
  @Test
  void testFindByUserId_Empty() {
    List<TodoList> result = repository.findByUserId(userId);
    assertTrue(result.isEmpty());
  }

  /** test for {@link TodoListRepository#findByUserId(String)}. */
  @Test
  void testFindByUserId_ok() {
    list = repository.save(Samples.createSampleTodoList(userId));

    List<TodoList> result = repository.findByUserId(userId);
    assertEquals(1, result.size());
  }

  /** test for {@link TodoListRepository#save(Object)}. */
  @Test
  void testSave_ok() {
    list = repository.save(Samples.createSampleTodoList(userId));

    Optional<TodoList> result = repository.findById(list.getTodoListId());
    assertTrue(result.isPresent());
  }

  /** test for {@link TodoListRepository#delete(Object)}. */
  @Test
  void testDelete_ok() {
    list = repository.save(Samples.createSampleTodoList(userId));

    repository.delete(list);
    assertTrue(repository.findById(list.getTodoListId()).isEmpty());
  }
}
