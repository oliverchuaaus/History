package jdk8.optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.Test;

public class TestOptional {

	@Test
	public void testCreateOptional() {
		// Get
		try {
			Optional.of(null);
			fail("should have failed");
		} catch (NullPointerException e) {
		} catch (Exception e) {
			fail("unexpected");
		}

		Optional<Integer> optionalEmpty = Optional.empty();
		assertFalse(optionalEmpty.isPresent());

		Integer integer = null;
		Optional<Integer> optionalEmpty2 = Optional.ofNullable(integer);
		assertFalse(optionalEmpty2.isPresent());

		// Get
		try {
			Optional.of(integer);
			fail("should have failed");
		} catch (NullPointerException e) {
		} catch (Exception e) {
			fail("unexpected");
		}

		integer = Integer.valueOf(1);
		Optional<Integer> optional3 = Optional.of(integer);
		assertTrue(optional3.isPresent());
	}

	@Test
	public void testReadOptional() {
		MutableInt mutable1 = new MutableInt(1);
		MutableInt mutable2 = new MutableInt(2);

		Optional<MutableInt> optionalEmpty = Optional.empty();
		Optional<MutableInt> optional2 = Optional.of(mutable1);

		// ToString
		assertEquals("Optional.empty", optionalEmpty.toString());
		assertEquals("Optional[1]", optional2.toString());

		// IsPresent
		assertFalse(optionalEmpty.isPresent());
		assertTrue(optional2.isPresent());

		// Get
		try {
			optionalEmpty.get();
			fail("should have failed");
		} catch (NoSuchElementException e) {
		} catch (Exception e) {
			fail("unexpected");
		}
		assertEquals(1, optional2.get().intValue());

		// IfPresent
		optionalEmpty.ifPresent(num -> fail("should have failed"));
		optional2.ifPresent(num -> num.increment());
		assertEquals(mutable2, optional2.get());

		MutableInt mutable100 = new MutableInt(100);
		Optional<MutableInt> optional100 = Optional.of(mutable100);

		// OrElse
		assertEquals(mutable100, optionalEmpty.orElse(mutable100));
		assertEquals(mutable1, optional2.orElse(mutable100));

		// OrElseGet
		assertEquals(mutable100, optionalEmpty.orElseGet(() -> mutable100));
		assertEquals(mutable1, optional2.orElseGet(() -> mutable100));

		// Filter
		assertFalse(optionalEmpty.filter(num -> num.intValue() == 1).isPresent());
		assertTrue(optional2.filter(num -> num.intValue() == 2).isPresent());
		// Map
		assertEquals(Optional.empty(), optionalEmpty.map(num -> num.incrementAndGet()));
		assertEquals(1, optional2.map(num -> num.decrementAndGet()).get().intValue());

		// FlatMap
		assertEquals(Optional.empty(), optionalEmpty.map(num -> num));
		assertEquals(Optional.of(Optional.of(mutable1)), optional2.map(num -> Optional.of(num)));

		assertEquals(Optional.empty(), optionalEmpty.flatMap(num -> optional100));
		assertEquals(Optional.of(mutable1), optional2.flatMap(num -> Optional.of(num)));
	}

	@Test
	public void testOptionalNullPointer() {
		Planet planet = new Planet();

		// all nested objects null
		String cityName = planet.getContinent().flatMap(continent -> continent.getCountry())
				.flatMap(country -> country.getCity()).map(city -> city.getCityName()).orElse("");
		assertEquals("", cityName);

		// nested objects not null
		planet.setContinent(Optional.ofNullable(new Continent()));
		planet.getContinent().get().setCountry(Optional.ofNullable(new Country()));
		planet.getContinent().get().getCountry().get().setCity((Optional.ofNullable(new City())));
		planet.getContinent().get().getCountry().get().getCity().get().setCityName("Manila");
		cityName = planet.getContinent().flatMap(continent -> continent.getCountry())
				.flatMap(country -> country.getCity()).map(city -> city.getCityName()).orElse("");
		assertEquals("Manila", cityName);
	}

}
