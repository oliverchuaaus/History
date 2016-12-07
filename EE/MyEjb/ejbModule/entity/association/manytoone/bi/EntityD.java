package entity.association.manytoone.bi;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class EntityD implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "entityD")
    private Set<EntityC> entityC;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Set<EntityC> getEntityC() {
	return entityC;
    }

    public void setEntityC(Set<EntityC> entityC) {
	this.entityC = entityC;
    }
}
