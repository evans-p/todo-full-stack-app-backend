package gr.evansp.todofullstackappbackend.repositories.todos;

import gr.evansp.todofullstackappbackend.models.todos.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Todo1.
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
  //EMPTY
}
