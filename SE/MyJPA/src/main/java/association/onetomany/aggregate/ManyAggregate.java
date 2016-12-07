package association.onetomany.aggregate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "OneToMany_Aggregate_Many")
public class ManyAggregate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "one_fk", insertable = false, updatable = false)
	private OneAggregate one;

	private String manyField;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public OneAggregate getOne() {
		return one;
	}

	public void setOne(OneAggregate one) {
		this.one = one;
	}

	public String getManyField() {
		return manyField;
	}

	public void setManyField(String manyField) {
		this.manyField = manyField;
	}
}
