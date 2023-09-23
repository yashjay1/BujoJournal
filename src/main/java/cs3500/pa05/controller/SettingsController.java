package cs3500.pa05.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

/**
 * The SettingsController class is responsible for controlling the settings view
 * and managing user interactions with the settings.
 */
public class SettingsController implements JournalController {

  @FXML
  private ToggleButton toggleFlip;

  @FXML
  private TextField maxEvents;

  @FXML
  private TextField maxTasks;

  @FXML
  private TextField passwordField;

  private final JournalControllerImpl journalController;

  /**
   * Constructs a SettingsController object with the specified JournalControllerImpl.
   *
   * @param journalController the JournalControllerImpl instance
   */
  SettingsController(JournalControllerImpl journalController) {
    this.journalController = journalController;
  }

  /**
   * Runs the settings controller. Sets the toggle button's selected state based on the
   * current state of the journal controller.
   *
   * @throws IllegalStateException if the controller is unable to run
   */
  @Override
  public void run() throws IllegalStateException {
    if (journalController.isFlipped()) {
      toggleFlip.setSelected(true);
    }
  }

  /**
   * Checks if the toggle button is selected.
   *
   * @return true if the toggle button is selected, false otherwise
   */
  public boolean isToggled() {
    return toggleFlip.isSelected();
  }

  /**
   * Retrieves the maximum number of events specified in the maxEvents TextField.
   * If the input is not a valid integer, returns 0.
   *
   * @return the maximum number of events, or 0 if not a valid integer
   */
  public int getMaxEvents() {
    try {
      Integer.parseInt(maxEvents.getText());
    } catch (NumberFormatException e) {
      return 0;
    }
    return Integer.parseInt(maxEvents.getText());
  }

  /**
   * Sets the maximum number of events in the maxEvents TextField.
   *
   * @param maxEvents the maximum number of events
   */
  public void setMaxEvents(int maxEvents) {
    this.maxEvents.setText(Integer.toString(maxEvents));
  }

  /**
   * Retrieves the maximum number of tasks specified in the maxTasks TextField.
   * If the input is not a valid integer, returns 0.
   *
   * @return the maximum number of tasks, or 0 if not a valid integer
   */
  public int getMaxTasks() {
    try {
      Integer.parseInt(maxTasks.getText());
    } catch (NumberFormatException e) {
      return 0;
    }
    return Integer.parseInt(maxTasks.getText());
  }

  /**
   * Sets the maximum number of tasks in the maxTasks TextField.
   *
   * @param maxTasks the maximum number of tasks
   */
  public void setMaxTasks(int maxTasks) {
    this.maxTasks.setText(Integer.toString(maxTasks));
  }

  /**
   * Retrieves the password specified in the passwordField TextField.
   *
   * @return the password as a string
   */
  public String getPassword() {
    return passwordField.getText();
  }
}
