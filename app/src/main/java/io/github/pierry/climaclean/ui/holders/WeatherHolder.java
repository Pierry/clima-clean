package io.github.pierry.climaclean.ui.holders;

import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.pierry.climaclean.R;
import io.github.pierry.climaclean.controller.interfaces.IWeatherHolderInteractor;
import io.github.pierry.climaclean.domain.City;

public class WeatherHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

  @BindView(R.id.city) TextView cityName;
  @BindView(R.id.temp) TextView temp;
  @BindView(R.id.max) TextView max;
  @BindView(R.id.min) TextView min;
  @BindView(R.id.bg) RelativeLayout bg;

  private static final String CLOUD = "Clouds";
  private static final String RAIN = "Rain";
  private static final String CLEAR = "Clear";

  private City city;
  private IWeatherHolderInteractor interactor;

  public WeatherHolder(View view, IWeatherHolderInteractor interactor) {
    super(view);
    ButterKnife.bind(this, view);
    this.interactor = interactor;
  }

  public void bind(final City city, final int position) {
    this.city = city;
    cityName.setText(city.getName());
    temp.setText(city.getWeather().getTempFormatted());
    max.setText(city.getWeather().getMaxFormatted());
    min.setText(city.getWeather().getMinFormatted());
    Log.i("Weather", city.getName() + " - " + city.getWeather().getWeatherDescription());
    if (city.getWeather().getWeatherDescription().equals(CLEAR)) {
      bg.setBackgroundResource(R.mipmap.cleanbg);
    } else if (city.getWeather().getWeatherDescription().equals(CLOUD)) {
      bg.setBackgroundResource(R.mipmap.cloudsbg);
    } else if (city.getWeather().getWeatherDescription().equals(RAIN)) {
      bg.setBackgroundResource(R.mipmap.rainbg);
    } else {
      bg.setBackgroundResource(R.mipmap.cleanbg);
    }
    bg.setOnLongClickListener(this);
  }

  @Override public boolean onLongClick(View v) {
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
    alertDialog.setMessage("Excluir a cidade selecionada?");
    alertDialog.setPositiveButton("Excluir", (dialog, which) -> {
      interactor.delete(city);
    }).setNegativeButton("Cancelar", null);
    alertDialog.show();
    return true;
  }
}