package io.github.pierry.climaclean.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.github.pierry.climaclean.R;
import io.github.pierry.climaclean.controller.interfaces.IWeatherHolderInteractor;
import io.github.pierry.climaclean.repository.viewmodel.CityWeatherViewModel;
import io.github.pierry.climaclean.ui.holders.WeatherHolder;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherHolder> {

  private List<CityWeatherViewModel> items;
  private IWeatherHolderInteractor interactor;

  public WeatherAdapter(IWeatherHolderInteractor interactor) {
    this.interactor = interactor;
  }

  public void addItems(List<CityWeatherViewModel> viewModelList) {
    this.items = viewModelList;
  }

  @Override public WeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_adapter, parent, false);
    return new WeatherHolder(itemView, interactor);
  }

  @Override public void onBindViewHolder(WeatherHolder holder, int position) {
    CityWeatherViewModel viewModel = items.get(position);
    holder.bind(viewModel, position);
  }

  @Override public int getItemCount() {
    return items != null ? items.size() : 0;
  }
}