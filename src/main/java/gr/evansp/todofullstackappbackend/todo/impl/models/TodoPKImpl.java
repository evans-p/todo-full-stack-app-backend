package gr.evansp.todofullstackappbackend.todo.impl.models;

import gr.evansp.todofullstackappbackend.todo.def.models.TodoPK;
import lombok.*;

/**
 * Implementation for {@link TodoPK}.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TodoPKImpl implements TodoPK {
  private Long userId;
  private Long todoListId;
  private Long todoId;
}
