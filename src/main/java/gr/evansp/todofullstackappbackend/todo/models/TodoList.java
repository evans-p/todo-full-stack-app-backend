package gr.evansp.todofullstackappbackend.todo.models;

import gr.evansp.todofullstackappbackend.common.constants.ValidationConstants;
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
public class TodoList {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LIST_ID", nullable = false)
  @EqualsAndHashCode.Include
  @Min(value = 1L, message = ValidationConstants.ID_GREATER_THAN_ZERO)
  private Long todoListId;

  @Column(name = "USER_ID", nullable = false)
  @NotNull(message = ValidationConstants.ID_NULL)
  @Min(value = 1L, message = ValidationConstants.ID_GREATER_THAN_ZERO)
  @lombok.NonNull
  private Long userId;

  @Column(name = "TITLE", nullable = false)
  @NotEmpty(message = ValidationConstants.TITLE_EMPTY)
  @lombok.NonNull
  private String title;

  @Column(name = "CREATED", nullable = false)
  @NotNull(message = ValidationConstants.CREATE_DATE_NULL)
  private LocalDateTime created = LocalDateTime.now();

  @Column(name = "LAST_MODIFIED", nullable = false)
  @NotNull(message = ValidationConstants.LAST_MODIFIED_DATE_NULL)
  private LocalDateTime lastModified = LocalDateTime.now();

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "LIST_ID")
  private Set<Todo> todos = new HashSet<>();
}
