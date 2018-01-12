package io.github.pierry.climaclean.controller;

import android.content.Context;
import android.util.Log;
import com.arasthel.asyncjob.AsyncJob;
import io.github.pierry.climaclean.api.SyncApi;
import io.github.pierry.climaclean.api.interfaces.ISyncApi;
import io.github.pierry.climaclean.api.viewmodels.forecast.Details;
import io.github.pierry.climaclean.api.viewmodels.forecast.Forecast;
import io.github.pierry.climaclean.api.viewmodels.now.Now;
import io.github.pierry.climaclean.common.Const;
import io.github.pierry.climaclean.controller.interfaces.IWeatherInteractor;
import io.github.pierry.climaclean.domain.City;
import io.github.pierry.climaclean.domain.Weather;
import org.threeten.bp.Instant;

public class WeatherController extends BaseController implements IWeatherInteractor {

  private CityController cityController;
  private static final int MINUTE = 60000;

  public WeatherController(Context context, CityController cityController) {
    super(context);
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

  void save(Weather weather) {
    new AsyncJob.AsyncJobBuilder<Boolean>().doInBackground(() -> {
      db().weatherRepository().save(weather);
      return true;
    }).create().start();
  }

  @Override public void found(Now now) {
    Weather weather = new Weather();
    weather.setTemp(now.getWeatherDetails().get(0).getTemp());
    weather.setDescription(now.getWeatherDescription().getDescription());
    weather.setTitle(now.getWeatherDescription().getTitle());
    weather.setMin(now.getWeatherDetails().get(0).getTempMin());
    weather.setMax(now.getWeatherDetails().get(0).getTempMax());
    weather.setDateTime(now.getDateTime());
    weather.setCityId(now.getId());
    save(weather);
    City newCity = new City(now.getId(), now.getName(), Instant.now().toEpochMilli());
    cityController.save(newCity);
  }

  @Override public void error(int code, String msg) {
    Log.e(Const.TAG, String.valueOf(code) + " - " + msg);
  }

  @Override public void forecast(Forecast body) {
    City city = new City(body.getCity().getId(), body.getCity().getName(), Instant.now().toEpochMilli());
    cityController.save(city);
    for (Details d : body.getDetails()) {
      Weather weather = new Weather();
      weather.setDateTime(d.getDateTime());
      weather.setMax(d.getWeatherDetails().getTempMax());
      weather.setMin(d.getWeatherDetails().getTempMin());
      weather.setTitle(d.getWeatherDescription().getTitle());
      weather.setDescription(d.getWeatherDescription().getDescription());
      weather.setTemp(d.getWeatherDetails().getTemp());
      weather.setCityId(city.getId());
      save(weather);
    }
  }
}
