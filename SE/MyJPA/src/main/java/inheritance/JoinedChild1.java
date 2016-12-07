package inheritance;

import javax.persistence.Entity;

@Entity(name = "Inheritance_Joined_Child1")
public class JoinedChild1 extends JoinedParent {
	private static final long serialVersionUID = 1L;
	private String child1Field;

	public String getChild1Field() {
		return child1Field;
	}

	public void setChild1Field(String child1Field) {
		this.child1Field = child1Field;
	}
}
