package jdk8.lambda;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import jdk8.lambda.Calculator.IntegerMath;

public class TestLambda {

	@Test
	public void testComparatorWithoutLambda() {
		List<Developer> developerList = TestHelper.getDevelopers();
		Comparator<Developer> comparator = new Comparator<Developer>() {
			@Override
			public int compare(Developer o1, Developer o2) {
				return o1.getAge() - o2.getAge();
			}
		};

		TestHelper.printList(developerList);
		Collections.sort(developerList, comparator);
		TestHelper.printList(developerList);
	}

	@Test
	public void testComparatorWithLambda() {
		List<Developer> developerList = TestHelper.getDevelopers();
		Comparator<Developer> comparator = (Developer o1, Developer o2) -> o1.getAge() - o2.getAge();

		TestHelper.printList(developerList);
		developerList.sort(comparator);
		TestHelper.printList(developerList);
	}

	@Test
	public void testComparatorWithLambdaShorthand() {
		List<Developer> developerList = TestHelper.getDevelopers();
		Comparator<Developer> comparator = (o1, o2) -> o1.getAge() - o2.getAge();

		TestHelper.printList(developerList);
		developerList.sort(comparator);
		TestHelper.printList(developerList);
	}

	@Test
	public void testComparatorWithLambdaObject() {
		List<Developer> developerList = TestHelper.getDevelopers();
		Comparator<Developer> comparator = (o1, o2) -> o1.getName().compareTo(o2.getName());

		TestHelper.printList(developerList);
		developerList.sort(comparator);
		TestHelper.printList(developerList);
	}

	@Test
	public void testComparatorWithLambdaObjectMultilineFunction() {
		List<Developer> developerList = TestHelper.getDevelopers();
		Comparator<Developer> comparator = (o1, o2) -> {o1.getName(); return o1.getName().compareTo(o2.getName()); } ;

		TestHelper.printList(developerList);
		developerList.sort(comparator);
		TestHelper.printList(developerList);
	}

	@Test
	public void testLambdaMethodReference() {
		List<Developer> developerList = TestHelper.getDevelopers();

		TestHelper.printList(developerList);
		developerList.sort(Developer::compareByAge);
		TestHelper.printList(developerList);

		TestHelper.printList(developerList);
		developerList.sort(Developer::compareByName);
		TestHelper.printList(developerList);

		TestHelper.printList(developerList);
		developerList.sort(Developer::compareBySalary);
		TestHelper.printList(developerList);
	}

	@Test
	public void testLambdaNonComparator() {
		Calculator myApp = new Calculator();
		IntegerMath addition = (a, b, c) -> a + b + c;
		IntegerMath subtraction = (a, b, c) -> a - b - c;
		assertEquals(52, myApp.operateTernary(40, 2, 10, addition));
		assertEquals(5, myApp.operateTernary(20, 10, 5, subtraction));
	}

}
