package gr.evansp.todofullstackappbackend.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class for all IT tests.
 */
public abstract class BaseITTest extends BaseTest {
  /**
   * User ID.
   */
  public static final String SUB = "dummyUser";

  /**
   * Base URI for todolist Controller.
   */
  public static final String TODO_LISTS_BASE_URI = "/todo-lists/";

  /**
   * Base URI for todo1 Controller.
   */
  public static final String TODOS_BASE_URI = "/todos/";


  /**
   * Creates token headers.
   *
   * @return Map<String, Object>
   */
  private static Map<String, Object> createHeaders() {
    Map<String, Object> headers = new HashMap<>();
    headers.put("dummyHeader", "dummyHeader");
    return headers;
  }

  /**
   * Creates token claims.
   *
   * @return Map<String, Object>
   */
  private static Map<String, Object> createClaims() {
    Map<String, Object> headers = new HashMap<>();
    headers.put("dummyClaim", "dummyClaim");
    headers.put("sub", SUB);
    return headers;
  }

  /**
   * Auth setup
   */
  @BeforeEach
  void setupAuthorization() {

    Jwt jwt = new Jwt("dummy", Instant.now(), Instant.MAX, createHeaders(), createClaims());

    JwtAuthenticationToken token = new JwtAuthenticationToken(jwt);
    SecurityContext context = SecurityContextHolder.createEmptyContext();
    context.setAuthentication(token);


    SecurityContextHolder.setContext(context);
  }

  /**
   * Auth cleanup.
   */
  @AfterEach
  void cleanupAuthorization() {
    SecurityContextHolder.clearContext();
  }
}
