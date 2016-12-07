package entity.association.onetomany.uni;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class EntityK implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private Set<EntityL> entityL = new HashSet<EntityL>();

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Set<EntityL> getEntityL() {
	return entityL;
    }

    public void setEntityL(Set<EntityL> entityL) {
	this.entityL = entityL;
    }
}
