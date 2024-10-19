package gr.evansp.todofullstackappbackend.models.todos;

import gr.evansp.todofullstackappbackend.constants.StringConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

/** Todo. */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(schema = "TODO", name = "TBTODO")
public class Todo {

  /**
   * Required Args Constructor.
   *
   * @param todoListId todoListId
   * @param title title
   * @param userId userId
   */
  public Todo(@NonNull Long todoListId, @NonNull String title, @NonNull String userId) {

    this.todoListId = todoListId;
    this.title = title;
    this.userId = userId;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TODO_ID", nullable = false)
  @Min(value = 1L, message = "{id.min}")
  private Long todoId;

  @Column(name = "LIST_ID", nullable = false)
  @NotNull(message = "{id.null}")
  @Min(value = 1L, message = "{id.min}")
  @lombok.NonNull
  private Long todoListId = 0L;

  @Column(name = "TITLE", nullable = false)
  @NotEmpty(message = "{title.empty}")
  @lombok.NonNull
  private String title = StringConstants.EMPTY;

  @Column(name = "USER_ID", nullable = false)
  @NotEmpty(message = "{user.id.null}")
  @lombok.NonNull
  private String userId = StringConstants.EMPTY;

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

  @Column(name = "COMPLETED")
  private Boolean completed;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Todo todo = (Todo) o;

    return Objects.equals(todoId, todo.todoId);
  }

  @Override
  public int hashCode() {
    return todoId != null ? todoId.hashCode() : 0;
  }
}
