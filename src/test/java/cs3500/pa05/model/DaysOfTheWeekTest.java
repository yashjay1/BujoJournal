package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DaysOfTheWeekTest {

  @Test
  void toStringTest() {
    assertEquals("SUNDAY", DaysOfTheWeek.SUNDAY.toString());
    assertEquals("MONDAY", DaysOfTheWeek.MONDAY.toString());
    assertEquals("TUESDAY", DaysOfTheWeek.TUESDAY.toString());
    assertEquals("WEDNESDAY", DaysOfTheWeek.WEDNESDAY.toString());
    assertEquals("THURSDAY", DaysOfTheWeek.THURSDAY.toString());
    assertEquals("FRIDAY", DaysOfTheWeek.FRIDAY.toString());
    assertEquals("SATURDAY", DaysOfTheWeek.SATURDAY.toString());
  }
}

