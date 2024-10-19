package gr.evansp.todofullstackappbackend.configurations;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/** {@link Configuration} for Message resource bundle. */
@SuppressWarnings("unused")
@Configuration
public class MessagesConfiguration {

  /**
   * Loads messages.properties as a {@link MessageSource}.
   *
   * @return {@link MessageSource}.
   */
  @Bean
  MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource =
        new ReloadableResourceBundleMessageSource();

    messageSource.setBasename("classpath:messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }
}
