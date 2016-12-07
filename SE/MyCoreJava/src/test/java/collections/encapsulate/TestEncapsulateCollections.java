package collections.encapsulate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

/*
 * Team class' collection is encapsulated by calling Collections.unmodifiableList(members)
 * so that an unmodifiable copy of the collection is returned can only be read but cannot be changed.
 */
public class TestEncapsulateCollections {
	@Test
	public void testModify() throws Exception {
		Team team = new Team();
		team.addMember("member1");
		List<String> members = team.getMembers();
		for (String member : members) {
			assertEquals("member1", member);
		}
	}

	@Test
	public void testBadModify() throws Exception {
		Team team = new Team();
		team.addMember("member1");
		List<String> members = team.getMembers();

		try {
			// Runtime Exception: java.lang.UnsupportedOperationException
			members.add("member2");
			fail("expected exception not thrown");
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
		}
	}
}
