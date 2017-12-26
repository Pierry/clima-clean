package io.github.pierry.climaclean.api.viewmodels.forecast;

import com.google.gson.annotations.SerializedName;

public class Details {

  @SerializedName("dt") private long dateTime;
  @SerializedName("main") private WeatherDetails weatherDetails;
  @SerializedName("weather") private WeatherDescription weatherDescription;
  private Clouds clouds;
  private Wind wind;

  public long getDateTime() {
    return dateTime;
  }

  public void setDateTime(long dateTime) {
    this.dateTime = dateTime;
  }

  public WeatherDetails getWeatherDetails() {
    return weatherDetails;
  }

  public void setWeatherDetails(WeatherDetails weatherDetails) {
    this.weatherDetails = weatherDetails;
  }

  public WeatherDescription getWeatherDescription() {
    return weatherDescription;
  }

  public void setWeatherDescription(WeatherDescription weatherDescription) {
    this.weatherDescription = weatherDescription;
  }

  public Clouds getClouds() {
    return clouds;
  }

  public void setClouds(Clouds clouds) {
    this.clouds = clouds;
  }

  public Wind getWind() {
    return wind;
  }

  public void setWind(Wind wind) {
    this.wind = wind;
  }
}
