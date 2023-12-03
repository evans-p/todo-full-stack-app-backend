package gr.evansp.todofullstackappbackend.configurations;

import gr.evansp.todofullstackappbackend.exceptions.NotFoundException;
import gr.evansp.todofullstackappbackend.utils.messages.ExceptionMessage;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Application global exception handler.
 */
@SuppressWarnings("unused")
@ControllerAdvice
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

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ExceptionMessage> handleInvalid(ConstraintViolationException e, Locale locale) {
    Map<String, String> messages = e.getConstraintViolations()
        .stream()
        .collect(Collectors.toMap(c -> {
          List<String> paths = Arrays.asList(c.getPropertyPath().toString().split("\\."));
          return paths.get(paths.size() - 1);
        }, ConstraintViolation::getMessage));
    return new ResponseEntity<>(new ExceptionMessage(messages), HttpStatus.NOT_FOUND);
  }
}
