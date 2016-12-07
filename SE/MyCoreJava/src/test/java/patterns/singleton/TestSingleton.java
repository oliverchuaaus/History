package patterns.singleton;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestSingleton {

	@Test
	public void testSingleton() {
		// private constructor not available
		// Singleton s = new Singleton();

		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		assertTrue(s1 == s2);
		assertEquals("message", s1.method());
	}

	@Test
	public void testEnumSingleton() {
		// private constructor not available
		// EnumSingleton s = new EnumSingleton();

		EnumSingleton s1 = EnumSingleton.INSTANCE;
		EnumSingleton s2 = EnumSingleton.INSTANCE;
		assertTrue(s1 == s2);
		assertEquals("message", s1.method());
	}

}
