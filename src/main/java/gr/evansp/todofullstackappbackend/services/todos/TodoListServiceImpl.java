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
import java.util.Objects;

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

    return todoListRepository.findByUserId(authentication.getName());
  }

  @Override
  @Transactional
  public TodoList find(Long id) {
    TodoList list = todoListRepository.findById(id).orElseThrow(
        () -> new NotFoundException(NotFoundException.LIST_NOT_FOUND, null));
    checkOwnership(list.getUserId());
    return list;
  }

  @Override
  @Transactional
  public TodoList store(TodoList todoList) {
    if (todoList.getTodoListId() != null) {
      throw new LogicException(LogicException.ALREADY_EXISTS, new Object[]{todoList.getTitle()});
    }
    checkOwnership(todoList.getUserId());
    return todoListRepository.save(todoList);
  }

  @Override
  @Transactional
  public TodoList update(TodoList todoList) {
    if (todoList.getTodoListId() == null) {
      throw new LogicException(LogicException.DOES_NOT_EXIST, new Object[]{todoList.getTitle()});
    }
    checkOwnership(find(todoList.getTodoListId()).getUserId());
    checkOwnership(todoList.getUserId());

    todoList.setLastModified(LocalDateTime.now());
    return todoListRepository.save(todoList);
  }

  @Override
  @Transactional
  public void delete(TodoList todoList) {
    checkOwnership(find(todoList.getTodoListId()).getUserId());
    todoListRepository.delete(todoList);
  }

  private void checkOwnership(String id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !Objects.equals(authentication.getName(), id)) {
      throw new UnauthorizedException(UnauthorizedException.UNAUTHORIZED, null);
    }
  }
}
