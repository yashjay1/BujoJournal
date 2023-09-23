package cs3500.pa05.controller;

import cs3500.pa05.model.CombinedJson;
import cs3500.pa05.model.DaysOfTheWeek;
import cs3500.pa05.model.Themes;
import cs3500.pa05.view.Event;
import cs3500.pa05.view.JournalGuiView;
import cs3500.pa05.view.JournalView;
import cs3500.pa05.view.Task;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The type Journal controller.
 */
public class JournalControllerImpl implements JournalController {

  private Themes theme;
  private final EventController eventController;
  private final TaskController taskController;
  private final SettingsController settingsController;
  @FXML
  private VBox sundayListBox;
  @FXML
  private VBox mondayListBox;
  @FXML
  private VBox tuesdayListBox;
  @FXML
  private VBox wednesdayListBox;
  @FXML
  private VBox thursdayListBox;
  @FXML
  private VBox fridayListBox;
  @FXML
  private VBox saturdayListBox;
  @FXML
  private MenuItem eventButton;
  @FXML
  private MenuItem taskButton;
  @FXML
  private ComboBox daySelector;
  @FXML
  private Button saveButton;
  @FXML
  private TextArea notesArea;
  @FXML
  private Label nameText;
  @FXML
  private Label numOfEvents;
  @FXML
  private Label numOfTasks;
  @FXML
  private Label percentOfCompletion;
  @FXML
  private Label totalEventsDuration;
  @FXML
  private Button settingsButton;
  @FXML
  private LineChart<String, Number> eventLineChart;
  private List<Event> events = new ArrayList<>();
  private List<Task> tasks = new ArrayList<>();
  private final String name;
  @FXML
  private HBox weekContainer;
  @FXML
  private Button mainMenuButton;
  private String password = "";
  private boolean isFlipped = false;
  private int maxTasks = 0;
  private int maxEvents = 0;


  /**
   * Instantiates a new Journal controller.
   *
   * @param name the name
   */
  public JournalControllerImpl(String name) {
    this.eventController = new EventController();
    this.taskController = new TaskController();
    this.settingsController = new SettingsController(this);
    this.name = name;
  }

