package gr.evansp.todofullstackappbackend.user.repository;

import gr.evansp.todofullstackappbackend.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link User}.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  //EMPTY
}
