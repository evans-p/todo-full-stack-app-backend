package gr.evansp.todofullstackappbackend.todo.impl.models;

import gr.evansp.todofullstackappbackend.common.constants.ValidationConstants;
import gr.evansp.todofullstackappbackend.todo.def.models.Todo;
import gr.evansp.todofullstackappbackend.todo.def.models.TodoList;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link TodoList}
 */
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "TBTODOLIST")
@IdClass(TodoListImpl.class)
public class TodoListImpl implements TodoList {
  @Id
  @Column(name = "USER_ID", nullable = false)
  @EqualsAndHashCode.Include
  @NotNull(message = ValidationConstants.ID_NULL)
  @Min(value = 1L, message = ValidationConstants.ID_GREATER_THAN_ZERO)
  @lombok.NonNull
  private Long userId;

  @Id
  @Column(name = "LIST_ID", nullable = false)
  @EqualsAndHashCode.Include
  @NotNull(message = ValidationConstants.ID_NULL)
  @Min(value = 1L, message = ValidationConstants.ID_GREATER_THAN_ZERO)
  @lombok.NonNull
  private Long todoListId;

  @Column(name = "TITLE", nullable = false)
  @NotNull(message = ValidationConstants.TITLE_NULL)
  @lombok.NonNull
  private String title;

  @Column(name = "CREATED", nullable = false)
  @NotNull(message = ValidationConstants.CREATE_DATE_NULL)
  private LocalDateTime created = LocalDateTime.now();

  @Column(name = "LAST_MODIFIED", nullable = false)
  @NotNull(message = ValidationConstants.LAST_MODIFIED_DATE_NULL)
  private LocalDateTime lastModified = LocalDateTime.now();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "LIST_ID")
  private Set<Todo> todos = new HashSet<>();
}