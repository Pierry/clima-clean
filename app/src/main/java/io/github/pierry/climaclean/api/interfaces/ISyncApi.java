package io.github.pierry.climaclean.api.interfaces;

import io.github.pierry.climaclean.controller.interfaces.IWeatherInteractor;

public interface ISyncApi {

  void get(IWeatherInteractor interactor, String city);
}
