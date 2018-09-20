package objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class TestObjects {
	@Test
	public void testToString() {
		Objects o = new Objects();
		assertEquals("Custom toString", o.toString());
	}

	@Test
	public void testEquals() {
		Objects o1 = new Objects();
		assertFalse(o1.equals(null));

		Objects o2 = new Objects();
		assertTrue(o1.equals(o2));

		o2.setName("B2");
		assertFalse(o1.equals(o2));
	}

	@Test
	public void testClone() throws CloneNotSupportedException {
		Objects o1 = new Objects();
		o1.setAddress("address");
		o1.setName("name");

		Objects o2 = o1;
		Objects o3 = (Objects) o1.clone();

		assertTrue(o1.equals(o2));
		assertTrue(o1 == o2);

		assertTrue(o1.equals(o3));
		assertFalse(o1 == o3);

		o1.setName("name2");

		assertTrue(o1.equals(o2));
		assertFalse(o1.equals(o3));
	}

	@Test
	public void testReflectionConstructor() throws Exception {
		Class<?> clazz = Class.forName("objects.Objects");
		Objects objects = (Objects) clazz.getDeclaredConstructor().newInstance();
		objects.setAddress("address");
		objects.setName("name");
	}

	@Test
	public void testReflectionMethod() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {
		Class<?> clazz = Class.forName("objects.Objects");
		Objects objects = (Objects) clazz.getDeclaredConstructor().newInstance();
		Method method = clazz.getMethod("setAddress", String.class);
		method.invoke(objects, "address");
		assertEquals("address", objects.getAddress());
	}

	// TODO NIO,io, threads, reflection, inner class
}
