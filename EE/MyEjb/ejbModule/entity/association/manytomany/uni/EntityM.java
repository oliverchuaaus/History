package entity.association.manytomany.uni;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class EntityM implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany
    private Set<EntityN> entityN = new HashSet<EntityN>();

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Set<EntityN> getEntityN() {
	return entityN;
    }

    public void setEntityN(Set<EntityN> entityN) {
	this.entityN = entityN;
    }
}
