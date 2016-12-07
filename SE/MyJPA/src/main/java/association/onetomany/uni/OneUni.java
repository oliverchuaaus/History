package association.onetomany.uni;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * OneToMany Map Unidirectional
 * 
 * JPA 1.0 does not support a unidirectional OneToMany relationship without a
 * JoinTable. JPA 2.0 will have support for a unidirectional OneToMany
 */
@Entity(name = "OneToMany_Uni_One")
public class OneUni {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany
	@JoinColumn(name = "ONE_ID", referencedColumnName = "ID")
	Set<ManyUni> many = new HashSet<ManyUni>();

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Set<ManyUni> getMany() {
		return many;
	}

	public void setMany(Set<ManyUni> many) {
		this.many = many;
	}

}
