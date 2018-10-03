package envers.entities;

import org.hibernate.envers.RevisionListener;

public class ExampleListener implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		ExampleRevEntity rev = (ExampleRevEntity) revisionEntity;
		rev.setUsername(getUserName());
	}

	private String getUserName() {
		return "Betty Boop";
	}
}