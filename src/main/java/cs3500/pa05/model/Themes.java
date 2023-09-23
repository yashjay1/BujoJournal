package cs3500.pa05.model;

/**
 * The enum Themes.
 */
public enum Themes {
  /**
   * Blue themes.
   */
  BLUE,
  /**
   * Orange themes.
   */
  ORANGE,
  /**
   * Pinkblue themes.
   */
  PINKBLUE,
  /**
   * Pistachio themes.
   */
  PISTACHIO;

  /**
   * To string array string [ ].
   *
   * @return the string [ ]
   */
  public static String[] toStringArray() {
    String[] themes = new String[Themes.values().length];
    for (int i = 0; i < Themes.values().length; i++) {
      themes[i] = Themes.values()[i].toString();
    }
    return themes;
  }
}
