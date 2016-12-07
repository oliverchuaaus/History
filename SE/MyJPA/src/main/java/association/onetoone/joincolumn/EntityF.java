package association.onetoone.joincolumn;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "OneToOne_FK_EntityF")
public class EntityF implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/*
	 * mappedBy indicates that this entity is not responsible for persisting the
	 * link. i.e. it is already being handled by entityF through the
	 * getEntityF() method.
	 */
	@OneToOne(mappedBy = "entityF")
	private EntityE entityE;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntityE getEntityE() {
		return entityE;
	}

	public void setEntityE(EntityE entityE) {
		this.entityE = entityE;
	}
}

/*
 * CREATE TABLE "SANDBOX"."ONETOONE_FK_ENTITYF" ( "ID" NUMBER(19,0) NOT NULL
 * ENABLE, PRIMARY KEY ("ID") ENABLE )
 */