package gr.evansp.todofullstackappbackend.utils.messages;

import lombok.Getter;

import java.util.Map;

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
