package asserts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

//This test will fail as is because assert is not enabled by default.
//To enable add -ea as VM arguments
//In Eclipse, go to Run/Debug Configuration, Click corresponding class or class/method,
//go to arguments tab, and add -ea under VM arguments panel.
public class TestAsserts {

	@Test
	public void testSimple() {
		boolean b = false;
		try {
			assert b;
			fail("Expected exception not thrown");
		} catch (AssertionError e) {
			assertTrue(true);
			assertEquals(null, e.getMessage());
		} catch (Exception e) {
			fail("Unexpected exception thrown");
		}
	}

	@Test
	public void testWithMessage() {
		boolean b = 1 == 2;
		try {
			assert b : "We are not expecting false assert";
			fail("Expected exception not thrown");
		} catch (AssertionError e) {
			assertTrue(true);
			assertEquals("We are not expecting false assert", e.getMessage());
		} catch (Exception e) {
			fail("Unexpected exception thrown");
		}
	}
}
