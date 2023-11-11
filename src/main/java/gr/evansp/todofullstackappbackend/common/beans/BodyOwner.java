package gr.evansp.todofullstackappbackend.common.beans;

/**
 * Something that owns a body.
 */
@SuppressWarnings("unused")
public interface BodyOwner {
  /**
   * getter for body.
   *
   * @return body
   */
  String getBody();

  /**
   * getter for body.
   *
   * @param body body
   */
  void setBody(String body);
}
