package string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestString {
	@Test
	public void test() {
		String s1 = new String("A"); // not recommended, use String s1 = "A"
		String s2 = new String("A"); // not recommended, use String s2 = "A"

		assertFalse(s1 == s2);
		assertTrue(s1.equals(s2));

		String s3 = "A"; // goes into a String pool.
		String s4 = "A"; // refers to String already in the pool.

		assertTrue(s3 == s4);
		assertTrue(s3.equals(s4));
	}

	@Test
	public void testIntern() {
		String s1 = new String("A"); // not recommended, use String s1 = "A"
		String s2 = new String("A"); // not recommended, use String s2 = "A"

		assertTrue(s1.equals(s2));
		assertFalse(s1 == s2);
		assertFalse(s1 == s2.intern());
		assertFalse(s1.intern() == s2);
		assertTrue(s1.intern() == s2.intern());

		String s3 = "A"; // goes into a String pool.
		assertTrue(s3 == s2.intern());

	}
}
