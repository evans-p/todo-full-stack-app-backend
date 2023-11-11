package gr.evansp.todofullstackappbackend.todo.impl.models;

import gr.evansp.todofullstackappbackend.todo.def.models.TodoListPK;
import lombok.*;

/**
 * Implementation of {@link TodoListPK}.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TodoListPKImpl implements TodoListPK {
  private Long userId;
  private Long todoListId;
}
