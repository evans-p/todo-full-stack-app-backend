package gr.evansp.todofullstackappbackend.aspects;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/** {@link Aspect} for logging purposes. */
@SuppressWarnings("unused")
@Aspect
@Component
public class LoggingAspect {

  /** Aspect Logger. */
  private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  /** Method to check for Unauthorized access. */
  @AfterThrowing("execution( * *..checkOwnership(..))")
  public void afterUnauthorizedAccess() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null) {
      logger.warn("Unauthorized request for resource by {}", authentication.getName());
      return;
    }
    logger.warn("Unauthorized request for resource by Unknown User");
  }
}
