package springboot.entities;

public interface NamesOnlyDefaultMethod {

	String getFirstName();

	String getLastName();

	public default String getFullName() {
		return getFirstName() + " " + getLastName();
	}
}
