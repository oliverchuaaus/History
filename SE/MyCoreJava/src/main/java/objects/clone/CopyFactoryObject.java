package objects.clone;

public class CopyFactoryObject implements Cloneable {
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

	public static CopyFactoryObject newInstance(CopyFactoryObject cco) {
		CopyFactoryObject cfo = new CopyFactoryObject();
		cfo.setField1(cco.field1);
		cfo.setField2(cco.field2);
		return cfo;
	}
}
