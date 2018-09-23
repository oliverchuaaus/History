package jdk8.optional;

import java.util.Optional;

public class Planet {
	private Optional<Continent> continent = Optional.empty();

	public Optional<Continent> getContinent() {
		return continent;
	}

	public void setContinent(Optional<Continent> continent) {
		this.continent = continent;
	}
}

class Continent {
	private Optional<Country> country;

	public Optional<Country> getCountry() {
		return country;
	}

	public void setCountry(Optional<Country> country) {
		this.country = country;
	}

}

class Country {
	private Optional<City> city;

	public Optional<City> getCity() {
		return city;
	}

	public void setCity(Optional<City> city) {
		this.city = city;
	}

}

class City {
	private String cityName;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}