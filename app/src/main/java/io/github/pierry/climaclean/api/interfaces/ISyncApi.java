package io.github.pierry.climaclean.api.interfaces;

import io.github.pierry.climaclean.controller.interfaces.IWeatherInteractor;

public interface ISyncApi {

  void cityNow(IWeatherInteractor interactor, String city);

  void forecast(IWeatherInteractor interactor, String city);
}
