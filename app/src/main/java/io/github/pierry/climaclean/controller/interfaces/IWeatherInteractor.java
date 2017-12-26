package io.github.pierry.climaclean.controller.interfaces;

import io.github.pierry.climaclean.api.viewmodels.forecast.Forecast;
import io.github.pierry.climaclean.api.viewmodels.now.Now;
import io.github.pierry.climaclean.domain.Weather;

public interface IWeatherInteractor {

  void found(Now now);

  void error(int code, String msg);

  void forecast(Forecast body);
}
