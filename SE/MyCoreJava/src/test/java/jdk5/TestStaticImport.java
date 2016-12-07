package jdk5;

//note static keyword
import static java.lang.Math.PI;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestStaticImport {
	@Test
	public void testOldWay() {
		assertEquals(314.0, Math.floor(Math.PI * 100), 0.0);
	}

	@Test
	public void testNewWay() {
		assertEquals(314.0, Math.floor(PI * 100), 0.0);
	}
}
