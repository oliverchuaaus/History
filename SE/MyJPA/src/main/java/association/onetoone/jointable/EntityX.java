package association.onetoone.jointable;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

/**
 * An association table is used to store the link between the 2 entities (a
 * unique constraint has to be defined on each fk to ensure the one to one
 * multiplicity)
 */
@Entity(name = "OneToOne_JoinTable_EntityX")
public class EntityX implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Specify table name and column names where joinColumns = this entity's pk
	 * field and inverseJoinColumns = other entity's pk field
	 */
	@OneToOne
	@JoinTable(name = "OneToOne_JoinTable_XY", joinColumns = @JoinColumn(name = "x_fk"), inverseJoinColumns = @JoinColumn(name = "y_fk"))
	private EntityY entityY;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntityY getEntityY() {
		return entityY;
	}

	public void setEntityY(EntityY entityY) {
		this.entityY = entityY;
	}
}

/*
 * CREATE TABLE "SANDBOX"."ONETOONE_JOINTABLE_ENTITYX" ( "ID" NUMBER(19,0) NOT
 * NULL ENABLE, PRIMARY KEY ("ID") ENABLE )
 * 
 * CREATE TABLE "SANDBOX"."ONETOONE_JOINTABLE_XY" ( "Y_FK" NUMBER(19,0), "X_FK"
 * NUMBER(19,0) NOT NULL ENABLE, PRIMARY KEY ("X_FK") ENABLE, CONSTRAINT
 * "FK5E6CBF3694EA933B" FOREIGN KEY ("Y_FK") REFERENCES
 * "SANDBOX"."ONETOONE_JOINTABLE_ENTITYY" ("ID") ENABLE, CONSTRAINT
 * "FK5E6CBF3694EA1EDB" FOREIGN KEY ("X_FK") REFERENCES
 * "SANDBOX"."ONETOONE_JOINTABLE_ENTITYX" ("ID") ENABLE )
 */

