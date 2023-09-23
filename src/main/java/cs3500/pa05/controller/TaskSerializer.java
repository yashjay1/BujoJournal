package cs3500.pa05.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cs3500.pa05.view.Task;
import java.io.IOException;

/**
 * The type Task serializer.
 */
public class TaskSerializer extends JsonSerializer<Task> {

  /**
   * Method that can be called to ask implementation to serialize
   *
   * @param task               Value to serialize; can <b>not</b> be null.
   * @param jsonGenerator      Generator used to output resulting Json content
   * @param serializerProvider Provider that can be used to get serializers for
   *                           serializing Objects value contains, if any.
   * @throws IOException throws if there is an error reading the JSON content
   */
  @Override
  public void serialize(Task task, JsonGenerator jsonGenerator,
                        SerializerProvider serializerProvider) throws IOException {
    // Perform serialization logic for the Task class
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode taskNode = objectMapper.createObjectNode();
    taskNode.put("name", task.getName());
    taskNode.put("description", task.getDescription());
    taskNode.put("day-of-week", task.getDay().toString());
    taskNode.put("status", task.getStatus().toString());
    taskNode.put("category", task.getCategory().toString());

    // Write the JSON representation of the task
    jsonGenerator.writeObject(taskNode);
  }
}

