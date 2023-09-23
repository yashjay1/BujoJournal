package cs3500.pa05.controller;

import cs3500.pa05.model.Category;
import cs3500.pa05.model.DaysOfTheWeek;
import cs3500.pa05.view.Event;
import cs3500.pa05.view.JournalGuiView;
import cs3500.pa05.view.JournalView;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The type Event controller.
 */
public class EventController implements JournalController {
  private final List<Event> events;
  private int maxNumOfEvents = 0;
  private ArrayList<Integer> maxDayEvents = new ArrayList<>();
  @FXML
  private Button createEventButton;
  @FXML
  private TextField nameTextField;
  @FXML
  private TextArea descriptionTextField;
  @FXML
  private ComboBox daySelector;
  @FXML
  private TextField timeTextField;
  @FXML
  private TextField durationTextField;
  @FXML
  private ComboBox categorySelector;

  /**
   * Instantiates a new Event controller.
   */
  EventController() {
    this.events = new ArrayList<>();
  }

  @Override
  public void run() throws IllegalStateException {
    daySelector.getItems().addAll(DaysOfTheWeek.SUNDAY, DaysOfTheWeek.MONDAY, DaysOfTheWeek.TUESDAY,
        DaysOfTheWeek.WEDNESDAY, DaysOfTheWeek.THURSDAY, DaysOfTheWeek.FRIDAY,
        DaysOfTheWeek.SATURDAY);
    categorySelector.getItems()
        .addAll(Category.HOME, Category.SCHOOL, Category.WORK, Category.OTHER);

    createEventButton.setOnAction(e -> addEvent());
  }

  /**
   * Add event.
   */
  void addEvent() {
    DaysOfTheWeek day = (DaysOfTheWeek) daySelector.getValue();
    try {
      boolean dayMaxEvents = maxDayEvents.get(day.ordinal()) == maxNumOfEvents;


      if (dayMaxEvents) {
        showInvalidEventStage("InvalidEvent.fxml", "Invalid Event");
      } else {
        try {
          String name = nameTextField.getText();
          if (name.equals("") || name == null) {
            throw new NullPointerException();
          }
          String time = timeTextField.getText();

          try {
            int duration = Integer.parseInt(durationTextField.getText());
            Category category = (Category) categorySelector.getValue();
            String description = descriptionTextField.getText();
            if (description == null) {
              description = "";
            }

            Event currentEvent = new Event(name, description, day, time, duration, category);
            events.add(currentEvent);

            Stage stage = (Stage) createEventButton.getScene().getWindow();
            stage.close();
          } catch (NumberFormatException e) {
            showInvalidEventStage("InvalidEvent.fxml", "Invalid Time!");
          }

        } catch (NullPointerException e) {
          showInvalidEventStage("InvalidEvent.fxml", "Missing Fields!");
        }
      }
    } catch (NullPointerException e) {
      showInvalidEventStage("InvalidEvent.fxml", "No day selected!");
    }
  }

  /**
   * Show invalid event stage.
   *
   * @param fxmlFile the fxml file
   * @param title    the title
   */
  void showInvalidEventStage(String fxmlFile, String title) {
    Stage stage = new Stage();
    InvalidItemController invalidItemController = new InvalidItemController();
    JournalView view = new JournalGuiView(invalidItemController, fxmlFile);
    stage.setScene(view.load());
    invalidItemController.setMessage(title);
    stage.setTitle(title);
    invalidItemController.run();
    stage.show();
  }


  /**
   * Sets max num of events.
   *
   * @param maxNumOfEvents the max num of events
   */
  public void setMaxNumOfEvents(int maxNumOfEvents) {
    this.maxNumOfEvents = maxNumOfEvents;
  }

  /**
   * Sets max day events.
   *
   * @param maxDayEvents the max day events
   */
  public void setMaxDayEvents(ArrayList<Integer> maxDayEvents) {
    this.maxDayEvents = maxDayEvents;
  }

  /**
   * Gets events.
   *
   * @return the events
   */
  List<Event> getEvents() {
    return this.events;
  }


}
