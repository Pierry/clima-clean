package io.github.pierry.climaclean.controller.interfaces;

import io.github.pierry.climaclean.domain.Weather;

public interface IWeatherInteractor {

  void found(Weather weather, String cityName);

  void error(int code, String msg);
}
