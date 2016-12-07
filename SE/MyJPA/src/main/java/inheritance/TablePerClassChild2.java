package inheritance;

import javax.persistence.Entity;

@Entity(name="Inheritance_TPC_Child2")
public class TablePerClassChild2 extends TablePerClassParent {
	private String child2Field;

	public String getChild2Field() {
		return child2Field;
	}

	public void setChild2Field(String child2Field) {
		this.child2Field = child2Field;
	}
}
