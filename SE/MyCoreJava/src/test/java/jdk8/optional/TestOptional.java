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
		Optional<Integer> optionalEmpty = Optional.empty();

		// Get
		try {
			Optional.of(null);
			fail("should have failed");
		} catch (NullPointerException e) {
		} catch (Exception e) {
			fail("unexpected");
		}

		Integer integer = null;
		Optional<Integer> optionalEmpty2 = Optional.ofNullable(integer);

		integer = Integer.valueOf(1);
		Optional<Integer> optional3 = Optional.of(integer);

		assertFalse(optionalEmpty.isPresent());
		assertFalse(optionalEmpty2.isPresent());
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

		// IfPresentOrElse
		optionalEmpty.ifPresentOrElse(num -> fail("should have failed"), () -> System.out.println("none"));
		optional2.ifPresentOrElse(num -> num.decrement(), () -> fail("should have failed"));
		assertEquals(1, optional2.get().intValue());

		// Or
		MutableInt mutable100 = new MutableInt(100);
		Optional<MutableInt> optional100 = Optional.of(mutable100);
		assertEquals(optional100, optionalEmpty.or(() -> optional100));
		assertEquals(optional2, optional2.or(() -> optional100));

		// OrElse
		assertEquals(mutable100, optionalEmpty.orElse(mutable100));
		assertEquals(mutable1, optional2.orElse(mutable100));

		// OrElseGet
		assertEquals(mutable100, optionalEmpty.orElseGet(() -> mutable100));
		assertEquals(mutable1, optional2.orElseGet(() -> mutable100));

		// OrElseThrow
		try {
			optionalEmpty.orElseThrow();
			fail("should have failed");
		} catch (NoSuchElementException e) {
		} catch (Exception e) {
			fail("unexpected");
		}
		assertEquals(mutable1, optional2.orElseThrow());

		// OrElseThrowException
		try {
			optionalEmpty.orElseThrow(() -> new IllegalArgumentException());
			fail("should have failed");
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			fail("unexpected");
		}
		assertEquals(mutable1, optional2.orElseThrow());

		// Filter
		assertFalse(optionalEmpty.filter(num -> num.intValue() == 1).isPresent());
		assertTrue(optional2.filter(num -> num.intValue() == 1).isPresent());
		// Map
		assertEquals(Optional.empty(), optionalEmpty.map(num -> num.incrementAndGet()));
		assertEquals(2, optional2.map(num -> num.incrementAndGet()).get().intValue());

		// FlatMap
		assertEquals(Optional.empty(), optionalEmpty.map(num -> num));
		assertEquals(Optional.of(Optional.of(mutable2)), optional2.map(num -> Optional.of(num)));

		assertEquals(Optional.empty(), optionalEmpty.flatMap(num -> optional100));
		assertEquals(Optional.of(mutable2), optional2.flatMap(num -> Optional.of(num)));

		// Stream
		assertEquals(0, optionalEmpty.stream().count());
		assertEquals(1, optional2.stream().count());
	}

	@Test
	public void testOptionalNullPointer() {
		Planet planet = new Planet();

		String cityName = planet.getContinent().flatMap(continent -> continent.getCountry())
				.flatMap(country -> country.getCity()).map(city -> city.getCityName()).orElse("");
		assertEquals("", cityName);

		planet.setContinent(Optional.ofNullable(new Continent()));
		planet.getContinent().get().setCountry(Optional.ofNullable(new Country()));
		planet.getContinent().get().getCountry().get().setCity((Optional.ofNullable(new City())));
		planet.getContinent().get().getCountry().get().getCity().get().setCityName("Manila");
		cityName = planet.getContinent().flatMap(continent -> continent.getCountry())
				.flatMap(country -> country.getCity()).map(city -> city.getCityName()).orElse("");
		assertEquals("Manila", cityName);
	}

}
