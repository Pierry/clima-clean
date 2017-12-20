package io.github.pierry.climaclean.controller;

import android.content.Context;
import io.github.pierry.climaclean.ClimaCleanApp;
import io.github.pierry.climaclean.common.AppDatabase;

public class BaseController {

  private Context context;

  public BaseController(Context context) {
    this.context = context;
  }

  public synchronized AppDatabase db() {
    ClimaCleanApp app = (ClimaCleanApp) context.getApplicationContext();
    return app.db();
  }
}
