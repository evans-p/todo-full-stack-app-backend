package gr.evansp.todofullstackappbackend.todo.repository;

import gr.evansp.todofullstackappbackend.samples.Samples;
import gr.evansp.todofullstackappbackend.todo.models.Todo;
import gr.evansp.todofullstackappbackend.todo.models.TodoList;
import gr.evansp.todofullstackappbackend.user.models.User;
import gr.evansp.todofullstackappbackend.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration tests for {@link TodoRepository}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class TestTodoRepository {

  private final List<Todo> todos = new ArrayList<>();
  @Autowired
  TodoRepository repository;
  @Autowired
  TodoListRepository todoListRepository;
  @Autowired
  UserRepository userRepository;
  private User user;

  private TodoList todoList;

  @BeforeEach
  public void setup() {
    if (user == null) {
      user = userRepository.save(Samples.createSampleUser());
    }

    if (todoList == null) {
      todoList = todoListRepository.save(Samples.createSampleTodoList(user.getUserId()));
    }
  }

  @AfterEach
  public void cleanup() {
    for (Todo todo : todos) {
      repository.delete(todo);
    }
    todoListRepository.delete(todoList);
    userRepository.delete(user);
  }

  @Test
  void testSave() {
    Todo todo = repository.save(Samples.createSampleTodo(todoList.getTodoListId()));
    assertNotNull(todo);

    assertTrue(todo.getTodoId() > 0);
    todos.add(todo);
  }

  @Test
  void testDelete() {
    Todo todo;
    if (todos.isEmpty()) {
      todo = repository.save(Samples.createSampleTodo(todoList.getTodoListId()));
      todos.add(todo);
    } else {
      todo = todos.get(todos.size() - 1);
    }

    repository.delete(todo);
    Todo removed = todos.remove(todos.size() - 1);

    assertNull(repository.findById(removed.getTodoId()).orElse(null));
  }
}