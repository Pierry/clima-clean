package io.github.pierry.climaclean.domain;

import com.google.gson.annotations.SerializedName;
import java.text.NumberFormat;
import java.util.Locale;

public class Weather {

  private double temp;
  @SerializedName("temp_min") private double min;
  @SerializedName("temp_max") private double max;
  private String weatherDescription;

  private static final double K = 273.15;

  public double getTemp() {
    return temp;
  }

  public double getMin() {
    return min;
  }

  public double getMax() {
    return max;
  }

  public void setTemp(double temp) {
    this.temp = temp;
  }

  public void setMin(double min) {
    this.min = min;
  }

  public void setMax(double max) {
    this.max = max;
  }

  public String getWeatherDescription() {
    return weatherDescription;
  }

  public void setWeatherDescription(String weatherDescription) {
    this.weatherDescription = weatherDescription;
  }

  public String getTempFormatted() {
    double tempCelsius = temp - K;
    return format(tempCelsius);
  }

  public String getMinFormatted() {
    double minCelsius = min - K;
    return "Min " + format(minCelsius);
  }

  public String getMaxFormatted() {
    double maxCelsius = max - K;
    return "Max " + format(maxCelsius);
  }

  public static String format(double value) {
    final NumberFormat numberFormatter = NumberFormat.getInstance(Locale.getDefault());
    numberFormatter.setMinimumFractionDigits(0);
    numberFormatter.setMaximumFractionDigits(0);
    return numberFormatter.format(value) + (char) 0x00B0;
  }
}
