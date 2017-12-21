package io.github.pierry.climaclean;

import android.app.Application;
import android.arch.persistence.room.Room;
import io.github.pierry.climaclean.common.AppDatabase;
import io.github.pierry.climaclean.common.Const;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class ClimaCleanApp extends Application {

  private AppDatabase db;

  @Override public void onCreate() {
    super.onCreate();
    CalligraphyConfig.initDefault(
        new CalligraphyConfig.Builder().setDefaultFontPath("lato.otf").setFontAttrId(R.attr.fontPath).build());
  }

  public AppDatabase db() {
    if (db == null) {
      db = Room.databaseBuilder(this, AppDatabase.class, Const.DATABASE).build();
    }
    return db;
  }
}
