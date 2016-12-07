package association.onetomany.list;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "OneToMany_List_Many")
public class ManyList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private OneList one;

	private String manyField;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public OneList getOne() {
		return one;
	}

	public void setOne(OneList one) {
		this.one = one;
	}

	public String getManyField() {
		return manyField;
	}

	public void setManyField(String manyField) {
		this.manyField = manyField;
	}
}
