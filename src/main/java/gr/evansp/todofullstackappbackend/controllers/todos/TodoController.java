package gr.evansp.todofullstackappbackend.controllers.todos;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import gr.evansp.todofullstackappbackend.models.todos.Todo;
import gr.evansp.todofullstackappbackend.services.todos.TodoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** REST controller for {@link Todo}. */
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@RestController
@RequestMapping("todos")
public class TodoController {

  /** {@link TodoService}. */
  private final TodoService service;

  @Autowired
  public TodoController(final TodoService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public EntityModel<Todo> read(@PathVariable Long id) {
    Todo list = service.find(id);
    return EntityModel.of(list, linkTo(methodOn(TodoController.class).read(id)).withSelfRel());
  }

  /**
   * Read all Todos associated with the current loggedIn user.
   *
   * @return {@link Todo}.
   */
  @GetMapping("/")
  public CollectionModel<EntityModel<Todo>> readAll() {
    List<EntityModel<Todo>> todos =
        service.getAll().stream()
            .map(
                todo ->
                    EntityModel.of(
                        todo,
                        linkTo(methodOn(TodoController.class).read(todo.getTodoId())).withSelfRel(),
                        linkTo(methodOn(TodoController.class).readAll()).withRel("todos")))
            .toList();

    return CollectionModel.of(
        todos, linkTo(methodOn(TodoController.class).readAll()).withSelfRel());
  }

  /**
   * Creates new {@link Todo}.
   *
   * @param todo {@link Todo} to be stored.
   * @return newly created {@link Todo}.
   */
  @PostMapping("/")
  public EntityModel<Todo> create(@RequestBody Todo todo) {
    Todo todo1 = service.store(todo);
    return EntityModel.of(
        todo1, linkTo(methodOn(TodoController.class).read(todo1.getTodoListId())).withSelfRel());
  }

  /**
   * Updates {@link Todo}.
   *
   * @param todo {@link Todo} to be updated.
   * @return newly updated {@link Todo}.
   */
  @PutMapping("/")
  public EntityModel<Todo> update(@RequestBody Todo todo) {
    Todo todo1 = service.update(todo);
    return EntityModel.of(
        todo1,
        linkTo(methodOn(TodoListController.class).read(todo1.getTodoListId())).withSelfRel());
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
