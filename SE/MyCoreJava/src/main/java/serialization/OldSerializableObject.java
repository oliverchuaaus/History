package serialization;

import java.io.Serializable;

public class OldSerializableObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String field1;

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField1() {
		return field1;
	}
}
