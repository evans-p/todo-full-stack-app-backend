package gr.evansp.todofullstackappbackend.todo.repository;

import gr.evansp.todofullstackappbackend.todo.models.Todo;
import gr.evansp.todofullstackappbackend.todo.models.TodoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Todo1.
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, TodoPK> {
  //EMPTY
}
