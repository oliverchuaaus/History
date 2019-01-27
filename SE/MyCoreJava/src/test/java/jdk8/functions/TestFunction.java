package jdk8.functions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

import jdk8.lambda.Developer;

public class TestFunction {

	@Test
	public void testPredicate() {
		Predicate<Developer> predicate = (d -> d.getAge() >= 18 && d.getAge() <= 25);
		Developer developer = new Developer("Toffer", null, 18);
		if (!predicate.test(developer)) {
			fail("predicate should have return true");
		}
		developer = new Developer("Toffer", null, 26);
		if (predicate.test(developer)) {
			fail("predicate should have return false");
		}
	}

	@Test
	public void testFunction() {
		Function<Developer, String> function = d -> d.getName();
		Developer developer = new Developer("Toffer", null, 18);
		assertEquals("Toffer", function.apply(developer));
	}

	@Test
	public void testConsumer() {
		Consumer<Developer> consumer = d -> d.setName("Toffer1");
		Developer developer = new Developer("Toffer", null, 18);
		consumer.accept(developer);
		assertEquals("Toffer1", developer.getName());

	}

	@Test
	public void testSupplier() {
		Supplier<Developer> supplier = () -> new Developer("Toffer", null, 18);
		Developer developer = supplier.get();
		assertEquals("Toffer", developer.getName());
	}

	@Test
	public void testBiFunction() {
		BiFunction<Integer, Double, Double> function = (a, b) -> a + b;
		assertEquals(Double.valueOf(3), function.apply(1, 2d));
	}

	@Test
	public void testBinaryOperator() {
		BinaryOperator<Integer> function = (a, b) -> a + b;
		assertEquals(Integer.valueOf(3), function.apply(1, 2));
	}
}
