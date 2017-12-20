package io.github.pierry.climaclean.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.github.pierry.climaclean.R;
import io.github.pierry.climaclean.domain.City;
import io.github.pierry.climaclean.domain.Weather;
import io.github.pierry.climaclean.ui.holders.WeatherHolder;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherHolder> {

  private List<City> items;

  public void addItems(List<City> cities) {
    this.items = cities;
  }

  @Override public WeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_adapter, parent, false);
    return new WeatherHolder(itemView);
  }

  @Override public void onBindViewHolder(WeatherHolder holder, int position) {
    City city = items.get(position);
    holder.bind(city, position);
  }

  @Override public int getItemCount() {
    return items != null ? items.size() : 0;
  }
}