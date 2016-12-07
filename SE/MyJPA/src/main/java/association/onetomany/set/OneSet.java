package association.onetomany.set;

import java.util.HashSet;
import java.util.Set;

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
@Entity(name = "OneToMany_Set_One")
public class OneSet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy = "one")
	Set<ManySet> many = new HashSet<ManySet>();

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Set<ManySet> getMany() {
		return many;
	}

	public void setMany(Set<ManySet> many) {
		this.many = many;
	}

}
