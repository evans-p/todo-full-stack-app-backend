package gr.evansp.todofullstackappbackend.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

/** Configuration regarding application security. */
@SuppressWarnings("unused")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().requestMatchers("/swagger-ui/**", "/v3/api-docs/**");
  }
}
