package entity.association.manytoone.uni;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EntityI implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private EntityJ entityJ;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public EntityJ getEntityJ() {
	return entityJ;
    }

    public void setEntityJ(EntityJ entityJ) {
	this.entityJ = entityJ;
    }

}
