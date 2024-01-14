package gr.evansp.todofullstackappbackend.repositories.todos;

import gr.evansp.todofullstackappbackend.models.todos.Todo;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.samples.Samples;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Integration test for {@link TodoListRepository}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class TestTodoListRepository {

  private static final Long userId = 1L;
  private final List<TodoList> todoLists = new ArrayList<>();
  @Autowired
  TodoListRepository repository;

  @AfterEach
  public void cleanup() {
    for (TodoList todoList : todoLists) {
      repository.delete(todoList);
    }
  }

  @Test
  void testSaveEmptyList() {
    TodoList todoList = repository.save(Samples.createSampleTodoList(userId));
    assertNotNull(todoList);
    assertTrue(todoList.getTodoListId() > 0);

    todoLists.add(todoList);
  }

  @Test
  void testFindByUserId() {
    TodoList todoList = repository.save(Samples.createSampleTodoList(userId));
    todoLists.add(todoList);

    List<TodoList> lists = repository.findByUserId(userId);
    assertEquals(1, lists.size());
  }

  @Test
  void testSaveFullList() {
    TodoList todoList = Samples.createSampleTodoList(userId);
    todoList = repository.save(todoList);

    Todo todo = Samples.createSampleTodo(todoList.getTodoListId());

    todoList.getTodos().add(todo);

    repository.save(todoList);

    assertNotNull(todoList);
    assertTrue(todoList.getTodoListId() > 0);

    todoLists.add(todoList);
  }
}