package cs3500.pa05.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * The type Invalid item controller.
 */
public class InvalidItemController implements JournalController {
  @FXML
  private Label messageField;

  @Override
  public void run() throws IllegalStateException {

  }

  /**
   * Sets message.
   *
   * @param message the message
   */
  public void setMessage(String message) {
    messageField.setText(message);
  }
}
