package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.view.Event;
import cs3500.pa05.view.Task;
import java.util.List;

/**
 * The type Combined json.
 */
public record CombinedJson(
    @JsonProperty("events-list") List<Event> events,
    @JsonProperty("task-list") List<Task> tasks,
    @JsonProperty("notes") String notes,
    @JsonProperty("flipped") boolean flipped,
    @JsonProperty("max-tasks") int maxTasks,
    @JsonProperty("max-events") int maxEvents,
    @JsonProperty("password") String password
) {
}
