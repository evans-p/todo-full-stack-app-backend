package gr.evansp.todofullstackappbackend.user.def.models;

/**
 * User primary key
 */
public interface UserPK {
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
