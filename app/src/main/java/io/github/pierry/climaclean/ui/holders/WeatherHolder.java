package io.github.pierry.climaclean.ui.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
  @BindView(R.id.bg) RelativeLayout bg;

  private static final String CLOUD = "Clouds";
  private static final String RAIN = "Rain";
  private static final String CLEAR = "Clear";

  private City city;
  private Context context;

  public WeatherHolder(View view) {
    super(view);
    this.context = view.getContext();
    ButterKnife.bind(this, view);
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
  }
}