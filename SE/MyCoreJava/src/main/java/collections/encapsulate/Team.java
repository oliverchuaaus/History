package collections.encapsulate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
	private static List<String> members = new ArrayList<String>();

	public List<String> getMembers() {
		return Collections.unmodifiableList(members);
	}

	public void addMember(String member) {
		members.add(member);
	}

	public void removeMember(String member) {
		members.remove(member);
	}

}
