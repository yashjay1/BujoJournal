package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class ThemesTest {

  @Test
  void toStringArray() {
    String[] themes = new String[Themes.values().length];
    for (int i = 0; i < Themes.values().length; i++) {
      themes[i] = Themes.values()[i].toString();
    }
    assertArrayEquals(themes, Themes.toStringArray());
  }
}