package individual.simple.locking;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity(name = "Individual_Simple_Locking")
public class LockingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// No default access type. Default is set by how Id is set.
	private Long id;

	@Version
	private int version;

	private String stringField;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

}
