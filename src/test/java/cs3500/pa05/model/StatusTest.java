package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StatusTest {

  @Test
  void testToString() {
    assertEquals("COMPLETE", Status.COMPLETE.toString());
    assertEquals("INCOMPLETE", Status.INCOMPLETE.toString());
  }
}