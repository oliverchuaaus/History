package association.onetomany.elementcollection;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity(name = "OneToMany_EC_One")
public class OneElementCollection {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ElementCollection
	@CollectionTable(name = "OneToMany_EC_Many", joinColumns = { @JoinColumn(name = "OWNER_ID") })
	@Column(name = "MANY_FIELD")
	private List<String> manyList = new ArrayList<String>();

	public List<String> getManyList() {
		return manyList;
	}

	public void setManyList(List<String> manyList) {
		this.manyList = manyList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
