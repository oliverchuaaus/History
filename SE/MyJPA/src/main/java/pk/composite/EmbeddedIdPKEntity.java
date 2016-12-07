package pk.composite;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * 2 of 2 Annotate the component property as @EmbeddedId
 * 
 * @author Toffer
 */
@Entity(name = "Pk_Composite_EmbeddedIdPK")
public class EmbeddedIdPKEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmbeddedIdPK id;

	public EmbeddedIdPK getId() {
		return id;
	}

	public void setId(EmbeddedIdPK id) {
		this.id = id;
	}
}

/*
 * CREATE TABLE "PK_COMPOSITE_EMBEDDEDIDPK" ( "MOBILE" NUMBER(19,0) NOT NULL
 * ENABLE, "PIN" NUMBER(19,0) NOT NULL ENABLE, PRIMARY KEY ("MOBILE", "PIN")
 * ENABLE )
 */