package io.github.pierry.climaclean.api.viewmodels.forecast;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Forecast {

  private int cnt;
  @SerializedName("list") private List<Details> details;
  private City city;

  public int getCnt() {
    return cnt;
  }

  public void setCnt(int cnt) {
    this.cnt = cnt;
  }

  public List<Details> getDetails() {
    return details;
  }

  public void setDetails(List<Details> details) {
    this.details = details;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }
}
