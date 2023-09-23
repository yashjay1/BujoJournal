package cs3500.pa05.view;

import cs3500.pa05.controller.InvalidItemController;
import cs3500.pa05.controller.JournalController;
import cs3500.pa05.controller.MainMenuController;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The type Journal.
 */
public class Journal extends Application {

  private final String fxmlFile = "Main_Menu.fxml";

  /**
   * The entry point of application.
   *
   * @param primaryStage the primary stage
   */
  @Override
  public void start(Stage primaryStage) throws IOException {
    primaryStage.setTitle("Journal");

    JournalController splashController = new InvalidItemController();
    JournalView splashView = new JournalGuiView(splashController, "splash_screen.fxml");
    primaryStage.setScene(splashView.load());

    primaryStage.show();

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
      primaryStage.close();

      JournalController mainController = new MainMenuController();
      JournalView mainView = new JournalGuiView(mainController, fxmlFile);
      primaryStage.setScene(mainView.load());
      mainController.run();

      primaryStage.show();
    }));
    timeline.setCycleCount(1);
    timeline.play();
  }

}