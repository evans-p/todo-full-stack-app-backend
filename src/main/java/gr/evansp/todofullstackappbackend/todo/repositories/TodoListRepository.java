package gr.evansp.todofullstackappbackend.todo.repositories;

import gr.evansp.todofullstackappbackend.todo.models.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link TodoList}.
 */
@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
  //EMPTY
}
