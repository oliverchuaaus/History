package serialization.transients;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class TestSerializableTransient {
	private static final String SOURCE = "src/test/resources/serialization/";
	private static final String RESOURCE = ".output/serialization/";

	@Test
	public void testSerializeNoTransient() throws IOException {
		SerializableWithNoTransientObject so = new SerializableWithNoTransientObject();
		so.setField1("field1");
		FileInputStream fis = new FileInputStream(SOURCE + "OlderSerializableObject.ser");
		// If we didn't set non-Serializable field, no exception will be thrown
		so.setStream(fis);
		FileOutputStream fos = new FileOutputStream(RESOURCE + "SerializableObject.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		try {
			oos.writeObject(so);
			fail("expected exception not thrown");
		} catch (NotSerializableException e) {
			assertTrue(e.getMessage().indexOf("java.io.FileInputStream") != -1);
		} catch (Exception e) {
			fail("unexpected exception thrown");
		} finally {
			oos.close();
		}

	}

	@Test
	public void testSerializeTransient() throws IOException {
		SerializableWithTransientObject so = new SerializableWithTransientObject();
		so.setField1("field1");
		FileInputStream fis = new FileInputStream(SOURCE + "OlderSerializableObject.ser");
		so.setStream(fis);
		FileOutputStream fos = new FileOutputStream(RESOURCE + "SerializableObject.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		try {
			oos.writeObject(so);
			assertTrue(true);
		} catch (Exception e) {
			fail("unexpected exception thrown");
		} finally {
			oos.close();
		}
	}

}
