package jdk8.lambda;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
		Comparator<Developer> comparator = (o1, o2) -> {
			o1.getName();
			return o1.getName().compareTo(o2.getName());
		};

		TestHelper.printList(developerList);
		developerList.sort(comparator);
		TestHelper.printList(developerList);
	}

	@Test
	public void testLambdaMethodReference() {
		List<Developer> developerList = TestHelper.getDevelopers();

		// Static method
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
	public void testLambdaMethodReferenceInstance() {
		List<Developer> developerList = TestHelper.getDevelopers();

		// Instance method, no args
		developerList = developerList.stream().filter(d -> d.moreThan40YearsBeforeRetirement())
				.collect(Collectors.toList());
		TestHelper.printList(developerList);

		developerList = developerList.stream().filter(Developer::moreThan40YearsBeforeRetirement)
				.collect(Collectors.toList());
		TestHelper.printList(developerList);
		
		// Different sort of pattern
		TestHelper.printList(developerList);
		developerList.forEach(d -> System.out.println(d));
		
		TestHelper.printList(developerList);
		developerList.forEach(System.out::println);
		
		Consumer<Object> consumer = System.out::println;
		consumer.accept("Hello World");

		// Instance method, 1 arg
		BiFunction<Developer, Integer, Boolean> biFunction = (d, i) -> d.moreThan40YearsBeforeRetirement(i);
		developerList = developerList.stream().filter(d -> biFunction.apply(d, 74)).collect(Collectors.toList());
		TestHelper.printList(developerList);

		BiFunction<Developer, Integer, Boolean> biFunction2 = Developer::moreThan40YearsBeforeRetirement;
		developerList = developerList.stream().filter(d -> biFunction2.apply(d, 74)).collect(Collectors.toList());
		TestHelper.printList(developerList);
		
		// Instance method, 2 args
		TriFunction<Developer, Integer, Integer, Boolean> triFunction = (d, i, y) -> d.moreThanNYearsBeforeRetirement(i,
				y);
		developerList = developerList.stream().filter(d -> triFunction.apply(d, 74, 41)).collect(Collectors.toList());
		TestHelper.printList(developerList);

		TriFunction<Developer, Integer, Integer, Boolean> triFunction2 = Developer::moreThanNYearsBeforeRetirement;
		developerList = developerList.stream().filter(d -> triFunction2.apply(d, 74, 41)).collect(Collectors.toList());
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
