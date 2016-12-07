package entity.association.manytoone.bi;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EntityC implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private EntityD entityD;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public EntityD getEntityD() {
	return entityD;
    }

    public void setEntityD(EntityD entityD) {
	this.entityD = entityD;
    }

}
