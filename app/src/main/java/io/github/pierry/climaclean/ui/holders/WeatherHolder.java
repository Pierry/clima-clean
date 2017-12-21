package io.github.pierry.climaclean.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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
  @BindView(R.id.description) TextView description;
  @BindView(R.id.bg) RelativeLayout bg;
  @BindView(R.id.icon) ImageView icon;

  private static final String CLOUD = "Cloud";
  private static final String RAIN = "Rain";
  private static final String CLEAR = "Clear";

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
    description.setText(city.getWeather().getWeatherDescription());
    if (city.getWeather().getWeatherDescription().equals(CLEAR)) {
      icon.setBackgroundResource(R.mipmap.sun);
    } else if (city.getWeather().getWeatherDescription().equals(CLOUD)) {
      icon.setBackgroundResource(R.mipmap.cloud);
    } else if (city.getWeather().getWeatherDescription().equals(RAIN)) {
      icon.setBackgroundResource(R.mipmap.umbrella);
    } else {
      icon.setBackgroundResource(R.mipmap.cloud);
    }
  }
}