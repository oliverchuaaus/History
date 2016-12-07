package entity.association.onetoone.bi;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class EntityA implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private EntityB entityB;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public EntityB getEntityB() {
	return entityB;
    }

    public void setEntityB(EntityB entityB) {
	this.entityB = entityB;
    }

}
