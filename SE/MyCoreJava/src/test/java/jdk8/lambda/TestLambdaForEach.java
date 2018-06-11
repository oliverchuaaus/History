package jdk8.lambda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestLambdaForEach {
	private List<Developer> getDevelopers() {
		List<Developer> result = new ArrayList<Developer>();
		result.add(new Developer("mkyong", new BigDecimal("70000"), 33));
		result.add(new Developer("alvin", new BigDecimal("80000"), 20));
		result.add(new Developer("jason", new BigDecimal("100000"), 10));
		result.add(new Developer("iris", new BigDecimal("170000"), 55));
		return result;
	}

	@Test
	public void testForEachWithoutLambda() {
		List<Developer> developerList = getDevelopers();
		for (Developer developer : developerList) {
			System.out.println(developer);
		}
		System.out.println("");
	}

	@Test
	public void testForEachWithLambda() {
		List<Developer> developerList = getDevelopers();
		developerList.forEach(d -> {
			System.out.println(d);
		});
		System.out.println("");

		//Same
		developerList.forEach(System.out::println);
		System.out.println("");

	}
}
