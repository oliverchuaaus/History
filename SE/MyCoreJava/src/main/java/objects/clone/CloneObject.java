package objects.clone;

public class CloneObject implements Cloneable {
	private String field1;
	private String field2;

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public CloneObject clone() {
		CloneObject co = new CloneObject();
		co.field1 = field1;
		co.field2 = field2;
		return co;
	}
}
