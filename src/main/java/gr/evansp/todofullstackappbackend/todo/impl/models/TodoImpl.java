package gr.evansp.todofullstackappbackend.todo.impl.models;

import gr.evansp.todofullstackappbackend.common.constants.ValidationConstants;
import gr.evansp.todofullstackappbackend.todo.def.models.Todo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "TBTODO")
@IdClass(TodoPKImpl.class)
public class TodoImpl implements Todo {
  @Id
  @Column(name = "USER_ID", nullable = false)
  @EqualsAndHashCode.Include
  @NotNull(message = ValidationConstants.ID_NULL)
  @Min(value = 1L, message = ValidationConstants.ID_GREATER_THAN_ZERO)
  @lombok.NonNull
  Long userId;

  @Id
  @Column(name = "LIST_ID", nullable = false)
  @EqualsAndHashCode.Include
  @NotNull(message = ValidationConstants.ID_NULL)
  @Min(value = 1L, message = ValidationConstants.ID_GREATER_THAN_ZERO)
  @lombok.NonNull
  Long todoListId;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TODO_ID", nullable = false)
  @NotNull(message = ValidationConstants.ID_NULL)
  @Min(value = 1L, message = ValidationConstants.ID_GREATER_THAN_ZERO)
  @EqualsAndHashCode.Include
  Long todoId;

  @Column(name = "TITLE", nullable = false)
  @NotNull(message = ValidationConstants.TITLE_NULL)
  @lombok.NonNull
  String title;

  @Column(name = "BODY")
  @lombok.NonNull
  String body;

  @Column(name = "CREATED", nullable = false)
  @NotNull(message = ValidationConstants.CREATE_DATE_NULL)
  LocalDateTime created = LocalDateTime.now();

  @Column(name = "LAST_MODIFIED", nullable = false)
  @NotNull(message = ValidationConstants.LAST_MODIFIED_DATE_NULL)
  LocalDateTime lastModified = LocalDateTime.now();
}
