package jdk9.optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.Test;

public class TestOptionalJdk9 {

	@Test
	public void testReadOptional9() {
		MutableInt mutable1 = new MutableInt(1);

		Optional<MutableInt> optionalEmpty = Optional.empty();
		Optional<MutableInt> optional2 = Optional.of(mutable1);

		// IfPresentOrElse
		optionalEmpty.ifPresentOrElse(num -> fail("should have failed"), () -> System.out.println("none"));
		optional2.ifPresentOrElse(num -> num.decrement(), () -> fail("should have failed"));
		assertEquals(0, optional2.get().intValue());

		// Or
		MutableInt mutable100 = new MutableInt(100);
		Optional<MutableInt> optional100 = Optional.of(mutable100);
		assertEquals(optional100, optionalEmpty.or(() -> optional100));
		assertEquals(optional2, optional2.or(() -> optional100));

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

		// Stream
		assertEquals(0, optionalEmpty.stream().count());
		assertEquals(1, optional2.stream().count());
	}

}
