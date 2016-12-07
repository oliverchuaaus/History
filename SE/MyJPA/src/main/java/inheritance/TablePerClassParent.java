package inheritance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "Inheritance_TablePerClass_Parent")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TablePerClassParent {
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
 * @DiscriminatorColumn(name = "DTYPE", discriminatorType = ClassName)
 */
/**
 * 3 types of inheritance: a. table per class hierarchy (not recommended) b.
 * single table per class hierarchy c. joined subclass
 */
