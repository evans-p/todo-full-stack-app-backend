package gr.evansp.todofullstackappbackend.controllers.todos;

import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.services.todos.TodoListService;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SuppressWarnings("unused")
@RestController
@RequestMapping("todo-lists")
public class TodoListController {

  private final TodoListService service;

  public TodoListController(TodoListService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public EntityModel<TodoList> read(@PathVariable Long id, @AuthenticationPrincipal(errorOnInvalidType = true) Jwt user) {
    TodoList list = service.find(id);
    System.out.println(user);
    return EntityModel.of(list, linkTo(methodOn(TodoListController.class).read(id, user)).withSelfRel());
  }

  @PostMapping("/")
  public EntityModel<TodoList> create(@RequestBody TodoList todoList, @AuthenticationPrincipal Jwt user) {
    TodoList list = service.store(todoList);
    System.out.println(user);
    return EntityModel.of(list, linkTo(methodOn(TodoListController.class).read(list.getTodoListId(), user)).withSelfRel());
  }

  @PutMapping("/")
  public EntityModel<TodoList> update(@RequestBody TodoList todoList, @AuthenticationPrincipal Jwt user) {
    TodoList list = service.update(todoList);
    System.out.println(user);
    return EntityModel.of(list, linkTo(methodOn(TodoListController.class).read(list.getTodoListId(), user)).withSelfRel());
  }

  @DeleteMapping("/")
  public void delete(@RequestBody TodoList todoList, @AuthenticationPrincipal Jwt user) {
    service.delete(todoList);
  }
}
