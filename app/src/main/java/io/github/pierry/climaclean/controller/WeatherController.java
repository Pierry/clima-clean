package io.github.pierry.climaclean.controller;

import io.github.pierry.climaclean.api.SyncApi;
import io.github.pierry.climaclean.api.interfaces.ISyncApi;
import io.github.pierry.climaclean.controller.interfaces.IWeatherInteractor;
import io.github.pierry.climaclean.domain.City;
import io.github.pierry.climaclean.domain.Weather;

public class WeatherController implements IWeatherInteractor {

  private CityController cityController;

  public WeatherController(CityController cityController) {
    this.cityController = cityController;
  }

  public void fetch(String city) {
    ISyncApi api = new SyncApi();
    api.get(this, city);
  }

  @Override public void found(Weather weather, String cityName) {
    City newCity = new City(cityName, weather);
    cityController.add(newCity);
  }

  @Override public void error(int code, String msg) {

  }
}
