package jdk8.optional;

import java.util.Optional;

public class Country {
	private Optional<City> city;

	public Optional<City> getCity() {
		return city;
	}

	public void setCity(Optional<City> city) {
		this.city = city;
	}

}