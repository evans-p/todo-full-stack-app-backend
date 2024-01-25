package gr.evansp.todofullstackappbackend.models.todos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(schema = "TODO", name = "TBTODOLIST")
@SuppressWarnings("com.haulmont.jpb.LombokEqualsAndHashCodeInspection")
public class TodoList {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LIST_ID", nullable = false)
  @EqualsAndHashCode.Include
  @Min(value = 1L, message = "{id.min}")
  private Long todoListId;

  @Column(name = "USER_ID", nullable = false)
  @NotEmpty(message = "{id.null}")
  @lombok.NonNull
  private String userId;

  @Column(name = "TITLE", nullable = false)
  @NotEmpty(message = "{title.empty}")
  @lombok.NonNull
  private String title;

  @Column(name = "CREATED", nullable = false)
  @NotNull(message = "{create.date.null}")
  private LocalDateTime created = LocalDateTime.now();

  @Column(name = "LAST_MODIFIED", nullable = false)
  @NotNull(message = "{last.modified.date.null}")
  private LocalDateTime lastModified = LocalDateTime.now();

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "LIST_ID")
  private Set<Todo> todos = new HashSet<>();
}
