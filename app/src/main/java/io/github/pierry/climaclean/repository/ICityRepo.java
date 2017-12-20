package io.github.pierry.climaclean.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import io.github.pierry.climaclean.domain.City;
import java.util.List;

@Dao public interface ICityRepo {

  @Query("SELECT * FROM City") LiveData<List<City>> cities();

  @Query("SELECT * FROM City") List<City> all();

  @Insert(onConflict = OnConflictStrategy.REPLACE) void save(City city);

  @Delete void delete(City city);
}
