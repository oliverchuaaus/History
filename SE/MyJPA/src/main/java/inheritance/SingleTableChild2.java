package inheritance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Child2")
public class SingleTableChild2 extends SingleTableParent {
	private String child2Field;

	public String getChild2Field() {
		return child2Field;
	}

	public void setChild2Field(String child2Field) {
		this.child2Field = child2Field;
	}
}
