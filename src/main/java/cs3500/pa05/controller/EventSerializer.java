package cs3500.pa05.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cs3500.pa05.view.Event;
import java.io.IOException;

/**
 * The type Event serializer.
 */
public class EventSerializer extends JsonSerializer<Event> {

  /**
   * Method that can be called to ask implementation to serialize
   *
   * @param event              Value to serialize; can <b>not</b> be null.
   * @param jsonGenerator      Generator used to output resulting Json content
   * @param serializerProvider Provider that can be used to get serializers for
   *                           serializing Objects value contains, if any.
   * @throws IOException throws if there is an error reading the JSON content
   */
  @Override
  public void serialize(Event event, JsonGenerator jsonGenerator,
                        SerializerProvider serializerProvider) throws IOException {
    // Perform serialization logic for the Task class
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode eventNode = objectMapper.createObjectNode();
    eventNode.put("name", event.getName());
    eventNode.put("description", event.getDescription());
    eventNode.put("day-of-week", event.getDay().toString());
    eventNode.put("start-time", event.getStartTime());
    eventNode.put("duration", event.getDuration());
    eventNode.put("status", event.getStatus().toString());
    eventNode.put("category", event.getCategory().toString());
    // Serialize other properties as needed

    // Write the JSON representation of the task
    jsonGenerator.writeObject(eventNode);
  }
}