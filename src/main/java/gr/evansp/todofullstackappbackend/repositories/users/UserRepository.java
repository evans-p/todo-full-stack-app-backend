package gr.evansp.todofullstackappbackend.repositories.users;

import gr.evansp.todofullstackappbackend.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link User}.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  //EMPTY
}
