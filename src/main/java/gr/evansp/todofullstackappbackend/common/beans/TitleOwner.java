package gr.evansp.todofullstackappbackend.common.beans;

/**
 * Something that owns a title.
 */
@SuppressWarnings("unused")
public interface TitleOwner {
  /**
   * getter for title.
   *
   * @return title
   */
  String getTitle();

  /**
   * getter for title.
   *
   * @param title title
   */
  void setTitle(String title);
}
