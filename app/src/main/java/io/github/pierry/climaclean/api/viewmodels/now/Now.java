package io.github.pierry.climaclean.api.viewmodels.now;

import com.google.gson.annotations.SerializedName;
import io.github.pierry.climaclean.api.viewmodels.forecast.Clouds;
import io.github.pierry.climaclean.api.viewmodels.forecast.WeatherDescription;
import io.github.pierry.climaclean.api.viewmodels.forecast.Wind;
import java.util.List;

public class Now {

  private Coord coord;
  @SerializedName("weather") private List<WeatherDetails> weatherDetails;
  @SerializedName("main") private WeatherDescription weatherDescription;
  private Wind wind;
  private Clouds clouds;
  @SerializedName("dt") private long dateTime;
  @SerializedName("name") private String name;
  @SerializedName("id") private long id;

  public Coord getCoord() {
    return coord;
  }

  public void setCoord(Coord coord) {
    this.coord = coord;
  }

  public List<WeatherDetails> getWeatherDetails() {
    return weatherDetails;
  }

  public void setWeatherDetails(List<WeatherDetails> weatherDetails) {
    this.weatherDetails = weatherDetails;
  }

  public WeatherDescription getWeatherDescription() {
    return weatherDescription;
  }

  public void setWeatherDescription(WeatherDescription weatherDescription) {
    this.weatherDescription = weatherDescription;
  }

  public Wind getWind() {
    return wind;
  }

  public void setWind(Wind wind) {
    this.wind = wind;
  }

  public Clouds getClouds() {
    return clouds;
  }

  public void setClouds(Clouds clouds) {
    this.clouds = clouds;
  }

  public long getDateTime() {
    return dateTime;
  }

  public void setDateTime(long dateTime) {
    this.dateTime = dateTime;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
