package association.onetoone.jointable;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "OneToOne_JoinTable_EntityY")
public class EntityY implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/*
	 * This will function as a composition. EntityY cannot stand by itself
	 * (create EntityY with null EntityX) but EntityX can.
	 */
	@OneToOne(mappedBy = "entityY",optional=false)
	private EntityX entityX;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntityX getEntityX() {
		return entityX;
	}

	public void setEntityX(EntityX entityX) {
		this.entityX = entityX;
	}
}

/*
 * CREATE TABLE "SANDBOX"."ONETOONE_JOINTABLE_ENTITYY" ( "ID" NUMBER(19,0) NOT
 * NULL ENABLE, PRIMARY KEY ("ID") ENABLE )
 */