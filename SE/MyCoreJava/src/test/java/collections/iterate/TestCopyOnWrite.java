package collections.iterate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

//Shows behaviour of CopyOnWriteArrayList.
//Recommended to use when list is mostly read only.

//Tradeoffs are:
//1. list is rebuilt when list is modified
//2. iterator doesn't reflect modifications after iterator creation.
public class TestCopyOnWrite {
	@Test
	public void testCopyOnWrite() throws Exception {
		List<String> stateList = new ArrayList<String>();
		stateList.add("Sydney");
		stateList.add("Melbourne");

		CopyOnWriteArrayList<String> stateList2 = new CopyOnWriteArrayList<String>(
				stateList);

		Iterator<String> iter = stateList2.iterator();
		stateList2.add("Hobart");

		int i = 0;
		while (iter.hasNext()) {
			String state = iter.next();
			switch (i) {
			case 0:
				assertEquals("Sydney", state);
				break;
			case 1:
				assertEquals("Melbourne", state);
				break;
			case 2:
				assertEquals("Hobart", state);
				break;
			default:
				fail("unexpected element in list");

			}
			i++;
		}
	}

	@Test
	public void testBad() throws Exception {
		List<String> stateList = new ArrayList<String>();
		stateList.add("Sydney");
		stateList.add("Melbourne");

		Iterator<String> iter = stateList.iterator();
		stateList.add("Hobart");

		// fail fast when list has been modified after iteration creation.
		try {
			while (iter.hasNext()) {
				iter.next();
			}
			fail("Expected exception not thrown");
		} catch (ConcurrentModificationException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception thrown");
		}
	}

}
