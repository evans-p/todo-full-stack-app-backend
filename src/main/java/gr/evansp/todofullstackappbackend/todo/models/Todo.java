package gr.evansp.todofullstackappbackend.todo.models;

import gr.evansp.todofullstackappbackend.common.constants.ValidationConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(schema = "TODO", name = "TBTODO")
@IdClass(TodoPK.class)
public class Todo {
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

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TODO_ID", nullable = false)
  @NotNull(message = ValidationConstants.ID_NULL)
  @Min(value = 1L, message = ValidationConstants.ID_GREATER_THAN_ZERO)
  @EqualsAndHashCode.Include
  private Long todoId;

  @Column(name = "TITLE", nullable = false)
  @NotEmpty(message = ValidationConstants.TITLE_EMPTY)
  @lombok.NonNull
  private String title;

  @Column(name = "BODY")
  @lombok.NonNull
  private String body;

  @Column(name = "CREATED", nullable = false)
  @NotNull(message = ValidationConstants.CREATE_DATE_NULL)
  private LocalDateTime created = LocalDateTime.now();

  @Column(name = "LAST_MODIFIED", nullable = false)
  @NotNull(message = ValidationConstants.LAST_MODIFIED_DATE_NULL)
  private LocalDateTime lastModified = LocalDateTime.now();
}
