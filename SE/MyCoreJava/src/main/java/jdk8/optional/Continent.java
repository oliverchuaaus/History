package jdk8.optional;

import java.util.Optional;

public class Continent {
	private Optional<Country> country;

	public Optional<Country> getCountry() {
		return country;
	}

	public void setCountry(Optional<Country> country) {
		this.country = country;
	}

}
