package io.github.pierry.climaclean.repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import io.github.pierry.climaclean.domain.Weather;
import java.util.List;

@Dao public interface IWeatherRepo {

  @Query("SELECT * FROM Weather") List<Weather> all();

  @Insert(onConflict = OnConflictStrategy.REPLACE) void save(Weather Weather);

  @Delete void delete(Weather weather);
}
