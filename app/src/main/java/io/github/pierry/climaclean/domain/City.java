package io.github.pierry.climaclean.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity public class City {

  @NonNull @PrimaryKey private String name;
  private long updatedAt;

  public City() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(long updatedAt) {
    this.updatedAt = updatedAt;
  }
}
