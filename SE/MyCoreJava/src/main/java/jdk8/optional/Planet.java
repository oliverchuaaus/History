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
