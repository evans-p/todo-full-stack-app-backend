package gr.evansp.todofullstackappbackend.models.todos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;


@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(schema = "TODO", name = "TBTODO")
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TODO_ID", nullable = false)
  @Min(value = 1L, message = "{id.min}")
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

  @Column(name = "USER_ID", nullable = false)
  @NotEmpty(message = "{user.id.null}")
  @lombok.NonNull
  private String userId;

  @Column(name = "BODY")
  private String body;

  @Column(name = "CREATED", nullable = false)
  @NotNull(message = "{create.date.null}")
  private LocalDateTime created = LocalDateTime.now();

  @Column(name = "LAST_MODIFIED", nullable = false)
  @NotNull(message = "{last.modified.date.null}")
  private LocalDateTime lastModified = LocalDateTime.now();

  @Column(name = "FAVOURITE")
  private Boolean favourite;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Todo todo = (Todo) o;

    return Objects.equals(todoId, todo.todoId);
  }

  @Override
  public int hashCode() {
    return todoId != null ? todoId.hashCode() : 0;
  }
}
