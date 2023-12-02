package gr.evansp.todofullstackappbackend.services.todos;

import gr.evansp.todofullstackappbackend.common.exceptions.NotFoundException;
import gr.evansp.todofullstackappbackend.common.exceptions.messages.ExceptionMessages;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.repositories.todos.TodoListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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
  public TodoList find(Long id) {
    return todoListRepository.findById(id).orElseThrow(
        () -> new NotFoundException(ExceptionMessages.LIST_NOT_FOUND, null));
  }

  @Override
  @Transactional
  public TodoList store(TodoList todoList) {
    //TODO: add check to make sure to todoList == null
    return todoListRepository.save(todoList);
  }

  @Override
  @Transactional
  public TodoList update(TodoList todoList) {
    //TODO: add check to make sure to todoList != null
    return todoListRepository.save(todoList);
  }

  @Override
  @Transactional
  public void delete(TodoList todoList) {
    todoListRepository.delete(todoList);
  }
}
