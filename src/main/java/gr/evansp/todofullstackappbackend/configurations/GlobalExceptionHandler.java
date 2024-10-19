package gr.evansp.todofullstackappbackend.configurations;

import gr.evansp.todofullstackappbackend.exceptions.LogicException;
import gr.evansp.todofullstackappbackend.exceptions.NotFoundException;
import gr.evansp.todofullstackappbackend.exceptions.UnauthorizedException;
import gr.evansp.todofullstackappbackend.exceptions.messages.ExceptionMessage;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/** Application global exception handler. */
@SuppressWarnings("unused")
@ControllerAdvice
public class GlobalExceptionHandler {

  /** CANNOT PROCESS REQUEST. */
  public static final String CANNOT_PROCESS_REQUEST = "cannot.process.request";

  /** RESOURCE NOT FOUND. */
  public static final String RESOURCE_NOT_FOUND = "resource.not.found";

  /** FAULTY MESSAGE BODY. */
  public static final String FAULTY_MESSAGE_BODY = "faulty.message.body";

  /** MEDIA TYPE NOT SUPPORTED. */
  public static final String MEDIA_TYPE_NOT_SUPPORTED = "media.type.not.supported";

  /** INVALID DATA ACCESS. */
  public static final String INVALID_DATA_ACCESS = "invalid.data.access";

  /** METHOD NOT SUPPORTED. */
  public static final String METHOD_NOT_SUPPORTED = "method.not.supported";

  /** INTERNAL SERVER ERROR. */
  public static final String INTERNAL_SERVER_ERROR = "internal.server.error";

  /** {@link MessageSource}. */
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

  @ExceptionHandler(LogicException.class)
  public ResponseEntity<ExceptionMessage> handleLogic(LogicException e, Locale locale) {
    String errorMessage = messageSource.getMessage(e.getMessage(), e.getArgs(), locale);
    return new ResponseEntity<>(new ExceptionMessage(errorMessage), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ExceptionMessage> handleUnauthorized(
      UnauthorizedException e, Locale locale) {
    String errorMessage = messageSource.getMessage(e.getMessage(), e.getArgs(), locale);
    return new ResponseEntity<>(new ExceptionMessage(errorMessage), HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ExceptionMessage> handleMethodArgumentTypeMismatch(
      MethodArgumentTypeMismatchException e, Locale locale) {
    String errorMessage = messageSource.getMessage(CANNOT_PROCESS_REQUEST, null, locale);
    return new ResponseEntity<>(new ExceptionMessage(errorMessage), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ExceptionMessage> handleNoResourceFoundException(
      NoResourceFoundException e, Locale locale) {
    String errorMessage = messageSource.getMessage(RESOURCE_NOT_FOUND, null, locale);
    return new ResponseEntity<>(new ExceptionMessage(errorMessage), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ExceptionMessage> handleHttpMessageNotReadable(
      HttpMessageNotReadableException e, Locale locale) {
    String errorMessage = messageSource.getMessage(FAULTY_MESSAGE_BODY, null, locale);
    return new ResponseEntity<>(new ExceptionMessage(errorMessage), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<ExceptionMessage> handleHttpMediaTypeNotSupported(
      HttpMediaTypeNotSupportedException e, Locale locale) {
    String errorMessage = messageSource.getMessage(MEDIA_TYPE_NOT_SUPPORTED, null, locale);
    return new ResponseEntity<>(new ExceptionMessage(errorMessage), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidDataAccessApiUsageException.class)
  public ResponseEntity<ExceptionMessage> handleInvalidDataAccessApiUsageException(
      InvalidDataAccessApiUsageException e, Locale locale) {
    String errorMessage = messageSource.getMessage(INVALID_DATA_ACCESS, null, locale);
    return new ResponseEntity<>(new ExceptionMessage(errorMessage), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ExceptionMessage> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException e, Locale locale) {
    String errorMessage = messageSource.getMessage(METHOD_NOT_SUPPORTED, null, locale);
    return new ResponseEntity<>(new ExceptionMessage(errorMessage), HttpStatus.METHOD_NOT_ALLOWED);
  }

  /**
   * Handler for invalid Entities.
   *
   * @param e {@link ConstraintViolationException}.
   * @param locale locale.
   * @return {@link ResponseEntity}.
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ExceptionMessage> handleInvalid(
      ConstraintViolationException e, Locale locale) {
    Map<String, String> messages =
        e.getConstraintViolations().stream()
            .collect(
                Collectors.toMap(
                    c -> {
                      List<String> paths =
                          Arrays.asList(c.getPropertyPath().toString().split("\\."));
                      return paths.get(paths.size() - 1);
                    },
                    ConstraintViolation::getMessage));
    return new ResponseEntity<>(new ExceptionMessage(messages), HttpStatus.UNPROCESSABLE_ENTITY);
  }

  /**
   * If none of the exceptions above work, this one takes effect.
   *
   * @param e {@link Exception}.
   * @param locale locale.
   * @return {@link ResponseEntity}
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionMessage> handleGenericException(Exception e, Locale locale) {
    String errorMessage = messageSource.getMessage(METHOD_NOT_SUPPORTED, null, locale);
    return new ResponseEntity<>(
        new ExceptionMessage(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
