package cs3500.pa05.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.Category;
import cs3500.pa05.model.DaysOfTheWeek;
import cs3500.pa05.model.Status;
import cs3500.pa05.view.Event;
import java.io.IOException;

/**
 * The type Event deserializer.
 */
public class EventDeserializer extends JsonDeserializer<Event> {

  /**
   * Deserialize event.
   *
   * @param jsonParser             Parsed used for reading JSON content
   * @param deserializationContext Context that can be used to access information about
   *                               this deserialization activity.
   * @return the event
   * @throws IOException throws if there is an error reading the JSON content
   */
  @Override
  public Event deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);

    // Retrieve properties from the JSON node
    String name = node.get("name").asText();
    String description = node.get("description").asText();
    String dayOfWeek = node.get("day-of-week").asText();
    String status = node.get("status").asText();
    String startTime = node.get("start-time").asText();
    int duration = node.get("duration").asInt();
    String category = node.get("category").asText();

    Event event =
        new Event(name, description, DaysOfTheWeek.valueOf(dayOfWeek), Status.valueOf(status),
            startTime, duration, Category.valueOf(category));

    return event;
  }
}

