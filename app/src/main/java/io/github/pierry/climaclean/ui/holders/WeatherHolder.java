package io.github.pierry.climaclean.ui.holders;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.pierry.climaclean.R;
import io.github.pierry.climaclean.controller.interfaces.IWeatherHolderInteractor;
import io.github.pierry.climaclean.repository.viewmodel.CityWeatherViewModel;
import io.github.pierry.climaclean.ui.DetailsActivity;

public class WeatherHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

  @BindView(R.id.city) TextView cityName;
  @BindView(R.id.temp) TextView temp;
  @BindView(R.id.max) TextView max;
  @BindView(R.id.min) TextView min;
  @BindView(R.id.bg) RelativeLayout bg;

  private static final String CLOUD = "Clouds";
  private static final String RAIN = "Rain";
  private static final String CLEAR = "Clear";

  private CityWeatherViewModel viewModel;
  private IWeatherHolderInteractor interactor;
  private Context context;

  public WeatherHolder(View view, IWeatherHolderInteractor interactor) {
    super(view);
    context = view.getContext();
    ButterKnife.bind(this, view);
    this.interactor = interactor;
  }

  public void bind(final CityWeatherViewModel viewModel, final int position) {
    this.viewModel = viewModel;
    cityName.setText(viewModel.getName());
    temp.setText(viewModel.getTempFormatted());
    max.setText(viewModel.getMaxFormatted());
    min.setText(viewModel.getMinFormatted());
    if (CLEAR.equals(viewModel.getTitle())) {
      bg.setBackgroundResource(R.mipmap.cleanbg);
    } else if (CLOUD.equals(viewModel.getTitle())) {
      bg.setBackgroundResource(R.mipmap.cloudsbg);
    } else if (RAIN.equals(viewModel.getTitle())) {
      bg.setBackgroundResource(R.mipmap.rainbg);
    } else {
      bg.setBackgroundResource(R.mipmap.cleanbg);
    }
    bg.setOnLongClickListener(this);
    bg.setOnClickListener(this);
  }

  @Override public boolean onLongClick(View v) {
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
    alertDialog.setMessage("Excluir a cidade selecionada?");
    alertDialog.setPositiveButton("Excluir", (dialog, which) -> {
      interactor.delete(viewModel.getCityId());
    }).setNegativeButton("Cancelar", null);
    alertDialog.show();
    return true;
  }

  @Override public void onClick(View v) {
    Bundle bundle = new Bundle();
    bundle.putString("cityName", viewModel.getName());
    Intent details = new Intent(context, DetailsActivity.class);
    details.putExtras(bundle);
    context.startActivity(details);
  }
}