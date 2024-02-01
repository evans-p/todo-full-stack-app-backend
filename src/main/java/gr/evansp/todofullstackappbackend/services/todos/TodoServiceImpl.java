package gr.evansp.todofullstackappbackend.services.todos;

import gr.evansp.todofullstackappbackend.exceptions.LogicException;
import gr.evansp.todofullstackappbackend.exceptions.NotFoundException;
import gr.evansp.todofullstackappbackend.exceptions.UnauthorizedException;
import gr.evansp.todofullstackappbackend.models.todos.Todo;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.repositories.todos.TodoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of {@link TodoService}.
 */
@SuppressWarnings("unused")
@Service
@Validated
public class TodoServiceImpl implements TodoService {

  /**
   * {@link TodoRepository}.
   */
  TodoRepository todoRepository;

  /**
   * {@link TodoListService}
   */
  TodoListService todoListService;

  /**
   * {@link VerifyOwnershipService}
   */
  VerifyOwnershipService ownershipService;

  @Autowired
  public TodoServiceImpl(TodoRepository todoRepository, VerifyOwnershipService ownershipService,
                         TodoListService todoListService) {
    this.todoRepository = todoRepository;
    this.ownershipService = ownershipService;
    this.todoListService = todoListService;
  }

  @Override
  @Transactional
  public Todo find(@NotNull(message = "{id.null}") Long id) {
    Todo todo = todoRepository.findById(id).orElseThrow(
        () -> new NotFoundException(NotFoundException.TODO_NOT_FOUND, null));
    ownershipService.checkOwnership(todo.getUserId());
    return todo;
  }

  @Override
  @Transactional
  public Todo store(@NotNull(message = "{todo.null}") @Valid Todo todo) {
    if (todo.getTodoId() != null) {
      throw new LogicException(LogicException.ALREADY_EXISTS, new Object[]{todo.getTitle()});
    }
    ownershipService.checkOwnership(todo.getUserId());
    TodoList list = todoListService.find(todo.getTodoListId());
    ownershipService.checkOwnership(list.getUserId());

    return todoRepository.save(todo);
  }

  @Override
  @Transactional
  public Todo update(@NotNull(message = "{todo.null}") @Valid Todo todo) {
    if (todo.getTodoId() == null) {
      throw new LogicException(LogicException.DOES_NOT_EXIST, new Object[]{todo.getTitle()});
    }
    TodoList list = todoListService.find(todo.getTodoListId());
    ownershipService.checkOwnership(list.getUserId());
    ownershipService.checkOwnership(find(todo.getTodoId()).getUserId());
    ownershipService.checkOwnership(todo.getUserId());

    todo.setLastModified(LocalDateTime.now());
    return todoRepository.save(todo);
  }

  @Override
  @Transactional
  public void delete(@NotNull(message = "{id.null}") Long id) {
    Todo todo = find(id);
    ownershipService.checkOwnership(find(id).getUserId());
    todoRepository.delete(todo);
  }

  @Override
  @Transactional
  public List<Todo> getAll() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      throw new UnauthorizedException(UnauthorizedException.UNAUTHORIZED, null);
    }
    return todoRepository.findByUserId(authentication.getName());
  }
}
