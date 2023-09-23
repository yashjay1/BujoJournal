package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CategoryTest {

  @Test
  void toStringTest() {
    assertEquals("HOME", Category.HOME.toString());
    assertEquals("WORK", Category.WORK.toString());
    assertEquals("SCHOOL", Category.SCHOOL.toString());
    assertEquals("OTHER", Category.OTHER.toString());
  }
}