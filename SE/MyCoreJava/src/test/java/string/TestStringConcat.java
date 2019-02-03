package string;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Illustrates the speed difference between + operator and StringBuilder.append,
 * when performing many concatenations.
 */
// To make the profiling correct, -Xint should be added as VM argument
// to turn off the HotSpot compiler.
// This ensures the program is always interpreted, and will execute in a uniform
// environment from start to finish, without any compilation into native code.)
public class TestStringConcat {

	/*
	 * Output is
	 * 
	 * iterations: 100 Concatenate: 0 milliseconds Buffer: 0 milliseconds Builder: 0
	 * milliseconds
	 * 
	 * iterations: 1000 Concatenate: 15 milliseconds Buffer: 0 milliseconds Builder:
	 * 0 milliseconds
	 * 
	 * iterations: 10000 Concatenate: 875 milliseconds Buffer: 0 milliseconds
	 * Builder: 16 milliseconds
	 * 
	 * iterations: 100000 Buffer: 109 milliseconds Builder: 110 milliseconds
	 * 
	 * iterations: 1000000 Buffer: 1047 milliseconds Builder: 922 milliseconds
	 */
	@Test
	public void testConcat() {
		concatenate(1000);
		concatenate(10000);
		concatenate(100000);
	}

	private static void concatenate(int iterations) {
		// Need to skip as it is too slow
		boolean skipConcat = iterations <= 10000;

		long concatTime = 0;
		if (skipConcat) {
			long start = System.currentTimeMillis();
			doWithConcatenationOperator(iterations);
			long finish = System.currentTimeMillis();
			concatTime = finish - start;
		}

		long start = System.currentTimeMillis();
		doWithStringBuffer(iterations);
		long finish = System.currentTimeMillis();
		long bufferTime = finish - start;

		start = System.currentTimeMillis();
		doWithStringBuilder(iterations);
		finish = System.currentTimeMillis();
		long builderTime = finish - start;

		if (skipConcat) {
			assertTrue(concatTime >= builderTime);
		}
		// Less than 16 ms difference is negligible
		assertTrue("Difference " + Math.abs(bufferTime - builderTime) + " is more than 16 ms.",
				Math.abs(bufferTime - builderTime) <= 16);
	}

	private static String doWithConcatenationOperator(int iterations) {
		String result = "start";
		for (int idx = 0; idx < iterations; idx++) {
			result = result + "blah";
		}
		return result;
	}

	private static String doWithStringBuffer(int iterations) {
		StringBuffer result = new StringBuffer("start");
		for (int idx = 0; idx < iterations; idx++) {
			result.append("blah");
		}
		return result.toString();
	}

	private static String doWithStringBuilder(int iterations) {
		StringBuilder result = new StringBuilder("start");
		for (int idx = 0; idx < iterations; idx++) {
			result.append("blah");
		}
		return result.toString();
	}

}
