package io.github.pierry.climaclean.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import io.github.pierry.climaclean.api.interfaces.IApi;
import io.github.pierry.climaclean.api.interfaces.ISyncApi;
import io.github.pierry.climaclean.api.viewmodels.forecast.Forecast;
import io.github.pierry.climaclean.api.viewmodels.now.Now;
import io.github.pierry.climaclean.common.Const;
import io.github.pierry.climaclean.controller.interfaces.IWeatherInteractor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SyncApi implements ISyncApi {

  private IApi api;
  private Gson gson;
  private JsonParser parser;

  public SyncApi() {
    instance();
    api = IApi.retrofit.create(IApi.class);
  }

  void instance() {
    gson = new GsonBuilder().create();
    parser = new JsonParser();
  }

  @Override public void cityNow(final IWeatherInteractor interactor, String city) {
    api.byCity(city, Const.KEY)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribeWith(new DisposableObserver<Response<Now>>() {
          @Override public void onNext(Response<Now> response) {
            switch (response.code()) {
              case 200:
                interactor.found(response.body());
                break;
            }
          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onComplete() {
          }
        });
  }

  @Override public void forecast(IWeatherInteractor interactor, String city) {
    api.forecast(city, Const.KEY)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribeWith(new DisposableObserver<Response<Forecast>>() {
          @Override public void onNext(Response<Forecast> response) {
            switch (response.code()) {
              case 200:
                interactor.forecast(response.body());
                break;
            }
          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onComplete() {
          }
        });
  }
}
