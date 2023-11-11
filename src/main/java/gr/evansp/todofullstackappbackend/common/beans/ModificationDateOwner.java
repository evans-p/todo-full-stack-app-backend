package gr.evansp.todofullstackappbackend.common.beans;

import java.time.LocalDateTime;

/**
 * Interface containing modification timestamp for any object.
 */
@SuppressWarnings("unused")
public interface ModificationDateOwner {
  /**
   * Getter for Last modified.
   *
   * @return Last modified
   */
  LocalDateTime getLastModified();

  /**
   * Setter for Last modified.
   *
   * @param lastModified Last modified.
   */
  void setLastModified(LocalDateTime lastModified);
}
