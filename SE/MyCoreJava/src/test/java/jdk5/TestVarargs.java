package jdk5;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestVarargs {

	private String concat(String[] args) {
		String str = "";
		for (String string : args) {
			str += string;
		}
		return str;
	}

	// Cannot name method with the same signature as the first one
	private String concat2(String... args) {
		String str = "";
		// args implemented transparently as array
		for (String string : args) {
			str += string;
		}
		return str;
	}

	@Test
	public void testOldWay() {
		String str = concat(new String[] { "Hello", "World" });
		assertEquals("HelloWorld", str);
	}

	@Test
	public void testNewWay() {
		// Can use array as param
		String str = concat2(new String[] { "Hello", "World" });
		assertEquals("HelloWorld", str);

		str = concat2("Hello", "World");
		assertEquals("HelloWorld", str);

		// Can only use varargs in the context of a method param.
		// String... test;
	}

}
