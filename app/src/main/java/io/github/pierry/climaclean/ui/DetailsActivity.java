package io.github.pierry.climaclean.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.github.pierry.climaclean.R;
import io.github.pierry.climaclean.common.KeyboardHelper;
import io.github.pierry.climaclean.common.RxBus;
import io.github.pierry.climaclean.controller.CityController;
import io.github.pierry.climaclean.controller.WeatherController;
import io.github.pierry.climaclean.domain.City;
import io.reactivex.disposables.CompositeDisposable;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailsActivity extends AppCompatActivity {

  @BindView(R.id.rl) RelativeLayout rl;
  @BindView(R.id.toolbar) Toolbar toolbar;

  private Unbinder unbinder;
  private WeatherController weatherController;
  private CityController cityController;
  private String cityName;
  private RxBus rxBus;
  private CompositeDisposable disposable;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.details_activity);
    unbinder = ButterKnife.bind(this);
    instance();
    cityName = getIntent().getExtras().getString("cityName");
    actionBar(cityName);
    loadCity(cityName);
  }

  void instance() {
    cityController = new CityController(this);
    weatherController = new WeatherController(this, cityController);
  }

  void actionBar(String city) {
    toolbar.setTitle(city);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
  }

  void loadCity(String city) {
    weatherController.fetchForecast(city);
  }

  @Override public void onStart() {
    super.onStart();
    disposable = new CompositeDisposable();
    disposable.add(getRxBus().asFlowable().subscribe(event -> {
      if (event instanceof City) {
        loaded((City) event);
      }
    }));
  }

  @UiThread void loaded(City city) {
    actionBar(city.getName());
  }

  @Override protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }

  @Override public boolean dispatchTouchEvent(MotionEvent ev) {
    KeyboardHelper.hide(rl);
    return super.dispatchTouchEvent(ev);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
  }

  public RxBus getRxBus() {
    if (rxBus == null) {
      rxBus = new RxBus();
    }
    return rxBus;
  }
}
