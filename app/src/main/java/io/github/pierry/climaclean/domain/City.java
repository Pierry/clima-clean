package io.github.pierry.climaclean.domain;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity public class City {

  @NonNull @PrimaryKey private String name;
  @Embedded private Weather weather;

  public City() {
  }

  @Ignore public City(String name, Weather weather) {
    this.name = name;
    this.weather = weather;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Weather getWeather() {
    return weather;
  }

  public void setWeather(Weather weather) {
    this.weather = weather;
  }
}
