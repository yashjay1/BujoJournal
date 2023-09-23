package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CombinedJsonTest {

  CombinedJson json = new CombinedJson(null, null, "Some notes", true, 7, 9, "123");


  @Test
  void events() {
    assertNull(json.events());
  }

  @Test
  void tasks() {
    assertNull(json.tasks());
  }

  @Test
  void notes() {
    assertEquals("Some notes", json.notes());
  }

  @Test
  void flipped() {
    assertTrue(json.flipped());
  }

  @Test
  void maxTasks() {
    assertEquals(7, json.maxTasks());
  }

  @Test
  void maxEvents() {
    assertEquals(9, json.maxEvents());
  }

  @Test
  void password() {
    assertEquals("123", json.password());
  }
}