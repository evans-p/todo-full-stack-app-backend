package gr.evansp.todofullstackappbackend.todo.models.impl;

import gr.evansp.todofullstackappbackend.todo.models.def.TodoPK;
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
