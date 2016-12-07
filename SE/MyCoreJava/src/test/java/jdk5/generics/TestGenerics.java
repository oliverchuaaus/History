package jdk5.generics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import jdk5.generics.custom.CollectionHelper;

import org.junit.Test;

/**
 * http://java.sun.com/j2se/1.5/pdf/generics-tutorial.pdf
 */
public class TestGenerics {
	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testOldCollections() {
		List list = new ArrayList();
		list.add("String 1");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			assertEquals("String 1", string);
		}
	}

	@Test
	public void testNewCollections() {
		// Read as List of String, where string is a parameterized type or type
		// parameter
		List<String> list = new ArrayList<String>();
		list.add("String 1");
		for (String string : list) {
			assertEquals("String 1", string);
		}
	}

	@Test
	public void testArraySubtyping() {
		String[] stringArray = new String[] { "Hello", "World" };
		Object[] objectArray = stringArray;
		try {
			// Array has no compile time checking, while generics has.
			objectArray[0] = 1;
			fail("expected exception not thrown");
		} catch (ArrayStoreException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("unexpected exception thrown");
		}
	}

	@Test
	public void testSubtyping() {
		List<String> list = new ArrayList<String>();
		list.add("Hello World");

		// Legal
		Collection<String> col = list;
		for (Object object : col) {
			assertEquals("Hello World", object);
		}

		// Compile error: Type mismatch: cannot convert from List<String> to
		// List<Object>
		// List<Object> objectList = list;

		// List<?> is the supertype of List<String>
		List<?> objectList = list;
		for (Object object : objectList) {
			assertEquals("Hello World", object);
		}
		// Adding objects not safe, cause it might conflict with existing
		// objects.
		// Compile error: The method add(capture#2-of ?) in the type
		// List<capture#2-of ?> is not applicable for the arguments (Object)
		// objectList.add(new Object());

		// null can be safely added
		objectList.add(null);
	}

	@Test
	public void testInstanceOf() {
		List<String> list = new ArrayList<String>();

		// Compile error: Cannot perform instanceof check against parameterized
		// type List<String>.
		// Use the form List<?> instead since further generic type information
		// will be erased at runtime
		// boolean b = list instanceof List<String>;
		assertTrue(list instanceof List<?>);
		assertTrue(list instanceof ArrayList<?>);
		assertTrue(list instanceof List);
	}

	@Test
	public void testErasure() {
		@SuppressWarnings("rawtypes")
		List genericList = new ArrayList();
		List<String> stringList = new ArrayList<String>();
		List<Integer> integerList = new ArrayList<Integer>();

		// A Generic class is shared by all its invocations
		assertTrue(stringList.getClass() == genericList.getClass());
		assertTrue(stringList.getClass() == integerList.getClass());

		assertTrue(integerList instanceof ArrayList);
		assertTrue(integerList instanceof ArrayList<?>);

		// Compile error: Cannot perform instanceof check against parameterized
		// type ArrayList<String>. Use the form ArrayList<?> instead since
		// further generic type information will be erased at runtime
		// assertTrue(integerList instanceof ArrayList<Integer>);
	}

	@Test
	public void testArrayToList() {
		Integer[] intArray = new Integer[] { 1, 2 };
		List<Integer> intList = new ArrayList<Integer>();
		CollectionHelper.arrayToList(intArray, intList);
		assertEquals(2, intList.size());
		assertEquals(1, intList.get(0).intValue());
		assertEquals(2, intList.get(1).intValue());

		Double[] doubleArray = new Double[] { 1.0, 2.0 };
		List<Double> doubleList = new ArrayList<Double>();
		CollectionHelper.arrayToList(doubleArray, doubleList);
		assertEquals(2, doubleList.size());
		assertEquals(1.0, intList.get(0).doubleValue(), 0.0);
		assertEquals(2.0, intList.get(1).doubleValue(), 0.0);
	}

	@Test
	public void testAlternateArrayToList() {
		Integer[] intArray = new Integer[] { 1, 2 };

		List<Number> numList = new ArrayList<Number>();
		numList.add(Double.valueOf(1.0));

		// Use this to restrict numList to a List of Integer.
		// Not much use, cause intList is inherently still a list of Numbers.
		@SuppressWarnings("unused")
		List<? super Integer> intList = numList;
		// Compile error: The method add(capture#7-of ? super Integer) in the
		// type List<capture#7-of ? super Integer> is not applicable for the
		// arguments (Double)
		// intList.add(Double.valueOf(1.0));

		CollectionHelper.arrayToList(intArray, numList);
		assertEquals(3, numList.size());

		assertEquals(1.0, numList.get(0));
		assertEquals(1, numList.get(1));
		assertEquals(2, numList.get(2));

		assertEquals(Double.class, numList.get(0).getClass());
		assertEquals(Integer.class, numList.get(1).getClass());
		assertEquals(Integer.class, numList.get(2).getClass());

	}
}
