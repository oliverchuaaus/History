package pk.simple;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Sequence PK.
 * 
 * @author Toffer
 */
@Entity(name = "Pk_Simple_SequencePK")
@SequenceGenerator(name = "ENTITY_SEQ_STORE", sequenceName = "ENTITY_SEQ", allocationSize = 50)
public class EntitySequencePK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENTITY_SEQ_STORE")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

/*
 * CREATE TABLE PK_SIMPLE_SEQUENCEPK" ( "ID" NUMBER(19,0) NOT NULL ENABLE,
 * PRIMARY KEY ("ID") ENABLE )
 */