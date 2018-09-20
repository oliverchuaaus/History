package jdk5.generics.custom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * http://thegreyblog.blogspot.com.au/2011/03/java-generics-tutorial-part-i-
 * basics.html#!/2011/03/java-generics-tutorial-part-i-basics.html
 */
public class TestGenericsCustom {
	@Test
	public void testWrongCastToSuper() {
		List<String> list1 = new ArrayList<String>();
		list1.add("String 1");

		// Compiler error: cannot convert from List<String> to List<Object>
		// List<Object> list2 = list1;
		// list2.add(new Object());
		// String str = list1.get(1);
	}

	@Test
	public void testCorrectCastToSuper() {
		List<String> list1 = new ArrayList<String>();
		list1.add("String 1");

		Object[] array = CollectionHelper.listToObjectArray(list1);
		assertEquals("String 1", (String) array[0]);
	}

	@Test
	public void testCorrectCastToSuper2() {
		List<Number> list1 = new ArrayList<Number>();
		list1.add(Integer.valueOf(1));
		list1.add(Double.valueOf(1.1));

		Number[] array = CollectionHelper.listToNumberArray(list1);
		assertEquals(Integer.valueOf(1), (Number) array[0]);
		assertEquals(Double.valueOf(1.1), (Number) array[1]);
	}

	@Test
	public void testGeneric() {
		List<Number> list1 = new ArrayList<Number>();
		Number[] array1 = new Number[] { Integer.valueOf(1), Double.valueOf(1.1) };
		CollectionHelper.addArrayToList(array1, list1);
		assertEquals(Integer.valueOf(1), list1.get(0));
		assertEquals(Double.valueOf(1.1), list1.get(1));

		List<String> list2 = new ArrayList<String>();
		String[] array = new String[] { "String1" };
		CollectionHelper.addArrayToList(array, list2);
		assertEquals("String1", list2.get(0));
	}

	@Test
	public void testGenericFactory() throws Exception {
		Integer integer = Integer.valueOf(100);
		Double double1 = Double.valueOf(150.55);

		Authoriser<Integer> authoriserI = Authoriser.getAuthoriser(integer);
		assertEquals("authorise: 100", authoriserI.authorise(integer));

		Authoriser<Double> authoriserD = Authoriser.getAuthoriser(double1);
		assertEquals("authorise double: 150.55", authoriserD.authorise(double1));
	}

	@Test
	public void testBadGenericFactory() throws InstantiationException,
			IllegalAccessException {
		Short short1 = Short.valueOf("150");

		// Compile error: Bound mismatch: The generic method getAuthoriser(E) of
		// type Authoriser<E> is not applicable for the arguments (String). The
		// inferred type String is not a valid substitute for the bounded
		// parameter <E extends Number>
		// Authoriser<Integer> authoriserI =
		// Authoriser.getAuthoriser("integer");

		try {
			Authoriser.getAuthoriser(short1);
			fail("expected exception not thrown");
		} catch (NullPointerException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("unexpected exception thrown");
		}
	}
}
