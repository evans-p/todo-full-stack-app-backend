package gr.evansp.todofullstackappbackend.controllers.todos;

import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.services.todos.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * REST controller for {@link TodoList}.
 */
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@RestController
@RequestMapping("todo-lists")
public class TodoListController {
  /**
   * {@link TodoListService}.
   */
  private final TodoListService service;

  @Autowired
  public TodoListController(TodoListService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public EntityModel<TodoList> read(@PathVariable Long id) {
    TodoList list = service.find(id);
    return EntityModel.of(list, linkTo(methodOn(TodoListController.class).read(id)).withSelfRel());
  }

  @GetMapping("/")
  public CollectionModel<EntityModel<TodoList>> readAll() {
    List<EntityModel<TodoList>> lists = service.getAll().stream()
        .map(list -> EntityModel.of(list,
            linkTo(methodOn(TodoListController.class).read(list.getTodoListId())).withSelfRel(),
            linkTo(methodOn(TodoListController.class).readAll()).withRel("todoLists")))
        .toList();

    return CollectionModel.of(lists,
        linkTo(methodOn(TodoListController.class).readAll()).withSelfRel());
  }

  @PostMapping("/")
  public EntityModel<TodoList> create(@RequestBody TodoList todoList) {
    TodoList list = service.store(todoList);
    return EntityModel.of(list, linkTo(methodOn(TodoListController.class).read(list.getTodoListId())).withSelfRel());
  }

  @PutMapping("/")
  public EntityModel<TodoList> update(@RequestBody TodoList todoList) {
    TodoList list = service.update(todoList);
    return EntityModel.of(list, linkTo(methodOn(TodoListController.class).read(list.getTodoListId())).withSelfRel());
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
