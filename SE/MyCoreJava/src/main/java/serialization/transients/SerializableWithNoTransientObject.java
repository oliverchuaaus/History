package serialization.transients;

import java.io.InputStream;
import java.io.Serializable;

public class SerializableWithNoTransientObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private String field1;
	private InputStream stream;

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField1() {
		return field1;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}
}
