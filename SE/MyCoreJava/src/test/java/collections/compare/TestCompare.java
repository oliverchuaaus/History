package collections.compare;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class TestCompare {

	@Test
	public void testComparable() throws Exception {
		List<Apple> list = new ArrayList<Apple>();
		list.add(new Apple("Granny Smith", "small"));
		list.add(new Apple("Red Delicious", "small"));
		list.add(new Apple("Pink Lady", "small"));

		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			Apple a = list.get(i);
			switch (i) {
			case 0:
				assertEquals("Granny Smith", a.getVariety());
				break;
			case 1:
				assertEquals("Pink Lady", a.getVariety());
				break;
			case 2:
				assertEquals("Red Delicious", a.getVariety());
				break;
			default:
				fail("unexpected element in list");
			}
		}
	}

	public void testComparator() throws Exception {
		List<Apple> list = new ArrayList<Apple>();
		list.add(new Apple("Granny Smith", "small"));
		list.add(new Apple("Red Delicious", "small"));
		list.add(new Apple("Granny Smith", "medium"));

		Collections.sort(list, new AppleComparator());
		for (int i = 0; i < list.size(); i++) {
			Apple a = list.get(i);
			switch (i) {
			case 0:
				assertEquals("Granny Smith", a.getVariety());
				assertEquals("small", a.getFruitSize());
				break;
			case 1:
				assertEquals("Granny Smith", a.getVariety());
				assertEquals("medium", a.getFruitSize());
				break;
			case 2:
				assertEquals("Red Delicious", a.getVariety());
				assertEquals("small", a.getFruitSize());
				break;
			default:
				fail("unexpected element in list");

			}
		}
	}
}
