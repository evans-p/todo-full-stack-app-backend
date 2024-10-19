package gr.evansp.todofullstackappbackend.services.todos;

import static org.junit.jupiter.api.Assertions.assertThrows;

import gr.evansp.todofullstackappbackend.base.BaseITTest;
import gr.evansp.todofullstackappbackend.exceptions.UnauthorizedException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

/** Integration tests for {@link VerifyOwnershipService} */
@RunWith(SpringRunner.class)
@SpringBootTest
class TestVerifyOwnershipServiceIT extends BaseITTest {

  @Autowired VerifyOwnershipService service;

  @Test
  void testCheckOwnership_nullUserId() {
    assertThrows(UnauthorizedException.class, () -> service.checkOwnership(null));
  }

  @Test
  void testCheckOwnership_nullAuthentication() {
    SecurityContextHolder.clearContext();
    assertThrows(UnauthorizedException.class, () -> service.checkOwnership("efdasdf"));
  }

  @Test
  void testCheckOwnership_differentUserId() {
    assertThrows(UnauthorizedException.class, () -> service.checkOwnership("efdasdf"));
  }

  @Test
  void testCheckOwnership_ok() {
    service.checkOwnership(SUB);
  }
}
