package association.manytomany.uni;

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

@Entity(name = "ManyToMany_ManyA_Uni")
public class ManyAUni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToMany
	@JoinTable(name = "ManyToManyUni_ManyA_ManyBUni", joinColumns = @JoinColumn(name = "MANYA_ID"), inverseJoinColumns = @JoinColumn(name = "ManyBUni_ID"))
	private Set<ManyBUni> manyBUniSet = new HashSet<ManyBUni>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<ManyBUni> getManyBUniSet() {
		return manyBUniSet;
	}

	public void setManyBUniSet(Set<ManyBUni> manyBUniSet) {
		this.manyBUniSet = manyBUniSet;
	}

}