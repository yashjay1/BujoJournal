package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

class JsonUtilsTest {
  @Test
  void serializeRecord() {
    CombinedJson record = new CombinedJson(null, null, "Some notes", true, 7, 9, "123");
    JsonNode node = JsonUtils.serializeRecord(record);
    assertEquals("Some notes", node.get("notes").asText());
    assertTrue(node.get("flipped").asBoolean());
  }

  @Test
  void illegalSerializeRecord() {
    Record invalidRec = null;
    JsonUtils.serializeRecord(invalidRec);
    //assertThrows(IllegalArgumentException.class, () -> JsonUtils.serializeRecord(invalidRec));
  }
}
