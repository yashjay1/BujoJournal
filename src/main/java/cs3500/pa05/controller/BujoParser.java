package cs3500.pa05.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.CombinedJson;
import cs3500.pa05.view.JournalGuiView;
import cs3500.pa05.view.JournalView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.stage.Stage;

/**
 * A type of Bujo parser.
 */
public class BujoParser {
  private static final ObjectMapper mapper = new ObjectMapper();

  /**
   * To convert a record to json.
   *
   * @param record the record
   */
  public static void toJson(CombinedJson record) {
    String json = null;

    try {
      json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(record);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    try (FileWriter writer = new FileWriter(
        "/Users/yash/IdeaProjects/pa05-bujoballerz/exampleJSON.bujo")) {
      writer.write(json);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   * From json file to a record.
   *
   * @param path the path
   * @return the combined json
   */
  public static CombinedJson fromJson(String path) {

    JsonParser parser = null;
    try {
      parser = mapper.getFactory().createParser(new File(path));
    } catch (IOException e) {
      Stage stage = new Stage();
      InvalidItemController controller = new InvalidItemController();
      JournalView view = new JournalGuiView(controller, "InvalidEvent.fxml");
      stage.setScene(view.load());
      controller.setMessage("Invalid File / File Path");
      stage.setTitle("Invalid File");
      controller.run();
      stage.show();
    }
    try {
      return parser.readValueAs(CombinedJson.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
