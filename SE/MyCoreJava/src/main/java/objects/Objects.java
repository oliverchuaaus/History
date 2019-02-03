package objects;

public class Objects {
	private String name;

	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Objects o = new Objects();
		o.name = name;
		o.address = address;
		return o;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Objects)) {
			return false;
		}
		Objects obj2 = (Objects) obj;

		if (((name == null && obj2.name == null) || (name != null && name.equals(obj2.name)))
				&& ((address == null && obj2.address == null) || (address != null && address.equals(obj2.address)))

		) {
			return true;
		}
		return false;

	}

	@Override
	public int hashCode() {
		return name == null ? 0 : name.hashCode() + address == null ? 0 : address.hashCode();
	}

	@Override
	public String toString() {
		return "Custom toString";
	}

}
