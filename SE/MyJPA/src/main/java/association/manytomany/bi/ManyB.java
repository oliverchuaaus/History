package association.manytomany.bi;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "ManyToMany_ManyB")
public class ManyB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToMany(mappedBy = "manyBSet")
	private Set<ManyA> manyASet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<ManyA> getManyASet() {
		return manyASet;
	}

	public void setManyASet(Set<ManyA> manyASet) {
		this.manyASet = manyASet;
	}

}
