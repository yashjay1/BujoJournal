package cs3500.pa05.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cs3500.pa05.controller.EventDeserializer;
import cs3500.pa05.controller.EventSerializer;
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
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * The type Event.
 */
// Represents an event
@JsonSerialize(using = EventSerializer.class)
@JsonDeserialize(using = EventDeserializer.class)
public class Event extends VBox {
  /**
   * The Event status.
   */
  Label eventStatus = new Label();
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
  @JsonProperty("start-time")
  private String startTime;
  @JsonProperty("duration")
  private int duration;
  @JsonProperty
  private Category category;

  /**
   * Instantiates a new Event.
   *
   * @param name         the name
   * @param description  the description
   * @param dayOfTheWeek the day of the week
   * @param startTime    the start time
   * @param duration     the duration
   * @param category     the category
   */
  public Event(String name, String description, DaysOfTheWeek dayOfTheWeek, String startTime,
               int duration, Category category) {

    this.name = name;
    this.description = description;
    this.dayOfTheWeek = dayOfTheWeek;
    this.startTime = startTime;
    this.duration = duration;
    this.status = Status.INCOMPLETE;
    this.category = category;
    initVisuals();
  }

  /**
   * Instantiates a new Event.
   *
   * @param name         the name
   * @param description  the description
   * @param dayOfTheWeek the day of the week
   * @param status       the status
   * @param startTime    the start time
   * @param duration     the duration
   * @param category     the category
   */
  public Event(String name, String description, DaysOfTheWeek dayOfTheWeek, Status status,
               String startTime, int duration, Category category) {

    this.name = name;
    this.description = description;
    this.dayOfTheWeek = dayOfTheWeek;
    this.startTime = startTime;
    this.duration = duration;
    this.status = status;
    this.category = category;
    initVisuals();
  }

  /**
   * Instantiates a new Event.
   */
  public Event() {
    this.status = Status.INCOMPLETE;
    initVisuals();
  }


  /**
   * Init visuals.
   */
  void initVisuals() {
    Label startLabel = new Label();
    startLabel.setPrefSize(170 / 2, 15);
    startLabel.setText(this.startTime);
    // Text properties
    startLabel.setStyle(
        "-fx-text-fill: #000000;" + " -fx-font-size: 15px;" + "-fx-font-type: bold;"
            + " -fx-alignment: center;" + " -fx-border-color: #001A23;"
            + " -fx-border-width: 2px;");

    Label durationLabel = new Label();
    durationLabel.setPrefSize(170 / 2, 15);
    durationLabel.setText(this.duration + " mins");
    // Text properties
    durationLabel.setStyle(
        "-fx-text-fill: #000000;" + " -fx-font-size: 15px;" + "-fx-font-type: bold;"
            + " -fx-alignment: center;" + " -fx-border-color: #001A23;"
            + " -fx-border-width: 2px;");

    HBox timeBox = new HBox();
    timeBox.getChildren().add(startLabel);
    timeBox.getChildren().add(durationLabel);
    Label eventLogo = new Label();
    eventLogo.setPrefSize(50, 15);
    eventLogo.setText("E");
    eventLogo.setStyle(
        "-fx-background-color: #000000; " + "-fx-text-fill: #FFFFFF;"
            + " -fx-font-size: 15px;"
            + "-fx-font-type: bold;" + " -fx-alignment: center;"
            + " -fx-border-color: #001A23;"
            + " -fx-border-width: 2px;");

    Label eventName = new Label();
    eventName.setPrefSize(170, 15);
    eventName.setText(this.name);
    // Text properties
    eventName.setStyle(
        "-fx-background-color: " + colorCategory() + "; " + "-fx-text-fill: #FFFFFF;"
            + " -fx-font-size: 15px;" + "-fx-font-type: bold;" + " -fx-alignment: center;"
            + " -fx-border-color: #001A23;" + " -fx-border-width: 2px;");

    HBox nameBox = new HBox();
    nameBox.getChildren().add(eventLogo);
    nameBox.getChildren().add(eventName);

    eventStatus.setPrefSize(170, 15);
    eventStatus.setText(this.status.toString());
    // Text properties
    eventStatus.setStyle(
        "-fx-background-color: #000000;" + " -fx-text-fill: #FFFFFF;" + " -fx-font-size: 10px;"
            + " -fx-alignment: center;" + " -fx-border-color: #001A23;" + " -fx-border-width: 2px;"
            + " -fx-font-family: bold");

    this.setFillWidth(true);
    this.getChildren().add(nameBox);
    this.getChildren().add(eventStatus);
    this.getChildren().add(timeBox);

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
        + "Day of the Week: " + this.dayOfTheWeek.toString() + "\n" + "Start Time: "
        + this.startTime + "\n" + "Duration: " + this.duration + " mins" + "\n" + "Status: "
        + this.status.toString() + "\n" + "Category: " + this.category.toString());

    Tooltip.install(this, tooltip);
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
   * Mark as complete.
   */
  public void markAsComplete() {
    if (this.status == Status.COMPLETE) {
      this.status = Status.INCOMPLETE;
    } else {
      this.status = Status.COMPLETE;
    }
    eventStatus.setText(this.status.toString());
    tooltipMaker();
  }

  private void openInfoWindow() {

    VBox infoBox = new VBox();
    infoBox.setAlignment(Pos.CENTER);

    Label nameLabel = new Label("Name: " + this.name);
    nameLabel.setStyle("-fx-font-size: 16px");
    Label statusLabel = new Label("Status: " + this.status.toString());
    Label dayLabel = new Label("Day of the Week: " + this.dayOfTheWeek.toString());
    Label categoryLabel = new Label("Category: " + this.category.toString());
    Label startTimeLabel = new Label("Start Time: " + this.startTime);
    Label durationLabel = new Label("Duration: " + this.duration);
    Label descriptionLabel = new Label();

    TextFlow descriptionFlow = new TextFlow();
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
    descriptionLabel.setGraphic(descriptionFlow);


    infoBox.getChildren()
        .addAll(nameLabel, statusLabel, dayLabel, categoryLabel, startTimeLabel, durationLabel,
            descriptionLabel);

    Scene infoScene = new Scene(infoBox, 500, 500);
    Stage infoStage = new Stage();
    infoStage.setScene(infoScene);
    infoStage.setTitle("Event Info: " + this.name);
    infoStage.show();
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
   * Gets start time.
   *
   * @return the start time
   */
  public String getStartTime() {
    return this.startTime;
  }

  /**
   * Gets duration.
   *
   * @return the duration
   */
  public int getDuration() {
    return this.duration;
  }

  /**
   * Gets status.
   *
   * @return the status
   */
  public Status getStatus() {
    return status;
  }

  /**
   * Gets category.
   *
   * @return the category
   */
  public Category getCategory() {
    return category;
  }

}
