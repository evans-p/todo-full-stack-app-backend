package gr.evansp.todofullstackappbackend.user.models.def;

import java.io.Serializable;

/**
 * User primary key
 */
public interface UserPK extends Serializable {
  /**
   * Getter for userId.
   *
   * @return userId.
   */
  Long getUserId();

  /**
   * Setter for userId.
   *
   * @param userId userId.
   */
  void setUserId(Long userId);
}
