package io.github.pierry.climaclean.repository.viewmodel;

import io.github.pierry.climaclean.domain.Weather;
import java.text.NumberFormat;
import java.util.Locale;

public class CityWeatherViewModel {

  private long id;
  private String name;
  private long updatedAt;
  private long dateTime;
  private double temp;
  private double min;
  private double max;
  private String description;
  private String title;
  private long cityId;

  private static final double K = 273.15;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(long updatedAt) {
    this.updatedAt = updatedAt;
  }

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
    return Weather.format(tempCelsius);
  }

  public String getMinFormatted() {
    double minCelsius = min - K;
    return "Min " + Weather.format(minCelsius);
  }

  public String getMaxFormatted() {
    double maxCelsius = max - K;
    return "Max " + Weather.format(maxCelsius);
  }

  public long getCityId() {
    return cityId;
  }

  public void setCityId(long cityId) {
    this.cityId = cityId;
  }

}
