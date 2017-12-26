package io.github.pierry.climaclean.api.interfaces;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.pierry.climaclean.api.viewmodels.forecast.Forecast;
import io.github.pierry.climaclean.api.viewmodels.now.Now;
import io.github.pierry.climaclean.common.Const;
import io.reactivex.Observable;
import java.lang.reflect.Modifier;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IApi {

  GsonBuilder builder = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);
  Gson gson = builder.create();

  Retrofit retrofit = new Retrofit.Builder().baseUrl(Const.URL)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build();

  @POST(Const.BY_CITY) Observable<Response<Now>> byCity(@Query("q") String city, @Query("APPID") String appId);

  @POST(Const.BY_CITY_FORECAST) Observable<Response<Forecast>> forecast(@Query("q") String city, @Query("APPID") String appid);
}
