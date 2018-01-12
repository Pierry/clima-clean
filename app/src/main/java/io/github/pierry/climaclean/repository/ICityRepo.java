package io.github.pierry.climaclean.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import io.github.pierry.climaclean.domain.City;
import io.github.pierry.climaclean.repository.viewmodel.CityWeatherViewModel;
import java.util.List;

@Dao public interface ICityRepo {

  @Query("SELECT * FROM City c LEFT JOIN Weather w ON c.id = w.cityId") LiveData<List<CityWeatherViewModel>> cities();

  @Query("SELECT * FROM City") List<City> all();

  @Query("SELECT * FROM City WHERE id = :id") City getById(long id);

  @Insert(onConflict = OnConflictStrategy.REPLACE) void save(City city);

  @Query("DELETE FROM City WHERE id = :id") void delete(long id);
}
