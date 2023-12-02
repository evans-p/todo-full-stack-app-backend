package gr.evansp.todofullstackappbackend.repositories.todos;

import gr.evansp.todofullstackappbackend.models.todos.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link TodoList}.
 */
@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
  //EMPTY
}
