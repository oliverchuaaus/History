package jdk5.generics.custom;

import java.util.List;

public final class CollectionHelper {

	private CollectionHelper() {

	}

	// ? is a wildcard type. List<?> is called List of unknowns.
	public static Object[] listToObjectArray(List<?> list) {
		Object[] array = new Object[list.size()];
		int i = 0;
		for (Object object : list) {
			array[i++] = object;
		}

		// Compiler doesn't allow adding for collection of unknowns.
		// Compile error: The method add(capture#3-of ?) in the type
		// List<capture#3-of ?> is not applicable for the arguments (Object)
		// list.add(new Object());

		// Only Null allowed
		list.add(null);

		return array;
	}

	// List<? extends Number> is a super type of List<Double> and List<Integer>
	// Called bounded wildcard
	// Number is a superclass of Integer and Double
	public static Number[] listToNumberArray(List<? extends Number> list) {
		Number[] array = new Number[list.size()];
		int i = 0;
		for (Number number : list) {
			array[i++] = number;
		}

		// Compiler doesn't allow adding for collection of unknowns.
		// Compiler error: The method add(capture#5-of ? extends Number) in the
		// type List<capture#5-of ? extends Number> is not applicable for the
		// arguments (Integer)
		// list.add(new Integer(1));

		return array;
	}

	// This is a generic method
	// E is called formal type parameter.
	// We cannot use List<?> because we cannot add unknowns to list
	// Without <E> before void, compile error: E cannot be resolved to a type
	public static <E> void addArrayToList(E[] array, List<E> list) {
		for (E obj : array) {
			list.add(obj);
		}
	}

}
