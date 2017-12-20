package io.github.pierry.climaclean.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.pierry.climaclean.R;
import io.github.pierry.climaclean.domain.City;

public class WeatherHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.city) TextView cityName;
  @BindView(R.id.temp) TextView temp;
  @BindView(R.id.max) TextView max;
  @BindView(R.id.min) TextView min;
  @BindView(R.id.bg) RelativeLayout bg;

  private City city;

  public WeatherHolder(View view) {
    super(view);
    ButterKnife.bind(this, view);
  }

  public void bind(final City city, final int position) {
    this.city = city;
    cityName.setText(city.getName());
    temp.setText(city.getWeather().getTempFormatted());
    max.setText(city.getWeather().getMaxFormatted());
    min.setText(city.getWeather().getMinFormatted());
  }
}