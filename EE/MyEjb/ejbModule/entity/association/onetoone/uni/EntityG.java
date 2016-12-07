package entity.association.onetoone.uni;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class EntityG implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private EntityH entityH;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public EntityH getEntityH() {
	return entityH;
    }

    public void setEntityH(EntityH entityH) {
	this.entityH = entityH;
    }

}
