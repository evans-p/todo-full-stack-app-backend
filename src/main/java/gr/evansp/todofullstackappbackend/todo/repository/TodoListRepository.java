package gr.evansp.todofullstackappbackend.todo.repository;

import gr.evansp.todofullstackappbackend.todo.models.TodoList;
import gr.evansp.todofullstackappbackend.todo.models.TodoListPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link TodoList}.
 */
@Repository
public interface TodoListRepository extends JpaRepository<TodoList, TodoListPK> {
  //EMPTY
}
