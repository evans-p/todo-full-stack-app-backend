package gr.evansp.todofullstackappbackend.todo.services;

import gr.evansp.todofullstackappbackend.todo.models.TodoList;
import gr.evansp.todofullstackappbackend.todo.repositories.TodoListRepository;
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
  public TodoList find(Long id) {
    //TODO Add custom exception
    return todoListRepository.findById(id).orElseThrow(RuntimeException::new);
  }

  @Override
  @Transactional
  public TodoList store(TodoList todoList) {
    return null;
  }

  @Override
  @Transactional
  public TodoList update(TodoList todoList) {
    return null;
  }

  @Override
  @Transactional
  public void delete(TodoList todoList) {
    //EMPTY
  }
}
