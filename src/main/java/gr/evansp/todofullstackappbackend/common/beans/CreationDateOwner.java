package gr.evansp.todofullstackappbackend.common.beans;

import java.time.LocalDateTime;

/**
 * Interface containing creating timestamp for any object.
 */
public interface CreationDateOwner {
  /**
   * Getter for created
   *
   * @return created
   */
  LocalDateTime getCreated();

  /**
   * Setter for created
   *
   * @param created created
   */
  void setCreated(LocalDateTime created);
}
