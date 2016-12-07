package association.onetoone.joincolumn;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * A foreign key is held by one of the entities (note that this FK column in the
 * database should be constrained unique to simulate one-to-one multiplicity).
 * 
 * Sensible default values: A join column(s) will be created in the owner table
 * and its name will be the concatenation of the name of the relationship in the
 * owner side, _ (underscore), and the name of the primary key column(s) in the
 * owned side. i.e. owned_id
 */
@Entity(name = "OneToOne_FK_EntityE")
public class EntityE implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Unique is needed so one-to-one relationship is correctly maintained
	 * default value entityF_id overridden as entityF_fk
	 */
	@OneToOne
	@JoinColumn(name = "entityF_fk", unique = true)
	private EntityF entityF;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntityF getEntityF() {
		return entityF;
	}

	public void setEntityF(EntityF entityF) {
		this.entityF = entityF;
	}
}

/*
 * CREATE TABLE "ONETOONE_FK_ENTITYE" ( "ID" NUMBER(19,0) NOT NULL ENABLE,
 * "ENTITYF_FK" NUMBER(19,0), PRIMARY KEY ("ID") ENABLE, UNIQUE ("ENTITYF_FK")
 * ENABLE, CONSTRAINT "FK202AC62AEAE2B57" FOREIGN KEY ("ENTITYF_FK") REFERENCES
 * "SANDBOX"."ONETOONE_FK_ENTITYF" ("ID") ENABLE )
 */

