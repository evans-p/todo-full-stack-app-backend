package gr.evansp.todofullstackappbackend.exceptions.messages;

import java.util.Map;
import lombok.Getter;

/** Message Bean te be returned when an exception occurs. */
@Getter
public class ExceptionMessage {
  private String message;
  private Map<String, String> messages;

  public ExceptionMessage(String message) {
    this.message = message;
  }

  public ExceptionMessage(Map<String, String> messages) {
    this.messages = messages;
  }
}
