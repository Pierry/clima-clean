package io.github.pierry.climaclean.common;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import io.github.pierry.climaclean.domain.City;
import io.github.pierry.climaclean.domain.Weather;
import io.github.pierry.climaclean.repository.ICityRepo;
import io.github.pierry.climaclean.repository.IWeatherRepo;

@Database(entities = {
    City.class, Weather.class
}, version = 1) public abstract class AppDatabase extends RoomDatabase {

  public abstract ICityRepo cityRepository();

  public abstract IWeatherRepo weatherRepository();
}