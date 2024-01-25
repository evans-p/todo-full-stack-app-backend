package gr.evansp.todofullstackappbackend.services.todos;

import gr.evansp.todofullstackappbackend.exceptions.LogicException;
import gr.evansp.todofullstackappbackend.exceptions.NotFoundException;
import gr.evansp.todofullstackappbackend.exceptions.UnauthorizedException;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.repositories.todos.TodoListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of {@link TodoListService}.
 */
@SuppressWarnings("unused")
@Service
@Validated
public class TodoListServiceImpl implements TodoListService {

  /**
   * {@link TodoListRepository}
   */
  TodoListRepository todoListRepository;

  /**
   * {@link VerifyOwnershipService}
   */
  VerifyOwnershipService ownershipService;

  @Autowired
  public TodoListServiceImpl(TodoListRepository todoListRepository, VerifyOwnershipService ownershipService) {
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
  public TodoList find(Long id) {
    TodoList list = todoListRepository.findById(id).orElseThrow(
        () -> new NotFoundException(NotFoundException.LIST_NOT_FOUND, null));
    ownershipService.checkOwnership(list.getUserId());
    return list;
  }

  @Override
  @Transactional
  public TodoList store(TodoList todoList) {
    if (todoList.getTodoListId() != null) {
      throw new LogicException(LogicException.ALREADY_EXISTS, new Object[]{todoList.getTitle()});
    }
    ownershipService.checkOwnership(todoList.getUserId());
    return todoListRepository.save(todoList);
  }

  @Override
  @Transactional
  public TodoList update(TodoList todoList) {
    if (todoList.getTodoListId() == null) {
      throw new LogicException(LogicException.DOES_NOT_EXIST, new Object[]{todoList.getTitle()});
    }
    ownershipService.checkOwnership(find(todoList.getTodoListId()).getUserId());
    ownershipService.checkOwnership(todoList.getUserId());

    todoList.setLastModified(LocalDateTime.now());
    return todoListRepository.save(todoList);
  }

  @Override
  @Transactional
  public void delete(TodoList todoList) {
    ownershipService.checkOwnership(find(todoList.getTodoListId()).getUserId());
    todoListRepository.delete(todoList);
  }
}
