package gr.evansp.todofullstackappbackend.todo.models.impl;

import gr.evansp.todofullstackappbackend.todo.models.def.TodoListPK;
import lombok.*;

/**
 * Implementation of {@link TodoListPK}.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TodoListPKImpl implements TodoListPK {
  @EqualsAndHashCode.Include
  private Long userId;

  @EqualsAndHashCode.Include
  private Long todoListId;
}
