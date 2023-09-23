package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * The interface Journal view.
 */
public interface JournalView {
  /**
   * Load scene.
   *
   * @return the scene
   * @throws IllegalStateException the illegal state exception
   */
  Scene load() throws IllegalStateException;
}
