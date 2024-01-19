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

  TodoListRepository todoListRepository;

  @Autowired
  public TodoListServiceImpl(TodoListRepository todoListRepository) {
    this.todoListRepository = todoListRepository;
  }

  @Override
  @Transactional
  public List<TodoList> getAll() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      throw new UnauthorizedException(UnauthorizedException.UNAUTHORIZED, null);
    }

    return todoListRepository.findByUserId(Long.parseLong(authentication.getName()));
  }

  @Override
  @Transactional
  public TodoList find(Long id) {
    return todoListRepository.findById(id).orElseThrow(
        () -> new NotFoundException(NotFoundException.LIST_NOT_FOUND, null));
  }

  @Override
  @Transactional
  public TodoList store(TodoList todoList) {
    if (todoList.getTodoListId() != null) {
      throw new LogicException(LogicException.ALREADY_EXISTS, null);
    }
    checkOwnership(todoList.getUserId());
    return todoListRepository.save(todoList);
  }

  @Override
  @Transactional
  public TodoList update(TodoList todoList) {

    if (todoList.getTodoListId() == null) {
      throw new LogicException(LogicException.DOES_NOT_EXIST, null);
    }
    checkOwnership(todoList.getUserId());

    todoList.setLastModified(LocalDateTime.now());
    return todoListRepository.save(todoList);
  }

  @Override
  @Transactional
  public void delete(TodoList todoList) {
    todoListRepository.delete(todoList);
  }

  private void checkOwnership(Long id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      throw new UnauthorizedException(UnauthorizedException.UNAUTHORIZED, null);
    }

    if (Long.parseLong(authentication.getName()) != id) {
      throw new UnauthorizedException(UnauthorizedException.DIFFERENT_OWNER, null);
    }
  }
}
