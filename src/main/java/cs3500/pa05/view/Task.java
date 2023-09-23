package cs3500.pa05.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cs3500.pa05.controller.TaskDeserializer;
import cs3500.pa05.controller.TaskSerializer;
import cs3500.pa05.model.Category;
import cs3500.pa05.model.DaysOfTheWeek;
import cs3500.pa05.model.Status;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * The type Task.
 */
// Represents a task
@JsonSerialize(using = TaskSerializer.class)
@JsonDeserialize(using = TaskDeserializer.class)
public class Task extends VBox {
  /**
   * The Task status.
   */
  Label taskStatus = new Label();
  /**
   * The Tooltip.
   */
  Tooltip tooltip = new Tooltip();
  @JsonProperty("name")
  private String name;
  @JsonProperty("description")
  private String description;
  @JsonProperty("day-of-week")
  private DaysOfTheWeek dayOfTheWeek;
  @JsonProperty("status")
  private Status status;
  @JsonProperty("category")
  private Category category;

  /**
   * Instantiates a new Task.
   *
   * @param name         the name
   * @param description  the description
   * @param dayOfTheWeek the day of the week
   * @param category     the category
   */
  public Task(String name, String description, DaysOfTheWeek dayOfTheWeek, Category category) {
    this.name = name;
    this.description = description;
    this.dayOfTheWeek = dayOfTheWeek;
    this.status = Status.INCOMPLETE;
    this.category = category;
    initVisuals();
  }

  /**
   * Instantiates a new Task.
   *
   * @param name         the name
   * @param description  the description
   * @param dayOfTheWeek the day of the week
   * @param status       the status
   * @param category     the category
   */
  public Task(String name, String description, DaysOfTheWeek dayOfTheWeek, Status status,
              Category category) {
    this.name = name;
    this.description = description;
    this.dayOfTheWeek = dayOfTheWeek;
    this.status = status;
    this.category = category;
    initVisuals();
  }

  /**
   * Instantiates a new Task.
   */
  public Task() {
    this.status = Status.INCOMPLETE;
    initVisuals();
  }

  /**
   * Gets day of the week.
   *
   * @return the day of the week
   */
  public DaysOfTheWeek getDayOfTheWeek() {
    return dayOfTheWeek;
  }

  /**
   * Init visuals.
   */
  void initVisuals() {

    Label taskLogo = new Label();
    taskLogo.setPrefSize(50, 15);
    taskLogo.setText("T");
    taskLogo.setStyle(
        "-fx-background-color: #000000; " + "-fx-text-fill: #FFFFFF;" + " -fx-font-size: 15px;"
            + "-fx-font-type: bold;" + " -fx-alignment: center;" + " -fx-border-color: #001A23;"
            + " -fx-border-width: 2px;");

    Label taskName = new Label();
    taskName.setPrefSize(170, 15);
    taskName.setText(this.name);
    // Text properties
    taskName.setStyle(
        "-fx-background-color: " + colorCategory() + "; " + "-fx-text-fill: #FFFFFF;"
            + " -fx-font-size: 15px;" + "-fx-font-type: bold;" + " -fx-alignment: center;"
            + " -fx-border-color: #001A23;" + " -fx-border-width: 2px;");

    HBox nameBox = new HBox();
    nameBox.getChildren().add(taskLogo);
    nameBox.getChildren().add(taskName);


    taskStatus.setPrefSize(170, 15);
    taskStatus.setText(this.status.toString());
    // Text properties
    taskStatus.setStyle(
        "-fx-background-color: #000000;" + " -fx-text-fill: #FFFFFF;" + " -fx-font-size: 10px;"
            + " -fx-alignment: center;" + " -fx-border-color: #001A23;" + " -fx-border-width: 2px;"
            + " -fx-font-family: bold");

    this.setFillWidth(true);
    this.getChildren().add(nameBox);
    this.getChildren().add(taskStatus);

    tooltipMaker();

    this.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.isAltDown()) {
          markAsComplete();
        } else {
          openInfoWindow();
        }
      }
    });
  }

  private void tooltipMaker() {
    tooltip.setText("Name: " + this.name + "\n" + "Description: " + this.description + "\n"
        + "Day of the Week: " + this.dayOfTheWeek.toString() + "\n" + "Status: "
        + this.status.toString() + "\n" + "Category: " + this.category.toString());

    Tooltip.install(this, tooltip);
  }

  /**
   * Mark as complete.
   */
  public void markAsComplete() {
    if (this.status == Status.COMPLETE) {
      this.status = Status.INCOMPLETE;
    } else {
      this.status = Status.COMPLETE;
    }
    taskStatus.setText(this.status.toString());
    tooltipMaker();

  }

  private String colorCategory() {
    String color = "";
    switch (this.category) {
      case HOME:
        color = "#1a8f00";
        break;
      case WORK:
        color = "#385dcf";
        break;
      case SCHOOL:
        color = "#daa520";
        break;
      case OTHER:
        color = "#868686";
        break;
      default:
        color = "#FFFFFF";
        break;
    }
    return color;
  }


  /**
   * Gets day.
   *
   * @return the day
   */
  public DaysOfTheWeek getDay() {
    return this.dayOfTheWeek;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets status.
   *
   * @return the status
   */
  public Status getStatus() {
    return this.status;
  }

  /**
   * Gets category.
   *
   * @return the category
   */
  public Category getCategory() {
    return this.category;
  }

  /**
   * Is completed boolean.
   *
   * @return the boolean
   */
  public boolean isCompleted() {
    return this.status.equals(Status.COMPLETE);
  }


  private void openInfoWindow() {
    VBox infoBox = new VBox();
    infoBox.setAlignment(Pos.CENTER);
    Label nameLabel = new Label("Name: " + this.name);
    nameLabel.setStyle("-fx-font-size: 16px;");
    Label dayLabel = new Label("Day of the Week: " + this.dayOfTheWeek);
    Label statusLabel = new Label("Status: " + this.status);
    Label categoryLabel = new Label("Category: " + this.category);

    TextFlow descriptionFlow = new TextFlow();
    descriptionFlow.setTextAlignment(TextAlignment.CENTER); // Set text alignment to center
    String[] words = this.description.split(" ");

    for (String word : words) {
      if (word.matches("(http|https)://[a-zA-Z0-9./?=&#]+")) {
        Hyperlink hyperlink = new Hyperlink(word);
        hyperlink.setOnAction(event -> {
          String url = hyperlink.getText();

          try {
            URI uri = new URI(url);
            Desktop desktop = Desktop.getDesktop();

            if (desktop.isSupported(Desktop.Action.BROWSE)) {
              desktop.browse(uri);
            } else {
              System.out.println("Opening a web browser is not supported on this platform.");
            }
          } catch (URISyntaxException | IOException e) {
            System.out.println("Error opening the URL: " + e.getMessage());
          }
        });

        descriptionFlow.getChildren().add(hyperlink);
      } else {
        Text text = new Text(word + " ");
        descriptionFlow.getChildren().add(text);
      }
    }

    infoBox.getChildren().addAll(nameLabel, statusLabel, dayLabel, categoryLabel, descriptionFlow);
    Scene infoScene = new Scene(infoBox, 500, 500);
    Stage infoStage = new Stage();
    infoStage.setScene(infoScene);
    infoStage.setTitle("Task Info: " + this.name);
    infoStage.show();
  }
}
