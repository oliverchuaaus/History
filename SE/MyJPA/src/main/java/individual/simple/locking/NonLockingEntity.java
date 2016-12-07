package individual.simple.locking;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Individual_Simple_NonLocking")
public class NonLockingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// No default access type. Default is set by how Id is set.
	private Long id;

	private String stringField;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

}
