package cs3500.pa05.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * The type Password controller.
 */
public class PasswordController implements JournalController {
  @FXML
  private PasswordField passwordField;
  @FXML
  private Button enterButton;
  private String correctPass;
  @FXML
  private boolean allowAccess = false;

  /**
   * Runs the controller.
   *
   * @throws IllegalStateException throws if the controller is not in a valid state
   */
  @Override
  public void run() throws IllegalStateException {
    enterButton.setOnAction(e -> enterPassword());
  }

  /**
   * Checks if correct password was entered.
   */
  public void enterPassword() {
    System.out.println(passwordField.getText());
    System.out.println("Correct password: " + correctPass);
    System.out.println(passwordField.getText().equals(correctPass));

    if (passwordField.getText().equals(correctPass)) {
      System.out.println("Access granted");
      allowAccess = true;
      Stage stage = (Stage) enterButton.getScene().getWindow();
      stage.close();
    } else {
      allowAccess = false;
    }
  }

  /**
   * Sets the correct password.
   *
   * @param correctPass the correct password
   */
  public void setCorrectPass(String correctPass) {
    this.correctPass = correctPass;
  }

  /**
   * Checks if access is allowed.
   *
   * @return true if access is allowed, false otherwise
   */
  public boolean isAccessAllowed() {
    return allowAccess;
  }
}
