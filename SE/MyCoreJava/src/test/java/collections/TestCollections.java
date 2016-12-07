package collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class TestCollections {
	@Test
	public void testToList() throws Exception {
		List<String> states = Arrays.asList("NSW", "QLD", "TAS", "VIC", "WA",
				"NT", "ACT");

		int i = 0;
		for (String state : states) {
			switch (i) {
			case 0:
				assertEquals("NSW", state);
				break;
			case 1:
				assertEquals("QLD", state);
				break;
			case 2:
				assertEquals("TAS", state);
				break;
			case 3:
				assertEquals("VIC", state);
				break;
			case 4:
				assertEquals("WA", state);
				break;
			case 5:
				assertEquals("NT", state);
				break;
			case 6:
				assertEquals("ACT", state);
				break;
			default:
				fail("unexpected element in list");
			}
			i++;
		}
	}

	@Test
	public void testToArray1() throws Exception {
		List<String> states = new ArrayList<String>();
		states.add("Michigan");
		states.add("Massachusetts");

		String[] array = new String[1];
		array = states.toArray(array);
		for (int i = 0; i < array.length; i++) {
			String state = (String) array[i];
			switch (i) {
			case 0:
				assertEquals("Michigan", state);
				break;
			case 1:
				assertEquals("Massachusetts", state);
				break;
			default:
				fail("unexpected element in array");
				break;
			}
		}
	}

	@Test
	public void testToArray2() throws Exception {
		List<String> states = new ArrayList<String>();
		states.add("Michigan");
		states.add("Massachusetts");

		Object[] array = states.toArray();
		for (int i = 0; i < array.length; i++) {
			String state = (String) array[i];
			switch (i) {
			case 0:
				assertEquals("Michigan", state);
				break;
			case 1:
				assertEquals("Massachusetts", state);
				break;
			default:
				fail("unexpected element in array");
				break;
			}
		}
	}

	@Test
	public void testBadToArray() throws Exception {
		List<String> states = new ArrayList<String>();
		states.add("Michigan");
		states.add("Massachusetts");

		// Runtime Error:java.lang.ClassCastException: [Ljava.lang.Object;
		// cannot be cast to [Ljava.lang.String;
		try {
			@SuppressWarnings("unused")
			String[] array = (String[]) states.toArray();
			fail("expected exception not thrown");
		} catch (ClassCastException e) {
			assertTrue(true);
		}

	}

	@Test
	public void testArrayToString() throws Exception {
		String[] states = new String[] { "New South Wales", "Queensland" };
		String[][] capitals = new String[][] { { "Sydney", "Brisbane" },
				{ "New South Wales", "Queensland" } };

		// System.out.println(states);
		// will print [Ljava.lang.String;@54172f
		assertEquals("[New South Wales, Queensland]", Arrays.asList(states)
				.toString());
		assertEquals("[New South Wales, Queensland]", Arrays.toString(states));

		// System.out.println(Arrays.toString(capitals));
		// 2d array will print [[Ljava.lang.String;@18a47e0,
		// [Ljava.lang.String;@174cc1f]
		assertEquals("[[Sydney, Brisbane], [New South Wales, Queensland]]",
				Arrays.deepToString(capitals));
	}

	@Test
	public void testList() {
		List<String> list = new ArrayList<String>();
		list.add("January");
		list.add("January");
		int i = 0;
		for (String month : list) {
			switch (i) {
			case 0:
				assertEquals("January", month);
				break;
			case 1:
				assertEquals("January", month);
				break;
			default:
				fail("unexpected element in list");
				break;
			}
		}
	}

	@Test
	public void testSet() {
		Set<String> set = new LinkedHashSet<String>();
		set.add("January");
		set.add("February");
		set.add("March");
		set.add("January");

		int i = 0;
		for (String month : set) {
			switch (i) {
			case 0:
				assertEquals("January", month);
				break;
			case 1:
				assertEquals("February", month);
				break;
			case 2:
				assertEquals("March", month);
				break;
			default:
				fail("unexpected element in set");
			}
			i++;
		}
	}

	@Test
	public void testMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("month1", "January");
		map.put("month2", "February");
		map.put("month3", "March");
		String month = map.get("month1");
		assertEquals("January", month);
		Set<String> keys = map.keySet();
		int i = 0;
		for (String key : keys) {
			switch (i) {
			case 0:
				assertEquals("month1", key);
				break;
			case 1:
				assertEquals("month2", key);
				break;
			case 2:
				assertEquals("month3", key);
				break;
			default:
				fail("unexpected element in map");
			}
			i++;
		}
		Set<String> values = new LinkedHashSet<String>(map.values());
		i = 0;
		for (String value : values) {
			switch (i) {
			case 0:
				assertEquals("January", value);
				break;
			case 1:
				assertEquals("February", value);
				break;
			case 2:
				assertEquals("March", value);
				break;
			default:
				fail("unexpected element in map");
			}
			i++;
		}
	}

	@Test
	public void testDeque() {
		// Deque is a double ended queue, can add or delete on either end.
		Deque<String> deque = new ArrayDeque<String>();
		deque.add("February");
		deque.addFirst("January");
		deque.addLast("March");
		deque.removeFirst();
		deque.removeLast();
		assertEquals(1, deque.size());
		assertEquals("February", deque.getFirst());
	}

	@Test
	public void testEmptyList() {
		@SuppressWarnings("unchecked")
		List<String> list = Collections.EMPTY_LIST;
		assertEquals(0, list.size());

		list = Collections.<String> emptyList();
		assertEquals(0, list.size());
	}

	@Test
	public void testBadEmptyList() {
		@SuppressWarnings("unchecked")
		List<String> list = Collections.EMPTY_LIST;

		try {
			// Runtime exception:java.lang.UnsupportedOperationException
			list.add("test");
			fail("expected exception not thrown");
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
		}

		list = Collections.<String> emptyList();
		assertEquals(0, list.size());

		try {
			// Runtime exception:java.lang.UnsupportedOperationException
			list.add("test");
			fail("expected exception not thrown");
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testBadSetEqualsNotOverridden() {
		Set<Name> set = new HashSet<Name>();
		Name name1 = new Name("Kristoffer", "Chua");
		Name name2 = new Name("Kristoffer", "Chua");
		set.add(name1);
		set.add(name2);

		// Set inserted duplicates
		assertEquals(2, set.size());
	}

	@Test
	public void testBadMapEqualsNotOverridden() {
		Map<Name, String> map = new HashMap<Name, String>();
		Name name1 = new Name("Kristoffer", "Chua");
		Name name2 = new Name("Kristoffer", "Chua");
		map.put(name1, "June");
		map.put(name2, "April");

		// Set inserted duplicates
		assertEquals(2, map.size());
	}

	@Test
	public void testBadMapMutableKey() {
		Map<NameWithEquals, String> map = new HashMap<NameWithEquals, String>();
		NameWithEquals name1 = new NameWithEquals("Kristoffer", "Chua");
		NameWithEquals name2 = new NameWithEquals("Kelvin", "Chua");
		map.put(name1, "June");
		map.put(name2, "April");
		name2.setFirstName("Kinsley");

		NameWithEquals name3 = new NameWithEquals("Kristoffer", "Chua");
		assertTrue(map.containsKey(name3));
		NameWithEquals name4 = new NameWithEquals("Kelvin", "Chua");
		// Key was changed, so old
		assertFalse(map.containsKey(name4));
	}
}
