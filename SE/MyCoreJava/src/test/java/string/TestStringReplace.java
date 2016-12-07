package string;

import static org.junit.Assert.assertEquals;

import java.util.regex.Pattern;

import org.junit.Test;

public class TestStringReplace {

	@Test
	public void testStringReplace() {
		String pattern = Pattern.quote("Apples and Oranges");
		String string = "ApPles and ORanGes are good for you.";
		// (?i) makes matching ignores case
		String result = string.replaceAll("(?i)" + pattern + "",
				"APPLES & ORANGES");
		assertEquals("APPLES & ORANGES are good for you.", result);
	}

}
