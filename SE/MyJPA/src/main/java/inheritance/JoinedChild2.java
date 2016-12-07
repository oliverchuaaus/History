package inheritance;

import javax.persistence.Entity;

@Entity(name = "Inheritance_Joined_Child2")
public class JoinedChild2 extends JoinedParent {
	private static final long serialVersionUID = 1L;
	private String child2Field;

	public String getChild2Field() {
		return child2Field;
	}

	public void setChild2Field(String child2Field) {
		this.child2Field = child2Field;
	}
}
