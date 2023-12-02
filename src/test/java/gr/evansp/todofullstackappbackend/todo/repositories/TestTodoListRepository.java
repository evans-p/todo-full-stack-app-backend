package gr.evansp.todofullstackappbackend.todo.repositories;

import gr.evansp.todofullstackappbackend.models.todos.Todo;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.models.users.User;
import gr.evansp.todofullstackappbackend.repositories.todos.TodoListRepository;
import gr.evansp.todofullstackappbackend.repositories.users.UserRepository;
import gr.evansp.todofullstackappbackend.samples.Samples;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Integration test for {@link TodoListRepository}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class TestTodoListRepository {

  private final List<TodoList> todoLists = new ArrayList<>();
  @Autowired
  TodoListRepository repository;
  @Autowired
  UserRepository userRepository;
  private User user;

  @BeforeEach
  public void setup() {
    if (user == null) {
      user = userRepository.save(Samples.createSampleUser());
    }
  }

  @AfterEach
  public void cleanup() {
    for (TodoList todoList : todoLists) {
      repository.delete(todoList);
    }
    userRepository.delete(user);
  }

  @Test
  void testSaveEmptyList() {
    TodoList todoList = repository.save(Samples.createSampleTodoList(user.getUserId()));
    assertNotNull(todoList);
    assertTrue(todoList.getTodoListId() > 0);

    todoLists.add(todoList);
  }

  @Test
  void testSaveFullList() {
    TodoList todoList = Samples.createSampleTodoList(user.getUserId());
    todoList = repository.save(todoList);

    Todo todo = Samples.createSampleTodo(todoList.getTodoListId());

    todoList.getTodos().add(todo);

    repository.save(todoList);

    assertNotNull(todoList);
    assertTrue(todoList.getTodoListId() > 0);

    todoLists.add(todoList);
  }

}