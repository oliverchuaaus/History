package inheritance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Child1")
public class SingleTableChild1 extends SingleTableParent {
	private String child1Field;

	public String getChild1Field() {
		return child1Field;
	}

	public void setChild1Field(String child1Field) {
		this.child1Field = child1Field;
	}
}
