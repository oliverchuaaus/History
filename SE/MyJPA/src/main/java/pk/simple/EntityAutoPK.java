package pk.simple;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Auto PK. For Oracle, Sequence named Hibernate_Sequence is created and shared
 * among all tables.
 * 
 * @author Toffer
 */
@Entity(name = "Pk_Simple_AutoPK")
public class EntityAutoPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
/*
 * CREATE TABLE PK_SIMPLE_AUTOPK" ( "ID" NUMBER(19,0) NOT NULL ENABLE, PRIMARY
 * KEY ("ID") ENABLE )
 */