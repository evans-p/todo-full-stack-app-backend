package gr.evansp.todofullstackappbackend.user.models;

import gr.evansp.todofullstackappbackend.todo.models.TodoList;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(schema = "TODO", name = "TBUSER")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_ID", nullable = false)
  @Min(value = 1L, message = "{id.min}")
  @EqualsAndHashCode.Include
  private Long userId;

  @Column(name = "EMAIL", unique = true, nullable = false)
  @NotEmpty(message = "{email.empty}")
  @Email(message = "{email.invalid}")
  @lombok.NonNull
  private String email;

  @NotNull(message = "{password.empty}")
  @Size(min = 8, message = "{password.too.short}")
  @Column(name = "PASSWORD", nullable = false)
  @lombok.NonNull
  private String password;

  @Column(name = "CREATED", nullable = false)
  @NotNull(message = "{create.date.null}")
  private LocalDateTime created = LocalDateTime.now();

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "USER_ID")
  private Set<TodoList> todoLists = new HashSet<>();
}
