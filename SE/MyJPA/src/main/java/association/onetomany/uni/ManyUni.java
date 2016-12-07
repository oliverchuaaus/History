package association.onetomany.uni;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "OneToMany_Uni_Many")
public class ManyUni {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String manyField;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getManyField() {
		return manyField;
	}

	public void setManyField(String manyField) {
		this.manyField = manyField;
	}
}
