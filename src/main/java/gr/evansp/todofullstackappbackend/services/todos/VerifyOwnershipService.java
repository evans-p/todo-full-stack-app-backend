package gr.evansp.todofullstackappbackend.services.todos;

/** Service for Resource Ownership check. */
public interface VerifyOwnershipService {

  /**
   * Checks if a resource belongs to the currently validated user.
   *
   * @param userId userId
   */
  void checkOwnership(String userId);
}
