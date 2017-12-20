package io.github.pierry.climaclean.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.github.pierry.climaclean.api.interfaces.IApi;
import io.github.pierry.climaclean.api.interfaces.ISyncApi;
import io.github.pierry.climaclean.common.Const;
import io.github.pierry.climaclean.controller.interfaces.IWeatherInteractor;
import io.github.pierry.climaclean.domain.Weather;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SyncApi implements ISyncApi {

  private IApi api;

  public SyncApi() {
    api = IApi.retrofit.create(IApi.class);
  }

  @Override public void get(final IWeatherInteractor interactor, String city) {
    api.byCity(city, Const.KEY)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribeWith(new DisposableObserver<Response<Object>>() {
          @Override public void onNext(Response<Object> response) {
            switch (response.code()) {
              case 200:
                Gson gson = new GsonBuilder().create();
                JsonParser parser = new JsonParser();
                String render = gson.toJson(response.body());
                JsonObject parsed = parser.parse(render).getAsJsonObject();
                JsonObject converted = parsed.get("main").getAsJsonObject();
                String cityName = parsed.get("name").getAsString();
                TypeToken<Weather> token = new TypeToken<Weather>() {
                };
                Weather weather = gson.fromJson(converted, token.getType());
                interactor.found(weather, cityName);
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
