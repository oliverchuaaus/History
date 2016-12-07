package association.onetomany.jointable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 * OneToMany Map Bidirectional
 */
@Entity(name = "OneToMany_JoinTable_One")
public class OneJoinTable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany
	@JoinTable(name = "OneToMany_JoinTable_OneMany", joinColumns = { @JoinColumn(name = "ONE_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "MANY_ID", referencedColumnName = "ID") })
	Set<ManyJoinTable> many = new HashSet<ManyJoinTable>();

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Set<ManyJoinTable> getMany() {
		return many;
	}

	public void setMany(Set<ManyJoinTable> many) {
		this.many = many;
	}

}
