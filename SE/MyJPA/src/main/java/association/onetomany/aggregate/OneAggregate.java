package association.onetomany.aggregate;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * OneToMany Set Bidirectional ManyAggregate aggregated by OneAggregate
 */
@Entity(name = "OneToMany_Aggregate_One")
public class OneAggregate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany
	@JoinColumn(name = "one_fk")
	Set<ManyAggregate> many = new HashSet<ManyAggregate>();

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Set<ManyAggregate> getMany() {
		return many;
	}

	public void setMany(Set<ManyAggregate> many) {
		this.many = many;
	}

}
