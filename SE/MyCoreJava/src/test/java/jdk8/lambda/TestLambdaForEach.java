package jdk8.lambda;

import java.util.List;

import org.junit.Test;

public class TestLambdaForEach {

	@Test
	public void testForEachWithoutLambda() {
		List<Developer> developerList = TestHelper.getDevelopers();
		for (Developer developer : developerList) {
			System.out.println(developer);
		}
		System.out.println("");
	}

	@Test
	public void testForEachWithLambda() {
		List<Developer> developerList = TestHelper.getDevelopers();
		developerList.forEach(d -> {
			System.out.println(d);
		});

		System.out.println("");

		// Same
		developerList.forEach(System.out::println);
		System.out.println("");
	}
}
