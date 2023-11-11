package gr.evansp.todofullstackappbackend.common.beans;

/**
 * Someone who owns an email.
 */
public interface EmailOwner {
  /**
   * getter for email.
   *
   * @return email
   */
  String getEmail();

  /**
   * setter for email.
   *
   * @param email email
   */
  void setEmail(String email);
}
