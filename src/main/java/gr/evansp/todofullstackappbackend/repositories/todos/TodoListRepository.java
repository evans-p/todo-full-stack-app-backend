package gr.evansp.todofullstackappbackend.repositories.todos;

import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for {@link TodoList}.
 */
@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
  /**
   * Finds all {@link TodoList} of the currently validated user.
   *
   * @param userId userId
   * @return List
   */
  List<TodoList> findByUserId(String userId);
}
