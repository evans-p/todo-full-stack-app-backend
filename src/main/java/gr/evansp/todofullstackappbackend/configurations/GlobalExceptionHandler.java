package gr.evansp.todofullstackappbackend.configurations;

import gr.evansp.todofullstackappbackend.exceptions.NotFoundException;
import gr.evansp.todofullstackappbackend.utils.messages.ExceptionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

/**
 * Application global exception handler.
 */
@SuppressWarnings("unused")
@RestControllerAdvice
public class GlobalExceptionHandler {

  private final MessageSource messageSource;

  /**
   * Constructor.
   *
   * @param messageSource messageSource.
   */
  @Autowired
  public GlobalExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ExceptionMessage> handleNotFound(NotFoundException e, Locale locale) {
    String errorMessage = messageSource.getMessage(e.getMessage(), e.getArgs(), locale);
    return new ResponseEntity<>(new ExceptionMessage(errorMessage), HttpStatus.NOT_FOUND);
  }
}
