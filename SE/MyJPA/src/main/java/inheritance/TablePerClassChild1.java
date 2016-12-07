package inheritance;

import javax.persistence.Entity;

@Entity(name = "Inheritance_TPC_Child1")
public class TablePerClassChild1 extends TablePerClassChild2 {
	private String child1Field;

	public String getChild1Field() {
		return child1Field;
	}

	public void setChild1Field(String child1Field) {
		this.child1Field = child1Field;
	}
}
