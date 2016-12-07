package serialization.uid;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class TestSerializableUID {
	private static final String RESOURCE = "output/serialization/";

	@Test
	public void testSerializeSimple() throws IOException {
		SerializableWithNoUIDObject so = new SerializableWithNoUIDObject();
		so.setField1("field1");
		FileOutputStream fos = new FileOutputStream(RESOURCE
				+ "SerializableWithNoUIDObject.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		try {
			oos.writeObject(so);
		} finally {
			oos.close();
		}

	}

	@Test
	public void testDeserializeWithNoUID() throws IOException {
		FileInputStream fis = new FileInputStream(RESOURCE
				+ "SerializableWithNoUIDObject.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			ois.readObject();
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail("unexpected exception thrown");
		} finally {
			ois.close();
		}
	}

}
