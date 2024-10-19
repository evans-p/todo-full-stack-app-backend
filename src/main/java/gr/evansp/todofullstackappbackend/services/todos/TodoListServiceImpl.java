package gr.evansp.todofullstackappbackend.services.todos;

import gr.evansp.todofullstackappbackend.exceptions.LogicException;
import gr.evansp.todofullstackappbackend.exceptions.NotFoundException;
import gr.evansp.todofullstackappbackend.exceptions.UnauthorizedException;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.repositories.todos.TodoListRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/** Implementation of {@link TodoListService}. */
@SuppressWarnings("unused")
@Service
@Validated
public class TodoListServiceImpl implements TodoListService {

  /** {@link TodoListRepository}. */
  TodoListRepository todoListRepository;

  /** {@link VerifyOwnershipService}. */
  VerifyOwnershipService ownershipService;

  @Autowired
  public TodoListServiceImpl(
      TodoListRepository todoListRepository, VerifyOwnershipService ownershipService) {
    this.todoListRepository = todoListRepository;
    this.ownershipService = ownershipService;
  }

  @Override
  @Transactional
  public List<TodoList> getAll() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      throw new UnauthorizedException(UnauthorizedException.UNAUTHORIZED, null);
    }

    return todoListRepository.findByUserId(authentication.getName());
  }

  @Override
  @Transactional
  public TodoList find(@NotNull(message = "{id.null}") Long id) {
    TodoList list =
        todoListRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(NotFoundException.LIST_NOT_FOUND, null));
    ownershipService.checkOwnership(list.getUserId());
    return list;
  }

  @Override
  @Transactional
  public TodoList store(@NotNull(message = "{todo.list.null}") @Valid TodoList todoList) {
    if (todoList.getTodoListId() != null) {
      throw new LogicException(LogicException.ALREADY_EXISTS, new Object[] {todoList.getTitle()});
    }
    ownershipService.checkOwnership(todoList.getUserId());
    return todoListRepository.save(todoList);
  }

  @Override
  @Transactional
  public TodoList update(@NotNull(message = "{todo.list.null}") @Valid TodoList todoList) {
    if (todoList.getTodoListId() == null) {
      throw new LogicException(LogicException.DOES_NOT_EXIST, new Object[] {todoList.getTitle()});
    }
    TodoList retrieved = find(todoList.getTodoListId());

    ownershipService.checkOwnership(todoList.getUserId());

    todoList.setCreated(retrieved.getCreated());
    todoList.setLastModified(LocalDateTime.now());
    return todoListRepository.save(todoList);
  }

  @Override
  @Transactional
  public void delete(@NotNull(message = "{id.null}") Long id) {
    TodoList list = find(id);
    todoListRepository.delete(list);
  }
}
