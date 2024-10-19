package gr.evansp.todofullstackappbackend.models.todos;

import gr.evansp.todofullstackappbackend.constants.StringConstants;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

/** TodoList. */
@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(schema = "TODO", name = "TBTODOLIST")
public class TodoList {

  public TodoList(@NonNull String userId, @NonNull String title) {
    this.userId = userId;
    this.title = title;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LIST_ID", nullable = false)
  @Min(value = 1L, message = "{id.min}")
  private Long todoListId;

  @Column(name = "USER_ID", nullable = false)
  @NotEmpty(message = "{user.id.null}")
  @lombok.NonNull
  private String userId = StringConstants.EMPTY;

  @Column(name = "TITLE", nullable = false)
  @NotEmpty(message = "{title.empty}")
  @lombok.NonNull
  private String title = StringConstants.EMPTY;

  @Column(name = "CREATED", nullable = false)
  @NotNull(message = "{create.date.null}")
  private LocalDateTime created = LocalDateTime.now();

  @Column(name = "LAST_MODIFIED", nullable = false)
  @NotNull(message = "{last.modified.date.null}")
  private LocalDateTime lastModified = LocalDateTime.now();

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "LIST_ID", insertable = false, updatable = false)
  private Set<Todo> todos = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    TodoList list = (TodoList) o;

    return Objects.equals(todoListId, list.todoListId);
  }

  @Override
  public int hashCode() {
    return todoListId != null ? todoListId.hashCode() : 0;
  }
}
