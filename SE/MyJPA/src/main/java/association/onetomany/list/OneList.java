package association.onetomany.list;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * OneToMany Map Bidirectional
 * 
 * Composite
 */
@Entity(name = "OneToMany_List_One")
public class OneList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy = "one")
	List<ManyList> many = new ArrayList<ManyList>();

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public List<ManyList> getMany() {
		return many;
	}

	public void setMany(List<ManyList> many) {
		this.many = many;
	}

}
