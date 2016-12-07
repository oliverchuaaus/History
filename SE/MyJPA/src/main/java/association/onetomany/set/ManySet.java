package association.onetomany.set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "OneToMany_Set_Many")
public class ManySet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private OneSet one;

	private String manyField;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public OneSet getOne() {
		return one;
	}

	public void setOne(OneSet one) {
		this.one = one;
	}

	public String getManyField() {
		return manyField;
	}

	public void setManyField(String manyField) {
		this.manyField = manyField;
	}

}
