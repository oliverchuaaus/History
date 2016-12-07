package jdk5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestAutobox {
	@Test
	public void testOldWay() {
		Integer obj = new Integer(1);
		int sum = obj.intValue() + 2;
		obj = new Integer(sum);
		assertEquals(3, obj.intValue());
	}

	@Test
	public void testNewWay() {
		Integer obj = new Integer(2);
		obj = obj + 1;
		assertEquals(3, obj.intValue());
		assertTrue(3 == obj);
		assertTrue(obj == 3);
	}

	@Test
	public void testSwitchInt() {
		// Previously only char and byte can be used in switch
		int i = 20;
		switch (i) {
		case 10:
			fail("Wrong case");
			break;
		case 20:
			break;
		default:
			fail("Wrong case");
			break;
		}
	}

	@Test
	public void testReturn() {
		Integer num = 2;
		int i = convert(num);
		assertEquals(2, i);
	}

	private int convert(Integer num) {
		return num;
	}

	@Test
	public void testUnboxNull() {
		Integer obj = null;
		try {
			@SuppressWarnings({ "unused", "null" })
			int num = obj;
			fail("expected exception not thrown");
		} catch (NullPointerException e) {
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail("unexpected exception thrown");
		}
	}

	@Test
	public void testUnboxCompare() {
		Integer obj1 = new Integer(22);
		Integer obj2 = new Integer(22);
		int num1 = 22;

		assertFalse(obj1 == obj2);
		assertTrue(obj1.equals(obj2));

		assertTrue(obj1 == num1);
		assertTrue(num1 == obj1);

		assertTrue(obj1.equals(num1));
		// cannot do this
		// assertTrue(num1.equals(num1));
	}

}
