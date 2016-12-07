package jdk5.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestEnums {
	@Test
	public void testSimpleEnum() {
		SimpleEnum num = SimpleEnum.ONE;
		assertTrue(num == SimpleEnum.ONE);
		assertTrue(num.equals(SimpleEnum.ONE));
		assertFalse(num == SimpleEnum.TWO);
	}

	@Test
	public void testOldEnum() {
		int num = OldEnum.ONE;
		num = 2;
		assertTrue(num == OldEnum.TWO);
	}

	@Test
	public void testNewEnum() {
		NewEnum num = NewEnum.TWO;
		// cannot do this: num = 1;
		assertTrue(num == NewEnum.TWO);

		// Enum string = TWO
		assertEquals("TWO", num.toString());
		// Enum ordinal = 2
		assertEquals(2, num.ordinal());
		assertEquals(2, num.getNum());
		assertEquals(4, num.square());

		// Use Enum string to retrieve Enum object.
		assertEquals(num, NewEnum.valueOf("TWO"));

		// Iterate through all Enum values
		NewEnum[] enums = NewEnum.values();
		assertEquals(3, enums.length);
		for (int i = 0; i < enums.length; i++) {
			NewEnum newEnum = enums[i];
			if (i == 0) {
				assertTrue(NewEnum.ZERO == newEnum);
			} else if (i == 1) {
				assertTrue(NewEnum.ONE == newEnum);
			} else if (i == 2) {
				assertTrue(NewEnum.TWO == newEnum);
			}
		}

		// Use Enum field to retrieve correct Enum object.
		int fieldToFind = 1;
		NewEnum enumToFind = null;
		enums = NewEnum.values();
		for (int i = 0; i < enums.length; i++) {
			NewEnum newEnum = enums[i];
			if (newEnum.getNum() == fieldToFind) {
				enumToFind = newEnum;
				break;
			}
		}
		assertEquals(NewEnum.ONE, enumToFind);
	}
}
