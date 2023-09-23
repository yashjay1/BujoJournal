package cs3500.pa05.controller;

import cs3500.pa05.model.Category;
import cs3500.pa05.model.DaysOfTheWeek;
import cs3500.pa05.view.JournalGuiView;
import cs3500.pa05.view.JournalView;
import cs3500.pa05.view.Task;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The type Task controller.
 */
public class TaskController implements JournalController {
  private final List<Task> tasks;
  private int maxNumOfTasks = 0;
  private ArrayList<Integer> maxDayTasks = new ArrayList<>();
  @FXML
  private Button createTaskButton;
  @FXML
  private TextField nameTextField;
  @FXML
  private TextArea descriptionTextField;
  @FXML
  private ComboBox daySelector;
  @FXML
  private ComboBox categorySelector;

  /**
   * Instantiates a new Task controller.
   */
  TaskController() {
    this.tasks = new ArrayList<>();
  }

  @Override
  public void run() throws IllegalStateException {
    daySelector.getItems()
        .addAll(DaysOfTheWeek.SUNDAY, DaysOfTheWeek.MONDAY, DaysOfTheWeek.TUESDAY,
            DaysOfTheWeek.WEDNESDAY, DaysOfTheWeek.THURSDAY, DaysOfTheWeek.FRIDAY,
            DaysOfTheWeek.SATURDAY);
    categorySelector.getItems()
        .addAll(Category.HOME, Category.SCHOOL, Category.WORK, Category.OTHER);
    createTaskButton.setOnAction(e -> addTask());
  }

  /**
   * Add task.
   */
  void addTask() {
    DaysOfTheWeek day = (DaysOfTheWeek) daySelector.getValue();
    try {
      boolean dayMaxTasks = maxDayTasks.get(day.ordinal()) == maxNumOfTasks;


      if (dayMaxTasks) {
        showInvalidEventStage("InvalidEvent.fxml", "Invalid Task");
      } else {
        try {
          String name = nameTextField.getText();
          if (name.equals("") || name == null) {
            throw new NullPointerException();
          }
          Category category = (Category) categorySelector.getValue();
          String description = descriptionTextField.getText();
          if (description == null) {
            description = "";
          }

          Task currentTask = new Task(name, description, day, category);
          tasks.add(currentTask);

          Stage stage = (Stage) createTaskButton.getScene().getWindow();
          stage.close();

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
   * Gets tasks.
   *
   * @return the tasks
   */
  List<Task> getTasks() {
    return this.tasks;
  }

  /**
   * Sets max num of tasks.
   *
   * @param maxNumOfTasks the max num of tasks
   */
  public void setMaxNumOfTasks(int maxNumOfTasks) {
    this.maxNumOfTasks = maxNumOfTasks;
  }

  /**
   * Sets max day tasks.
   *
   * @param maxDayTasks the max day tasks
   */
  public void setMaxDayTasks(ArrayList<Integer> maxDayTasks) {
    this.maxDayTasks = maxDayTasks;
  }

}
