
//http://www.vogella.com/tutorials/Mockito/article.html

import static org.mockito.Mockito.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import org.junit.Test;

import static org.junit.Assert.*;

public class MockitoTest {

	@Test
	public void test1() {
		// create mock
		MyClass test = mock(MyClass.class);

		// define return value for method getUniqueId()
		when(test.getUniqueId()).thenReturn(43);

		// use mock in test....
		assertEquals(test.getUniqueId(), 43);
	}

	// demonstrates the return of multiple values
	@Test
	public void testMoreThanOneReturnValue() {
		@SuppressWarnings("unchecked")
		Iterator<String> i = mock(Iterator.class);
		when(i.next()).thenReturn("Mockito").thenReturn("rocks");
		String result = i.next() + " " + i.next();
		// assert
		assertEquals("Mockito rocks", result);
	}

	// this test demonstrates how to return values based on the input
	@Test
	public void testReturnValueDependentOnMethodParameter() {
		@SuppressWarnings("unchecked")
		Comparable<String> c = mock(Comparable.class);
		when(c.compareTo("Mockito")).thenReturn(1);
		when(c.compareTo("Eclipse")).thenReturn(2);
		// assert
		assertEquals(1, c.compareTo("Mockito"));
	}

	// this test demonstrates how to return values independent of the input
	// value

	@Test
	public void testReturnValueInDependentOnMethodParameter() {
		@SuppressWarnings("unchecked")
		Comparable<Integer> c = mock(Comparable.class);
		when(c.compareTo(anyInt())).thenReturn(-1);
		// assert
		assertEquals(-1, c.compareTo(9));
	}

	// return a value based on the type of the provide parameter

	@Test
	public void testReturnValueInDependentOnMethodParameter2() {
		@SuppressWarnings("unchecked")
		Comparable<Todo> c = mock(Comparable.class);
		when(c.compareTo(isA(Todo.class))).thenReturn(0);
		// assert
		assertEquals(0, c.compareTo(new Todo(1)));
	}

	@Test
	public void testWhenThenThrow() {
		Properties properties = mock(Properties.class);
		when(properties.get("Anddroid")).thenThrow(new IllegalArgumentException(""));
		try {
			properties.get("Anddroid");
			fail("Anddroid is misspelled");
		} catch (IllegalArgumentException ex) {
			// good!
		}
	}

	@Test
	public void testDoReturn() {
		Properties properties = new Properties();
		Properties spyProperties = spy(properties);
		doReturn("42").when(spyProperties).get("shoeSize");
		String value = (String) spyProperties.get("shoeSize");
		assertEquals("42", value);
	}

	public void testLinkedListSpyWrong() {
		// Lets mock a LinkedList
		List<String> list = new LinkedList<>();
		List<String> spy = spy(list);

		// this does not work
		// real method is called so spy.get(0)
		// throws IndexOutOfBoundsException (list is still empty)
		when(spy.get(0)).thenReturn("foo");

		assertEquals("foo", spy.get(0));
	}

	@Test
	public void testLinkedListSpyCorrect() {
		// Lets mock a LinkedList
		List<String> list = new LinkedList<>();
		List<String> spy = spy(list);

		// You have to use doReturn() for stubbing
		doReturn("foo").when(spy).get(0);

		assertEquals("foo", spy.get(0));
	}
	
	@Test
	public void testVerify()  {
	    // create and configure mock
	    MyClass test = mock(MyClass.class);
	    when(test.getUniqueId()).thenReturn(43);


	    // call method testing on the mock with parameter 12
	    test.testing(12);
	    test.getUniqueId();
	    test.getUniqueId();


	    // now check if method testing was called with the parameter 12
	    verify(test).testing(12);

	    // was the method called twice?
	    verify(test, times(2)).getUniqueId();

	    // other alternatives for verifying the number of method calls for a method
	    verify(test, never()).someMethod("never called");
	    verify(test, atLeastOnce()).testing(12);
	    verify(test, atLeast(2)).getUniqueId();
	    verify(test, times(2)).getUniqueId();
	    verify(test, atMost(2)).getUniqueId();
	    // This let's you check that no other methods where called on this object.
	    // You call it after you have verified the expected method calls.
	    verifyNoMoreInteractions(test);
	}
}
