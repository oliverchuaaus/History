package entity.association.onetoone.bi;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class EntityB implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "entityB")
    private EntityA entityA;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public EntityA getEntityA() {
	return entityA;
    }

    public void setEntityA(EntityA entityA) {
	this.entityA = entityA;
    }
}
