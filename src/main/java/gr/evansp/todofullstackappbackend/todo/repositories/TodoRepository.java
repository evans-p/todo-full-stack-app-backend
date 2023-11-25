package gr.evansp.todofullstackappbackend.todo.repositories;

import gr.evansp.todofullstackappbackend.todo.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Todo1.
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
  //EMPTY
}
