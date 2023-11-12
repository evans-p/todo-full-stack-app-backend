package gr.evansp.todofullstackappbackend.todo.impl.models;

import gr.evansp.todofullstackappbackend.todo.def.models.TodoPK;
import lombok.*;

/**
 * Implementation for {@link TodoPK}.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TodoPKImpl implements TodoPK {
  @EqualsAndHashCode.Include
  private Long userId;

  @EqualsAndHashCode.Include
  private Long todoListId;

  @EqualsAndHashCode.Include
  private Long todoId;
}
