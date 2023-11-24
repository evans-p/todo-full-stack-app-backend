package gr.evansp.todofullstackappbackend.todo.models;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TodoListPK {
  @EqualsAndHashCode.Include
  private Long userId;

  @EqualsAndHashCode.Include
  private Long todoListId;
}
