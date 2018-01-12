package io.github.pierry.climaclean.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import java.text.NumberFormat;
import java.util.Locale;

@Entity(foreignKeys = @ForeignKey(entity = City.class, parentColumns = "id", childColumns = "cityId")) public class Weather {

  @PrimaryKey private long dateTime;
  private double temp;
  private double min;
  private double max;
  private String description;
  private String title;
  private long cityId;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public long getDateTime() {
    return dateTime;
  }

  public void setDateTime(long dateTime) {
    this.dateTime = dateTime;
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

  public long getCityId() {
    return cityId;
  }

  public void setCityId(long cityId) {
    this.cityId = cityId;
  }

  public static String format(double value) {
    final NumberFormat numberFormatter = NumberFormat.getInstance(Locale.getDefault());
    numberFormatter.setMinimumFractionDigits(0);
    numberFormatter.setMaximumFractionDigits(0);
    return numberFormatter.format(value) + (char) 0x00B0;
  }
}
