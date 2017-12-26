package io.github.pierry.climaclean.controller;

import io.github.pierry.climaclean.api.SyncApi;
import io.github.pierry.climaclean.api.interfaces.ISyncApi;
import io.github.pierry.climaclean.api.viewmodels.forecast.Details;
import io.github.pierry.climaclean.api.viewmodels.forecast.Forecast;
import io.github.pierry.climaclean.api.viewmodels.now.Now;
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

  public void fetchNow(City city) {
    long now = Instant.now().toEpochMilli();
    long updatedAt = city.getUpdatedAt();
    long dif = now - updatedAt;
    if (dif > (MINUTE * 60)) {
      search(city.getName());
    }
  }

  public void search(String search) {
    ISyncApi api = new SyncApi();
    api.cityNow(this, search);
  }

  public void fetchForecast(String city) {
    ISyncApi api = new SyncApi();
    api.forecast(this, city);
  }

  @Override public void found(Now now) {
    Weather weather = new Weather();
    weather.setTemp(now.getWeatherDetails().getTemp());
    weather.setDescription(now.getWeatherDescription().getDescription());
    weather.setTitle(now.getWeatherDescription().getTitle());
    weather.setMin(now.getWeatherDetails().getTempMin());
    weather.setMax(now.getWeatherDetails().getTempMax());
    weather.setDateTime(now.getDateTime());
    City newCity = new City(now.getName(), weather);
    newCity.setUpdatedAt(Instant.now().toEpochMilli());
    cityController.add(newCity);
  }

  @Override public void error(int code, String msg) {

  }

  @Override public void forecast(Forecast body) {
    City city = new City();
    city.setUpdatedAt(Instant.now().toEpochMilli());
    city.setName(body.getCity().getName());
    for (Details d : body.getDetails()) {
      Weather weather = new Weather();
      weather.setDateTime(d.getDateTime());
      weather.setMax(d.getWeatherDetails().getTempMax());
      weather.setMin(d.getWeatherDetails().getTempMin());
      weather.setTitle(d.getWeatherDescription().getTitle());
      weather.setDescription(d.getWeatherDescription().getDescription());
      weather.setTemp(d.getWeatherDetails().getTemp());
      city.addWeather(weather);
      cityController.add(city);
    }
  }
}
