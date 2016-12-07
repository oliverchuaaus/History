package inheritance;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "Inheritance_Joined_Parent")
@Inheritance(strategy = InheritanceType.JOINED)
public class JoinedParent implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String parentField;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParentField() {
		return parentField;
	}

	public void setParentField(String parentField) {
		this.parentField = parentField;
	}
}
/**
 * Sensible default values:
 * 
 * @PrimaryKeyJoinColumn(name = PKOfSuperClass)
 */
