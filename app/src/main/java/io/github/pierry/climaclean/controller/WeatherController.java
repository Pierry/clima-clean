package io.github.pierry.climaclean.controller;

import io.github.pierry.climaclean.api.SyncApi;
import io.github.pierry.climaclean.api.interfaces.ISyncApi;
import io.github.pierry.climaclean.controller.interfaces.IWeatherInteractor;
import io.github.pierry.climaclean.domain.City;
import io.github.pierry.climaclean.domain.Weather;
import org.threeten.bp.Instant;

public class WeatherController implements IWeatherInteractor {

  private CityController cityController;
  private static final int MINUTE = 60000;

  public WeatherController(CityController cityController) {
    this.cityController = cityController;
  }

  public void fetch(City city) {
    long now = Instant.now().toEpochMilli();
    long updatedAt = city.getWeather().getUpdatedAt();
    long dif = now - updatedAt;
    if (dif > (MINUTE * 60)) {
      search(city.getName());
    }
  }

  public void search(String search) {
    ISyncApi api = new SyncApi();
    api.get(this, search);
  }

  @Override public void found(Weather weather, String cityName) {
    weather.setUpdatedAt(Instant.now().toEpochMilli());
    City newCity = new City(cityName, weather);
    cityController.add(newCity);
  }

  @Override public void error(int code, String msg) {

  }
}
