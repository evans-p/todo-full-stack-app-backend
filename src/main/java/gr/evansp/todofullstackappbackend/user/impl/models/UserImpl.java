package gr.evansp.todofullstackappbackend.user.impl.models;

import gr.evansp.todofullstackappbackend.common.constants.ValidationConstants;
import gr.evansp.todofullstackappbackend.user.def.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Implementation of {@link User}.
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "TBUSER")
public class UserImpl implements User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_ID", nullable = false)
  @NotNull(message = ValidationConstants.USER_ID_NULL)
  @Min(value = 1L, message = ValidationConstants.USER_ID_GREATER_THAN_ZERO)
  @EqualsAndHashCode.Include
  private Long userId;

  @Column(name = "EMAIL", unique = true, nullable = false)
  @NotEmpty(message = ValidationConstants.EMAIL_EMPTY)
  @Email(message = ValidationConstants.EMAIL_VALID)
  @lombok.NonNull
  private String email;

  @NotNull(message = ValidationConstants.PASSWORD_NULL)
  @Size(min = 8, message = ValidationConstants.PASSWORD_SHORT)
  @Column(name = "PASSWORD", nullable = false)
  @lombok.NonNull
  private String password;

  @Column(name = "CREATED", nullable = false)
  @NotNull(message = ValidationConstants.CREATE_DATE_NULL)
  private LocalDateTime created = LocalDateTime.now();
}
