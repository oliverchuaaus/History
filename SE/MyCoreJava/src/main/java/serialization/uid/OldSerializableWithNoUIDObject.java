package serialization.uid;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OldSerializableWithNoUIDObject implements Serializable {

	private String field1;

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField1() {
		return field1;
	}
}
