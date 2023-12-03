package gr.evansp.todofullstackappbackend.controllers.todos;

import gr.evansp.todofullstackappbackend.TodoFullStackAppBackendApplication;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.models.users.User;
import gr.evansp.todofullstackappbackend.repositories.users.UserRepository;
import gr.evansp.todofullstackappbackend.samples.Samples;
import gr.evansp.todofullstackappbackend.services.todos.TodoListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for {@link TodoListController}.
 */
@SpringBootTest(classes = TodoFullStackAppBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestTodoListController {

  @Autowired
  MessageSource messageSource;

  @Autowired
  UserRepository userRepository;

  @Autowired
  TodoListService todoListService;

  @Autowired
  TestRestTemplate restTemplate;

  /**
   * Test for {@link TodoListController#read(Long)} not found.
   */
  @Test
  void testRead_notFound() {
    ResponseEntity<String> response = restTemplate.getForEntity("/todo-lists/-1", String.class);
    String result = String.format("{\"message\":\"%s\",\"messages\":null}",
        messageSource.getMessage("list.not.found", null, Locale.getDefault()));
    assertEquals(result, response.getBody());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  /**
   * Test for {@link TodoListController#read(Long)} ok.
   */
  @Test
  void testRead_ok() {
    User user = userRepository.save(Samples.createSampleUser());
    TodoList list = todoListService.store(Samples.createSampleTodoList(user.getUserId()));

    ResponseEntity<String> response = restTemplate.getForEntity(
        String.format("/todo-lists/%d", list.getTodoListId()), String.class);

    assertNotNull(response.getBody());
    assertTrue(response.getBody().contains(String.format("/todo-lists/%d", list.getTodoListId())));
    assertTrue(response.getBody().contains(list.getTitle()));

    userRepository.delete(user);
    todoListService.delete(list);
  }

  /**
   * Test for {@link TodoListController#create(TodoList)} ok.
   */
  @Test
  void testCreate_ok() {
    User user = userRepository.save(Samples.createSampleUser());
    TodoList list = todoListService.store(Samples.createSampleTodoList(user.getUserId()));

    ResponseEntity<String> response = restTemplate.postForEntity("/todo-lists/", list, String.class);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());

    userRepository.delete(user);
    todoListService.delete(list);
  }
}