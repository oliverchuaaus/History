package collections.iterate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

//This illustrates the ConcurrentModificationException single thread issue
public class TestRemoveInIterator {
	@Test
	public void testGoodIterator() throws Exception {
		List<String> stringList = new ArrayList<String>();
		stringList.add("Sydney");
		stringList.add("Melbourne");
		stringList.add("Hobart");
		stringList.add("Perth");
		stringList.add("Darwin");
		stringList.add("Brisbane");
		for (Iterator<String> iterator = stringList.iterator(); iterator.hasNext();) {
			String capital = (String) iterator.next();
			if ("Hobart".equals(capital)) {
				// This is the correct way of removing elements in a list using
				// an iterator.
				iterator.remove();
			}
		}
		assertEquals(5, stringList.size());
		for (int i = 0; i < stringList.size(); i++) {
			String capital = (String) stringList.get(i);
			switch (i) {
			case 0:
				assertEquals("Sydney", capital);
				break;
			case 1:
				assertEquals("Melbourne", capital);
				break;
			case 2:
				assertEquals("Perth", capital);
				break;
			case 3:
				assertEquals("Darwin", capital);
				break;
			case 4:
				assertEquals("Brisbane", capital);
				break;
			}
		}
	}

	@Test
	public void testBadIterator() throws Exception {
		List<String> stringList = new ArrayList<String>();
		stringList.add("Sydney");
		stringList.add("Melbourne");
		stringList.add("Hobart");
		stringList.add("Perth");
		stringList.add("Darwin");
		stringList.add("Brisbane");

		try {
			for (Iterator<String> iterator = stringList.iterator(); iterator.hasNext();) {
				String capital = (String) iterator.next();
				// After modifying the list and iterator.next() is called,
				// ConcurrentModificationException will be thrown.

				if ("Hobart".equals(capital)) {
					stringList.remove(capital);
					// Exception not thrown after this call, but the next time
					// iterator.next() is called
				}
			}
			fail("expected exception not thrown");
		} catch (ConcurrentModificationException e) {
			assertTrue(true);
		}

		assertEquals(5, stringList.size());
		for (int i = 0; i < stringList.size(); i++) {
			String capital = (String) stringList.get(i);
			switch (i) {
			case 0:
				assertEquals("Sydney", capital);
				break;
			case 1:
				assertEquals("Melbourne", capital);
				break;
			case 2:
				assertEquals("Perth", capital);
				break;
			case 3:
				assertEquals("Darwin", capital);
				break;
			case 4:
				assertEquals("Brisbane", capital);
				break;
			}
		}
	}

	@Test
	public void testBadIterator2() throws Exception {
		List<String> stringList = new ArrayList<String>();
		stringList.add("Sydney");
		stringList.add("Melbourne");
		stringList.add("Hobart");
		stringList.add("Hobart");
		stringList.add("Darwin");
		stringList.add("Brisbane");

		for (int i = 0; i < stringList.size(); i++) {
			String capital = (String) stringList.get(i);
			if ("Hobart".equals(capital)) {
				stringList.remove(capital);
				// Because it changes stringList, some elements will be skipped,
				// which we don't want
			}
		}

		assertEquals(5, stringList.size());
		for (int i = 0; i < stringList.size(); i++) {
			String capital = (String) stringList.get(i);
			switch (i) {
			case 0:
				assertEquals("Sydney", capital);
				break;
			case 1:
				assertEquals("Melbourne", capital);
				break;
			case 2:
				// This entry was skipped
				assertEquals("Hobart", capital);
				break;
			case 3:
				assertEquals("Darwin", capital);
				break;
			case 4:
				assertEquals("Brisbane", capital);
				break;
			}
		}
	}
}