  /**
   * Runs the controller
   */
  public void run() {
    nameText.setText(name);
    eventButton.setOnAction(e -> openEventCreator());
    taskButton.setOnAction(e -> openTaskCreator());

    saveButton.setOnAction(e -> {
      saveData();
    });

    settingsButton.setOnAction(e -> {
      openSettings();
    });

    weekContainer.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        percentOfCompletion.setText("Percent of Completion: " + percentOfCompletion());
        totalEventsDuration.setText("Total Events Duration: " + eventDuration());
      }
    });

    mainMenuButton.setOnAction(e -> {
      Stage mainStage = new Stage();
      JournalController controller = new MainMenuController();
      JournalView view = new JournalGuiView(controller, "Main_Menu.fxml");
      mainStage.setScene(view.load());
      mainStage.setTitle("Main Menu");

      Stage stage = (Stage) mainMenuButton.getScene().getWindow();
      stage.close();

      controller.run();
      mainStage.show();

    });
  }

  /**
   * Is password required boolean.
   *
   * @return the boolean
   */
  public boolean isPasswordRequired() {
    return !password.equals("");
  }

  /**
   * opens the settings windows
   */
  private void openSettings() {
    Stage tertiaryStage = new Stage();

    JournalView view = new JournalGuiView(settingsController, "settings.fxml");
    tertiaryStage.setScene(view.load());
    tertiaryStage.setTitle("Settings");
    settingsController.setMaxEvents(maxEvents);
    settingsController.setMaxTasks(maxTasks);
    settingsController.run();
    tertiaryStage.setOnHidden(event -> {
      this.isFlipped = settingsController.isToggled();
      if (isFlipped) {
        flipDayBox(true);
      }
      this.maxEvents = settingsController.getMaxEvents();
      this.maxTasks = settingsController.getMaxTasks();
      this.password = settingsController.getPassword();
    });
    tertiaryStage.show();
  }

  /**
   * Calculates the percent of tasks completed
   *
   * @return the percent of tasks completed
   */
  private String percentOfCompletion() {
    int completed = 0;
    for (Task t : tasks) {
      if (t.isCompleted()) {
        completed++;
      }
    }
    double percent = ((double) completed / tasks.size()) * 100;
    return percent + "%";
  }

  /**
   * Calculates the total duration of all events
   *
   * @return the duration of all events
   */
  private String eventDuration() {
    int total = 0;
    for (Event e : events) {
      total += e.getDuration();
    }
    return total + " minutes";
  }

  /**
   * Add event.
   *
   * @param day   the day
   * @param event the event
   */
  void addEvent(VBox day, Event event) {
    day.getChildren().add(event);
    totalEventsDuration.setText("Total Events Duration: " + eventDuration());
  }

  /**
   * Add task.
   *
   * @param day  the day
   * @param task the task
   */
  void addTask(VBox day, Task task) {
    day.getChildren().add(task);
  }

  /**
   * Save data.
   */
  public void saveData() {
    String notes = notesArea.getText();
    BujoParser.toJson(
        new CombinedJson(events, tasks, notes, isFlipped, maxTasks, maxEvents, password));
  }

  /**
   * Load data.
   *
   * @param path the path
   */
  public void loadData(String path) {
    CombinedJson data =
        BujoParser.fromJson(path);
    this.events = data.events();
    this.tasks = data.tasks();
    numOfEvents.setText("Number of Events: " + events.size());
    numOfTasks.setText("Number of Tasks: " + tasks.size());
    percentOfCompletion.setText("Percent of Completion: " + percentOfCompletion());
    totalEventsDuration.setText("Total Events Duration: " + eventDuration());
    this.notesArea.setText(data.notes());
    this.maxTasks = data.maxTasks();
    this.maxEvents = data.maxEvents();
    this.password = data.password();

    for (Event e : events) {
      addEvent(pickDayBox(e.getDay()), e);
    }

    for (Task t : tasks) {
      addTask(pickDayBox(t.getDay()), t);
    }

    if (data.flipped()) {
      this.isFlipped = data.flipped();
      flipDayBox(false);
    }
  }

  /**
   * Open event creator.
   */
  public void openEventCreator() {
    eventController.setMaxDayEvents(getNumOfEventsInEachDay());
    Stage tertiaryStage = new Stage();
    JournalView view =
        new JournalGuiView(eventController, "EVENT_" + this.theme.toString() + ".fxml");
    tertiaryStage.setScene(view.load());
    tertiaryStage.setTitle("Event Creator");
    eventController.setMaxNumOfEvents(maxEvents);
    System.out.println("Before: " + events);
    eventController.run();
    tertiaryStage.setOnHidden(event -> {
      try {
        events.addAll(eventController.getEvents());
        this.addEvent(pickDayBox(events.get(events.size() - 1).getDay()),
            events.get(events.size() - 1));
      } catch (IllegalArgumentException e) {
        if (events.get(events.size() - 1) == events.get(events.size() - 2)) {
          events.remove(events.size() - 1);
        }
        System.out.println("After: " + events);
      }
    });
    tertiaryStage.show();
  }

  /**
   * Open task creator.
   */
  public void openTaskCreator() {
    taskController.setMaxDayTasks(getNumOfTasksInEachDay());
    Stage tertiaryStage = new Stage();
    JournalView view =
        new JournalGuiView(taskController, "TASK_" + this.theme.toString() + ".fxml");
    tertiaryStage.setScene(view.load());
    tertiaryStage.setTitle("Task Creator");
    taskController.setMaxNumOfTasks(maxTasks);
    taskController.run();
    tertiaryStage.setOnHidden(t -> {
      try {
        tasks.addAll(taskController.getTasks());
        this.addTask(pickDayBox(tasks.get(tasks.size() - 1).getDay()), tasks.get(tasks.size() - 1));
      } catch (IllegalArgumentException e) {
        if (tasks.get(tasks.size() - 1) == tasks.get(tasks.size() - 2)) {
          tasks.remove(tasks.size() - 1);
        }
      }
    });
    tertiaryStage.show();
  }

  /**
   * Gets num of events in each day.
   *
   * @return the num of events in each day
   */
  private ArrayList<Integer> getNumOfEventsInEachDay() {
    ArrayList<Integer> numOfEventsInEachDay = new ArrayList<>();
    for (DaysOfTheWeek day : DaysOfTheWeek.values()) {
      int counter = 0;
      for (Node n : pickDayBox(day).getChildren()) {
        if (n instanceof Event) {
          counter++;
        }
      }
      numOfEventsInEachDay.add(counter);
    }
    return numOfEventsInEachDay;
  }

  /**
   * Gets num of tasks in each day.
   *
   * @return the num of tasks in each day
   */
  private ArrayList<Integer> getNumOfTasksInEachDay() {
    ArrayList<Integer> numOfTasksInEachDay = new ArrayList<>();
    for (DaysOfTheWeek day : DaysOfTheWeek.values()) {
      int counter = 0;
      for (Node n : pickDayBox(day).getChildren()) {
        if (n instanceof Task) {
          counter++;
        }
      }
      numOfTasksInEachDay.add(counter);
    }
    return numOfTasksInEachDay;
  }

  /**
   * Pick day box v box.
   *
   * @param day the day
   * @return the v box
   */
  private VBox pickDayBox(DaysOfTheWeek day) {
    switch (day) {
      case SUNDAY:
        return sundayListBox;
      case MONDAY:
        return mondayListBox;
      case TUESDAY:
        return tuesdayListBox;
      case WEDNESDAY:
        return wednesdayListBox;
      case THURSDAY:
        return thursdayListBox;
      case FRIDAY:
        return fridayListBox;
      case SATURDAY:
        return saturdayListBox;
      default:
        throw new IllegalArgumentException("Invalid day");
    }
  }

  /**
   * Flip day box between Monday and Sunday.
   *
   * @param shouldUpdate if it should update the field isFlipped
   */
  public void flipDayBox(boolean shouldUpdate) {
    if (shouldUpdate) {
      isFlipped = !isFlipped;
    }
    List<Node> wf = new ArrayList<>(weekContainer.getChildren());

    // Clear the weekContainer
    weekContainer.getChildren().clear();

    // Reorder the elements and add them back to the weekContainer
    if (wf.size() > 1) {
      Node firstChild = wf.get(0);
      Node secondChild = wf.get(1);

      weekContainer.getChildren().addAll(secondChild, firstChild);

      for (int i = 2; i < wf.size(); i++) {
        weekContainer.getChildren().add(wf.get(i));
      }
    }
  }

  /**
   * getter for Is flipped boolean.
   *
   * @return the boolean
   */
  public boolean isFlipped() {
    return isFlipped;
  }

  /**
   * Sets theme.
   *
   * @param theme the theme
   */
  public void setTheme(Themes theme) {
    this.theme = theme;
  }

  /**
   * Gets password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }
}
