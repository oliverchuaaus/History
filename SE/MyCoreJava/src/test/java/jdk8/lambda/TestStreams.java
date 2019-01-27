package jdk8.lambda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class TestStreams {

	@Test
	public void testFilter() {
		List<Developer> developerList = TestHelper.getDevelopers();
		TestHelper.printList(developerList);
		List<Developer> filteredList = developerList.stream().filter(d -> d.getAge() >= 18)
				.collect(Collectors.toList());
		TestHelper.printList(filteredList);
	}

	@Test
	public void testFindAny() {
		List<Developer> developerList = TestHelper.getDevelopers();
		TestHelper.printList(developerList);
		Developer developer = developerList.stream().filter(d -> d.getAge() < 18).findAny().orElse(null);
		System.out.println(developer);
		assertTrue(developer.getAge() < 18);

		developer = developerList.stream().filter(d -> d.getAge() > 55).findAny().orElse(null);
		System.out.println(developer);
		assertNull(developer);

		System.out.println("");
	}

	@Test
	public void testMap() {
		List<Developer> developerList = TestHelper.getDevelopers();
		TestHelper.printList(developerList);
		List<String> mappedList = developerList.stream().filter(d -> d.getAge() < 18).map(Developer::getName)
				.collect(Collectors.toList());
		TestHelper.printList(mappedList);
		assertEquals(1, mappedList.size());
		assertEquals("jason", mappedList.get(0));
	}

	//TODO: groupingBy, flatMap,
}
