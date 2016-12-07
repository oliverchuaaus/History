package association.onetoone.sharedpk;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "OneToOne_SharedPK_EntityA")
public class EntityA {
	@Id
	private Long idA;

	@OneToOne
	@PrimaryKeyJoinColumn
	private EntityB entityB;

	public Long getIdA() {
		return idA;
	}

	public void setIdA(Long idA) {
		this.idA = idA;
	}

	public EntityB getEntityB() {
		return entityB;
	}

	public void setEntityB(EntityB entityB) {
		this.entityB = entityB;
	}

}

/*
 * CREATE TABLE "ONETOONE_SHAREDPK_ENTITYA" ( "IDA" NUMBER(19,0) NOT NULL
 * ENABLE, PRIMARY KEY ("IDA") ENABLE )
 */
