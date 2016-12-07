package pk.simple;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Manual PK.
 * 
 * @author Toffer
 */
@Entity(name = "Pk_Simple_ManualPK")
public class EntityManualPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

/*
 * CREATE TABLE PK_SIMPLE_MANUALPK" ( "ID" NUMBER(19,0) NOT NULL ENABLE, PRIMARY
 * KEY ("ID") ENABLE )
 */