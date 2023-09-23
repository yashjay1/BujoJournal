package cs3500.pa05.controller;

import cs3500.pa05.model.Themes;
import cs3500.pa05.view.JournalGuiView;
import cs3500.pa05.view.JournalView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The Main menu controller.
 */
public class MainMenuController implements JournalController {

  @FXML
  private TextField fileField;
  @FXML
  private Button goButton;
  @FXML
  private TextField name;
  @FXML
  private ComboBox themeSelector;
  private Themes chosenTheme;

  @Override
  public void run() {

    goButton.setOnAction(e -> openWeekView());
    themeSelector.getItems().addAll(Themes.toStringArray());

  }

  /**
   * Opens the week view window.
   */
  public void openWeekView() {
    System.out.println(name.getText());
    String file = fileField.getText();
    Stage secondaryStage = new Stage();
    JournalControllerImpl controller = new JournalControllerImpl(name.getText());

    try {
      JournalView view = new JournalGuiView(controller, selectedTheme());
      secondaryStage.setScene(view.load());
      secondaryStage.setTitle("Journal");
      controller.run();
      controller.setTheme(getChosenTheme());
      controller.loadData(file);
      Stage stage = (Stage) goButton.getScene().getWindow();
      System.out.println(controller.isPasswordRequired());
      stage.close();

      if (controller.isPasswordRequired()) {
        Stage passwordStage = new Stage();
        PasswordController passwordController = new PasswordController();
        JournalView passwordView = new JournalGuiView(passwordController, "password_screen.fxml");
        passwordStage.setScene(passwordView.load());
        passwordController.setCorrectPass(controller.getPassword());
        passwordController.run();
        passwordStage.show();
        passwordStage.setOnHidden(event -> {
          if (passwordController.isAccessAllowed()) {
            secondaryStage.show();
          }
        });
      }
    } catch (NullPointerException e) {
      Stage stage = new Stage();
      InvalidItemController invalidItemController = new InvalidItemController();
      JournalView view = new JournalGuiView(invalidItemController, "InvalidEvent.fxml");
      stage.setScene(view.load());
      invalidItemController.setMessage("Select a theme!");
      stage.setTitle("No theme selected!");
      invalidItemController.run();
      stage.show();
    }
  }

  private String selectedTheme() {
    this.chosenTheme = Themes.valueOf((String) themeSelector.getValue());
    return "GUI_" + themeSelector.getValue() + ".fxml";
  }

  private Themes getChosenTheme() {
    return this.chosenTheme;
  }
}
