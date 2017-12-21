package io.github.pierry.climaclean.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
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
import io.github.pierry.climaclean.ui.adapters.WeatherAdapter;
import io.github.pierry.climaclean.ui.presenters.IMainPresenter;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements IMainPresenter {

  @BindView(R.id.rl) RelativeLayout rl;
  @BindView(R.id.recyclerView) RecyclerView recyclerView;
  @BindView(R.id.pesquisa) EditText pesquisa;

  private CompositeDisposable disposable;
  private RxBus rxBus;
  private Unbinder unbinder;
  private WeatherController controller;
  private CityController cityController;
  private WeatherAdapter adapter;
  private ProgressDialog progress;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    unbinder = ButterKnife.bind(this);
    KeyboardHelper.hide(rl);
    instance();
    configRecyclerView();
    pesquisa.setOnEditorActionListener((v, keyCode, keyEvent) -> {
      switch (keyCode) {
        case EditorInfo.IME_ACTION_NONE:
        case EditorInfo.IME_ACTION_DONE:
          String search = pesquisa.getText().toString();
          if (search.isEmpty()) {
            break;
          }
          progress = ProgressDialog.show(this, "", "Pesquisando...");
          progress.setIndeterminate(true);
          progress.setCancelable(true);
          controller.fetch(pesquisa.getText().toString());
          pesquisa.setText("");
          KeyboardHelper.hide(pesquisa);
          break;
      }
      return true;
    });
  }

  @Override public boolean dispatchTouchEvent(MotionEvent ev) {
    KeyboardHelper.hide(rl);
    return super.dispatchTouchEvent(ev);
  }

  @Override public void onStart() {
    super.onStart();
    disposable = new CompositeDisposable();
    disposable.add(getRxBus().asFlowable().subscribe(event -> {
      if (event instanceof List) {
        if (progress != null && progress.isShowing()) {
          progress.dismiss();
        }
        adapter(event);
      }
    }));
  }

  @Override protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }

  void instance() {
    cityController = new CityController(this);
    controller = new WeatherController(cityController);
    adapter = new WeatherAdapter();
    cityController.all(this);
  }

  void configRecyclerView() {
    LinearLayoutManager linearManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(linearManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    observeCities();
  }

  public void observeCities() {
    try {
      cityController.observe(this, getRxBus());
    } catch (Exception e) {

    }
  }

  @UiThread void adapter(Object object) {
    adapter.addItems((List<City>) object);
    recyclerView.setAdapter(adapter);
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

  @Override public void all(List<City> cityList) {
    for (City c : cityList) {
      controller.fetch(c.getName());
    }
  }
}
