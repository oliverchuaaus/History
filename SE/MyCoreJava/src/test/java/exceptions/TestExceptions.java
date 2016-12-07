package exceptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestExceptions {
	@Test
	public void testStackTrace() throws Exception {
		try {
			throw new Exception("my exception");
		} catch (Exception e) {
			String stackTrace = ExceptionHelper.getStackTrace(e);
			assertTrue(stackTrace.indexOf("java.lang.Exception: my exception") > -1);
		}
	}

	@Test
	public void testControlFlow() {
		String str = "";

		try {
			try {
				str += "1";
				throw new Exception();
			} catch (Exception e) {
				str += "2";
				throw e;
			} finally {
				str += "3";
			}
		} catch (Exception e) {
			str += "4";
		}
		str += "5";

		assertEquals("12345", str);
	}

}
