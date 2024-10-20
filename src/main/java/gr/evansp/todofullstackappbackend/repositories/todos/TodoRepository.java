package gr.evansp.todofullstackappbackend.repositories.todos;

import gr.evansp.todofullstackappbackend.models.todos.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Repository for Todo1. */
@SuppressWarnings("unused")
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
  /**
   * Finds all Todo1 of the currently validated user.
   *
   * @param userId userId
   * @return List of Todos
   */
  List<Todo> findByUserId(String userId);
}
