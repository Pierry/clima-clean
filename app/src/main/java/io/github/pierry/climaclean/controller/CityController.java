package io.github.pierry.climaclean.controller;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import com.arasthel.asyncjob.AsyncJob;
import io.github.pierry.climaclean.common.RxBus;
import io.github.pierry.climaclean.controller.interfaces.IWeatherHolderInteractor;
import io.github.pierry.climaclean.domain.City;
import io.github.pierry.climaclean.ui.presenters.IMainPresenter;
import java.util.List;

public class CityController extends BaseController implements IWeatherHolderInteractor {

  public CityController(Context context) {
    super(context);
  }

  public void observe(LifecycleOwner owner, RxBus rxBus) {
    new AsyncJob.AsyncJobBuilder<Boolean>().doInBackground(() -> {
      db().cityRepository().cities().observe(owner, cities -> rxBus.send(cities));
      return true;
    }).create().start();
  }

  public void all(IMainPresenter presenter) {
    new AsyncJob.AsyncJobBuilder<List<City>>().doInBackground(() -> db().cityRepository().all()).doWhenFinished(list -> {
      presenter.all((List<City>) list);
    }).create().start();
  }

  public void add(City city) {
    new AsyncJob.AsyncJobBuilder<Boolean>().doInBackground(() -> {
      db().cityRepository().save(city);
      return true;
    }).create().start();
  }

  @Override public void delete(City city) {
    new AsyncJob.AsyncJobBuilder<Boolean>().doInBackground(() -> {
      db().cityRepository().delete(city);
      return true;
    }).create().start();
  }

  public void getByName(String cityName, LifecycleOwner owner, RxBus rxBus) {
    new AsyncJob.AsyncJobBuilder<City>().doInBackground(() -> db().cityRepository().getByName(cityName))
        .doWhenFinished(city -> rxBus.send(city))
        .create()
        .start();
  }
}
