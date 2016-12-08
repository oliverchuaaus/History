package objects.clone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import serialization.transients.SerializableWithTransientObject;

public class TestClone {
	private static final String RESOURCE = ".output/serialization/";

	@Test
	public void testClone() {
		CloneObject co1 = new CloneObject();
		co1.setField1("field1");
		co1.setField2("field2");

		CloneObject co2 = co1.clone();
		assertFalse(co1 == co2);
		assertEquals(co1.getField1(), co2.getField1());
		assertEquals(co1.getField2(), co2.getField2());
	}

	@Test
	public void testCopyConstructor() {
		CopyConstructorObject cco1 = new CopyConstructorObject();
		cco1.setField1("field1");
		cco1.setField2("field2");

		CopyConstructorObject cco2 = new CopyConstructorObject(cco1);
		assertFalse(cco1 == cco2);
		assertEquals(cco1.getField1(), cco2.getField1());
		assertEquals(cco1.getField2(), cco2.getField2());
	}

	@Test
	public void testCopyFactory() {
		CopyFactoryObject cfo1 = new CopyFactoryObject();
		cfo1.setField1("field1");
		cfo1.setField2("field2");

		CopyFactoryObject cfo2 = CopyFactoryObject.newInstance(cfo1);
		assertFalse(cfo1 == cfo2);
		assertEquals(cfo1.getField1(), cfo2.getField1());
		assertEquals(cfo1.getField2(), cfo2.getField2());
	}

	@Test
	public void testCloneStringArray() {
		String[] array1 = new String[] { "string1" };

		String[] array2 = array1.clone();
		array2[0] = "string2";

		assertEquals("string1", array1[0]);
		assertEquals("string2", array2[0]);
	}

	@Test
	public void testCloneArray() {
		CloneObject[] array1 = new CloneObject[1];
		array1[0] = new CloneObject();
		array1[0].setField1("field1");
		array1[0].setField2("field2");

		CloneObject[] array2 = array1.clone();
		for (int i = 0; i < array2.length; i++) {
			array2[i] = array2[i].clone();
		}
		array2[0].setField1("field3");
		array2[0].setField2("field4");

		assertEquals("field1", array1[0].getField1());
		assertEquals("field3", array2[0].getField1());
	}

	@Test
	public void testCloneString2DArray() {
		String[][] array1 = new String[][] { { "string1" }, { "string2" } };

		String[][] array2 = array1.clone();
		for (int i = 0; i < array2.length; i++) {
			array2[i] = array2[i].clone();
		}

		array2[0][0] = "string3";
		array2[1][0] = "string4";

		assertEquals("string1", array1[0][0]);
		assertEquals("string2", array1[1][0]);
		assertEquals("string3", array2[0][0]);
		assertEquals("string4", array2[1][0]);
	}

	@Test
	public void testCloneUsingSerialization() throws IOException, ClassNotFoundException {
		SerializableWithTransientObject so = new SerializableWithTransientObject();
		so.setField1("field1");

		File f = new File(RESOURCE);
		f.mkdirs();

		FileOutputStream fos = new FileOutputStream(RESOURCE + "CloneSerializableObject.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		try {
			oos.writeObject(so);
		} finally {
			oos.close();
		}
		so.setField1("field2");
		FileInputStream fis = new FileInputStream(RESOURCE + "CloneSerializableObject.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			SerializableWithTransientObject so2 = (SerializableWithTransientObject) ois.readObject();
			assertEquals("field1", so2.getField1());
		} finally {
			ois.close();
		}
	}
}
