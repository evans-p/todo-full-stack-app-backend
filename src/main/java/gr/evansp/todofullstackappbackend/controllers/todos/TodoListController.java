package gr.evansp.todofullstackappbackend.controllers.todos;

import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.services.todos.TodoListService;
import org.springframework.hateoas.EntityModel;
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
  public EntityModel<TodoList> read(@PathVariable Long id) {
    TodoList list = service.find(id);
    return EntityModel.of(list, linkTo(methodOn(TodoListController.class).read(id)).withSelfRel());
  }

  @PutMapping("/")
  public EntityModel<TodoList> update(@RequestBody TodoList todoList) {
    TodoList list = service.update(todoList);
    return EntityModel.of(list, linkTo(methodOn(TodoListController.class).read(list.getTodoListId())).withSelfRel());
  }

  @PostMapping("/")
  public EntityModel<TodoList> create(@RequestBody TodoList todoList) {
    TodoList list = service.store(todoList);
    return EntityModel.of(list, linkTo(methodOn(TodoListController.class).read(list.getTodoListId())).withSelfRel());
  }

  @DeleteMapping("/")
  public void delete(@RequestBody TodoList todoList) {
    service.delete(todoList);
  }
}
