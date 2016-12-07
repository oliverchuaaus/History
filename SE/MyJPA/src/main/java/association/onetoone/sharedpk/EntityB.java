package association.onetoone.sharedpk;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "OneToOne_SharedPK_EntityB")
public class EntityB {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idB;
	@OneToOne(mappedBy = "entityB")
	private EntityA entityA;

	public EntityA getEntityA() {
		return entityA;
	}

	public void setEntityA(EntityA entityA) {
		this.entityA = entityA;
	}

	public Long getIdB() {
		return idB;
	}

	public void setIdB(Long idB) {
		this.idB = idB;
	}
}

/*
 * CREATE TABLE "SANDBOX"."ONETOONE_SHAREDPK_ENTITYB" ( "IDB" NUMBER(19,0) NOT
 * NULL ENABLE, PRIMARY KEY ("IDB") ENABLE )
 */
