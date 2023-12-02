package gr.evansp.todofullstackappbackend.services.todos;

import gr.evansp.todofullstackappbackend.common.exceptions.NotFoundException;
import gr.evansp.todofullstackappbackend.common.exceptions.messages.ExceptionMessages;
import gr.evansp.todofullstackappbackend.models.todos.Todo;
import gr.evansp.todofullstackappbackend.repositories.todos.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Implementation of {@link TodoService}.
 */
@SuppressWarnings("unused")
@Service
@Validated
public class TodoServiceImpl implements TodoService {

  TodoRepository todoRepository;

  public TodoServiceImpl(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @Override
  @Transactional
  public Todo find(Long id) {
    return todoRepository.findById(id).orElseThrow(
        () -> new NotFoundException(ExceptionMessages.TODO_NOT_FOUND, null)
    );
  }

  @Override
  @Transactional
  public Todo store(Todo todo) {
    return todoRepository.save(todo);
  }

  @Override
  @Transactional
  public Todo update(Todo todo) {
    return todoRepository.save(todo);
  }

  @Override
  @Transactional
  public void delete(Todo todo) {
    todoRepository.delete(todo);
  }
}
