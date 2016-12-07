package jdk5.foreach;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestForEach {
	@Test
	public void testList() {
		List<String> list = new ArrayList<String>();
		list.add("String 1");
		for (String string : list) {
			assertEquals("String 1", string);
		}
	}

	@Test
	public void testArray() {
		String[] array = new String[1];
		array[0] = "String 1";
		for (String string : array) {
			assertEquals("String 1", string);
		}
	}
}
