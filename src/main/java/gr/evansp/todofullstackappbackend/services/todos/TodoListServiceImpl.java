package gr.evansp.todofullstackappbackend.services.todos;

import gr.evansp.todofullstackappbackend.common.exceptions.ListNotFoundException;
import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import gr.evansp.todofullstackappbackend.repositories.todos.TodoListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link TodoListService}.
 */
@SuppressWarnings("unused")
@Service
public class TodoListServiceImpl implements TodoListService {

  TodoListRepository todoListRepository;

  @Autowired
  public TodoListServiceImpl(TodoListRepository todoListRepository) {
    this.todoListRepository = todoListRepository;
  }

  @Override
  @Transactional
  public TodoList find(Long id) throws ListNotFoundException {
    //TODO Add custom exception
    return todoListRepository.findById(id).orElseThrow(() -> new ListNotFoundException(""));
  }

  @Override
  @Transactional
  public TodoList store(TodoList todoList) {
    return todoListRepository.save(todoList);
  }

  @Override
  @Transactional
  public TodoList update(TodoList todoList) {
    return todoListRepository.save(todoList);
  }

  @Override
  @Transactional
  public void delete(TodoList todoList) {
    todoListRepository.delete(todoList);
  }
}
