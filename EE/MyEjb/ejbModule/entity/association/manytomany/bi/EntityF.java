package entity.association.manytomany.bi;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class EntityF implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(mappedBy = "entityF")
    private Set<EntityE> entityE = new HashSet<EntityE>();

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Set<EntityE> getEntityE() {
	return entityE;
    }

    public void setEntityE(Set<EntityE> entityE) {
	this.entityE = entityE;
    }
}
