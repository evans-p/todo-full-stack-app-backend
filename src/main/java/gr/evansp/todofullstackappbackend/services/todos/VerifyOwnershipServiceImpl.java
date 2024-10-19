package gr.evansp.todofullstackappbackend.services.todos;

import gr.evansp.todofullstackappbackend.exceptions.UnauthorizedException;
import java.util.Objects;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/** Implementation of {@link VerifyOwnershipService}. */
@SuppressWarnings("unused")
@Service
public class VerifyOwnershipServiceImpl implements VerifyOwnershipService {
  @Override
  public void checkOwnership(String userId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (userId == null
        || authentication == null
        || !Objects.equals(authentication.getName(), userId)) {
      throw new UnauthorizedException(UnauthorizedException.UNAUTHORIZED, null);
    }
  }
}
