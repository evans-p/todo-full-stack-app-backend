package gr.evansp.todofullstackappbackend.todo.models;

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
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TODO_ID", nullable = false)
  @Min(value = 1L, message = "{id.min}")
  @EqualsAndHashCode.Include
  private Long todoId;

  @Column(name = "LIST_ID", nullable = false)
  @NotNull(message = "{id.null}")
  @Min(value = 1L, message = "{id.min}")
  @lombok.NonNull
  private Long todoListId;

  @Column(name = "TITLE", nullable = false)
  @NotEmpty(message = "{title.empty}")
  @lombok.NonNull
  private String title;

  @Column(name = "BODY")
  @lombok.NonNull
  private String body;

  @Column(name = "CREATED", nullable = false)
  @NotNull(message = "{create.date.null}")
  private LocalDateTime created = LocalDateTime.now();

  @Column(name = "LAST_MODIFIED", nullable = false)
  @NotNull(message = "{last.modified.date.null}")
  private LocalDateTime lastModified = LocalDateTime.now();

  @Column(name = "FAVOURITE")
  private Boolean favourite;
}
