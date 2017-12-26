package io.github.pierry.climaclean.api.viewmodels.forecast;

public class City {

  private long id;
  private String name;
  private Coord coord;
  private String country;
  private int population;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Coord getCoord() {
    return coord;
  }

  public void setCoord(Coord coord) {
    this.coord = coord;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getPopulation() {
    return population;
  }

  public void setPopulation(int population) {
    this.population = population;
  }
}
