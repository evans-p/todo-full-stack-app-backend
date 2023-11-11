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
  @Column(name = "ID")
  @NotNull(message = ValidationConstants.USER_ID_NULL)
  @Min(value = 1L)
  @EqualsAndHashCode.Include
  Long userId;

  @Column(name = "EMAIL", unique = true)
  @NotEmpty(message = ValidationConstants.EMAIL_EMPTY)
  @Email(message = ValidationConstants.EMAIL_VALID)
  @lombok.NonNull
  String email;

  @NotNull(message = ValidationConstants.PASSWORD_NULL)
  @Size(min = 8, message = ValidationConstants.PASSWORD_SHORT)
  @Column(name = "PASSWORD")
  @lombok.NonNull
  String password;

  @Column(name = "CREATED")
  @NotNull(message = ValidationConstants.CREATE_DATE_NULL)
  @lombok.NonNull
  LocalDateTime created = LocalDateTime.now();
}
