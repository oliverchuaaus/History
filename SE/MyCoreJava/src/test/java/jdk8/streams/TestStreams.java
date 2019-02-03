package jdk8.streams;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import jdk8.lambda.Developer;
import jdk8.lambda.TestHelper;

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
	public void testAllMatch() {
		List<Developer> developerList = TestHelper.getDevelopers();
		boolean allMatch = developerList.stream().allMatch(d -> d.getAge() > 9);
		assertTrue(allMatch);

		allMatch = developerList.stream().allMatch(d -> d.getAge() > 10);
		assertFalse(allMatch);

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
	}

	@Test
	public void testFlatMap() {
		List<Developer> developerList1 = TestHelper.getDevelopers();
		List<Developer> developerList2 = TestHelper.getDevelopers();
		List<List<Developer>> listOfLists = new ArrayList<List<Developer>>();
		listOfLists.add(developerList1);
		listOfLists.add(developerList2);
		List<String> mappedList = listOfLists.stream().flatMap(list -> list.stream()).map(d -> d.getName())
				.collect(Collectors.toList());
		TestHelper.printList(mappedList);
		assertEquals(8, mappedList.size());
	}

	// TODO: groupingBy
}
