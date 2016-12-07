package association.onetomany.jointable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity(name = "OneToMany_JoinTable_Many")
public class ManyJoinTable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinTable(name = "OneToMany_JoinTable_OneMany", joinColumns = { @JoinColumn(name = "MANY_ID", insertable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ONE_ID", insertable = false, updatable = false) })
	private OneJoinTable one;

	private String manyField;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public OneJoinTable getOne() {
		return one;
	}

	public void setOne(OneJoinTable one) {
		this.one = one;
	}

	public String getManyField() {
		return manyField;
	}

	public void setManyField(String manyField) {
		this.manyField = manyField;
	}

}
