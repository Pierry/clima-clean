package io.github.pierry.climaclean.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

  private static final String HUMANIZER = "dd' de 'MMM', 'HH:mm";

  public static String humanizer(long d) {
    try {
      Date parsedDate = new Date(d);
      SimpleDateFormat dateFormat = new SimpleDateFormat(HUMANIZER);
      String formatted = dateFormat.format(parsedDate);
      return formatted;
    } catch (Exception e) {
      throw e;
    }
  }
}
