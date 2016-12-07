package exceptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

// Source: http://today.java.net/pub/a/today/2006/04/06/exception-handling-antipatterns.html
public class TestExceptionAntipatterns {
	@Test
	public void testLogAndThrow() {
		String exceptionString = "my exception";
		String stackTrace = "";
		try {
			try {
				throw new MyCheckedException(exceptionString);
			} catch (Exception e) {
				stackTrace += ExceptionHelper.getStackTrace(e);
				throw e;
			}
		} catch (Exception e) {
			stackTrace += ExceptionHelper.getStackTrace(e);
		}
		int firstIndex = stackTrace.indexOf(exceptionString);
		int secondIndex = stackTrace.indexOf(exceptionString, firstIndex);
		// i.e. same stack trace written twice.
		// This will be confusing for the support team.
		assertTrue(secondIndex > -1);
	}

	// Throwing Exception.
	// Defeats the purpose of declaring checked exceptions.
	// Declare each checked Exception possible.

	// Throwing Kitchen Sink.
	// If handling is same for all, wrap them in a custom exception

	// Catching Exception
	// Blindly catching all exceptions and failing to cater for newly introduced
	// exception
	@Test
	public void testDestructiveWrapping() throws Exception {
		String exceptionString = "my exception";
		String stackTrace = "";
		try {
			try {
				Exception e = new Exception(exceptionString);
				StackTraceElement ste = new StackTraceElement("declaringClass",
						"methodName", "fileName", 14344);
				e.setStackTrace(new StackTraceElement[] { ste });
				throw e;
			} catch (Exception e) {
				throw new MyCheckedException(e.getMessage());
			}
		} catch (Exception e) {
			stackTrace += ExceptionHelper.getStackTrace(e);
		}

		String expectedStackTrace = "declaringClass.methodName(fileName:14344)";
		// Original stack trace is lost
		assertEquals(-1, stackTrace.indexOf(expectedStackTrace));
	}

	// Log and Return Null
	// Silently Fails

	// Catch and Ignore
	// Silently Fails
	@Test
	public void testThrowFromWithinFinally() throws Exception {
		String exceptionString = "my exception";
		String stackTrace = "";
		try {
			try {
				throw new MyCheckedException(exceptionString);
			} catch (Exception e) {
			} finally {
				methodThatThrowsException("my another exception");
			}
		} catch (Exception e) {
			stackTrace += ExceptionHelper.getStackTrace(e);
		}

		// Original exception is lost
		assertEquals(-1, stackTrace.indexOf(exceptionString));
		// Only second exception is caught
		assertNotSame(-1, stackTrace.indexOf("my another exception"));

	}

	private void methodThatThrowsException(String exceptionString)
			throws MyCheckedException {
		throw new MyCheckedException(exceptionString);
	}

	// Multi-line Log Message
	// Log messages may be split in multi-threaded environment

	// Unsupported Operation Returning Null
	// Silently Fails. Should throw UnsupportedOperationException

	// Ignoring InterruptedException
	@Test
	public void testRelyingOnGetCause() throws Exception {
		String exceptionString = "my exception";
		try {
			try {
				MyRuntimeException mre = new MyRuntimeException("my root cause");
				Exception e = new Exception(exceptionString, mre);
				StackTraceElement ste = new StackTraceElement("declaringClass",
						"methodName", "fileName", 14344);
				e.setStackTrace(new StackTraceElement[] { ste });
				throw e;
			} catch (Exception e) {
				MyCheckedException mce = new MyCheckedException(e.getMessage());
				// initCause can only be called when exception constructor
				// didn't have a Throwable as a param
				mce.initCause(e);
				throw mce;
			}
		} catch (MyCheckedException e) {
			// Throwable t = e.getCause();
			// Fail because getCause is java.lang.Exception
			// assertTrue(t instanceof MyRuntimeException);

			Throwable t = getRootCause(e);
			assertTrue(t instanceof MyRuntimeException);
			assertNotSame(-1, t.getMessage().indexOf("my root cause"));
		}
	}

	// This method and below was copied from Apache Common Lang ExceptionUtils
	private Throwable getRootCause(Throwable throwable) {
		List<Throwable> list = getThrowableList(throwable);
		return list.size() < 2 ? null : (Throwable) list.get(list.size() - 1);
	}

	private List<Throwable> getThrowableList(Throwable throwable) {
		List<Throwable> list = new ArrayList<Throwable>();
		while (throwable != null && list.contains(throwable) == false) {
			list.add(throwable);
			throwable = throwable.getCause();
		}
		return list;
	}

}
