package gr.evansp.todofullstackappbackend.common.beans;

/**
 * Someone who owns a password.
 */
public interface PasswordOwner {
  /**
   * Getter for password.
   *
   * @return password(string)
   */
  String getPassword();

  /**
   * Setter for password
   *
   * @param password password
   */
  void setPassword(String password);
}
