package io.github.pierry.climaclean.common;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import io.github.pierry.climaclean.domain.City;
import io.github.pierry.climaclean.repository.ICityRepo;

@Database(entities = {
    City.class
}, version = 1) public abstract class AppDatabase extends RoomDatabase {

  public abstract ICityRepo cityRepository();
}