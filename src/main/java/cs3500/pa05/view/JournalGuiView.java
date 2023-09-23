package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * The type Journal gui view.
 */
public class JournalGuiView implements JournalView {
  private final FXMLLoader loader;

  /**
   * Instantiates a new Journal gui view.
   *
   * @param controller the controller
   * @param fxmlFile   the fxml file
   */
  public JournalGuiView(JournalController controller, String fxmlFile) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource(fxmlFile));
    this.loader.setController(controller);
  }

  /**
   * Load scene.
   *
   * @return the scene
   */
  public Scene load() {
    // load the layout
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException(exc);
    }
  }
}
