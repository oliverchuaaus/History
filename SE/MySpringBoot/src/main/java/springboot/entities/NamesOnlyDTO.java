package springboot.entities;

public class NamesOnlyDTO {
	private final String firstName, lastName;

	public NamesOnlyDTO(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}
}
