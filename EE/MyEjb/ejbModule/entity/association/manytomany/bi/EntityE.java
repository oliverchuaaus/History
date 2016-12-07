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
public class EntityE implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany
    private Set<EntityF> entityF = new HashSet<EntityF>();

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Set<EntityF> getEntityF() {
	return entityF;
    }

    public void setEntityF(Set<EntityF> entityF) {
	this.entityF = entityF;
    }

}
