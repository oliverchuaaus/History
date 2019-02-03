package serialization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class TestSerializable {
	private static final String SOURCE = "src/test/resources/serialization/";
	private static final String RESOURCE = ".output/serialization/";

	@Test
	public void testSerializeDeserializeSimple() throws IOException, ClassNotFoundException {
		SerializableObject so = new SerializableObject();
		so.setField1("field1");

		File f = new File(RESOURCE);
		f.mkdirs();

		FileOutputStream fos = new FileOutputStream(RESOURCE + "SerializableObject.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		try {
			oos.writeObject(so);
		} finally {
			oos.close();
		}
		FileInputStream fis = new FileInputStream(RESOURCE + "SerializableObject.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			so = (SerializableObject) ois.readObject();
			assertEquals("field1", so.getField1());
		} finally {
			ois.close();
		}
	}

	// OlderSerializableObject.ser is a serialised Java object with
	// serialVersionUID = 1 while the current class has serialVersionUID = 2
	@Test
	public void testDeserializeWrongVersion() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(SOURCE + "OlderSerializableObject.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			ois.readObject();
			fail("expected exception not thrown");
		} catch (InvalidClassException e) {
			assertTrue(e.getMessage().indexOf("local class incompatible") != -1);
			assertTrue(e.getMessage()
					.indexOf("stream classdesc serialVersionUID = 1, local class serialVersionUID = 2") != -1);
		} catch (Exception e) {
			fail("unexpected exception thrown");
		} finally {
			ois.close();
		}
	}

}
