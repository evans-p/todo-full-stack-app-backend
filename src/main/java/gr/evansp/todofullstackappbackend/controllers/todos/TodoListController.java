package gr.evansp.todofullstackappbackend.controllers.todos;

import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.services.todos.TodoListService;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SuppressWarnings("unused")
@RestController
@RequestMapping("todo-list")
public class TodoListController {

  private final TodoListService service;

  public TodoListController(TodoListService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public EntityModel<TodoList> read(@PathVariable Long id) {
    TodoList list = service.find(id);
    return EntityModel.of(list, linkTo(methodOn(TodoListController.class).read(id)).withSelfRel());
  }

  @PutMapping("/")
  public TodoList update(@RequestBody TodoList todoList) {
    return service.update(todoList);
  }

  @PostMapping("/")
  public TodoList create(@RequestBody TodoList todoList) {
    return service.store(todoList);
  }

  @DeleteMapping("/")
  public void delete(@RequestBody TodoList todoList) {
    service.delete(todoList);
  }
}
