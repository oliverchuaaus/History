package association.manytomany.bi;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "ManyToMany_ManyA")
public class ManyA implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToMany
	@JoinTable(name = "ManyToMany_ManyA_ManyB", joinColumns = @JoinColumn(name = "MANYA_ID"), inverseJoinColumns = @JoinColumn(name = "MANYB_ID"))
	private Set<ManyB> manyBSet = new HashSet<ManyB>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<ManyB> getManyBSet() {
		return manyBSet;
	}

	public void setManyBSet(Set<ManyB> manyBSet) {
		this.manyBSet = manyBSet;
	}

}