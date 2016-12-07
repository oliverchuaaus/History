package individual.embedabble;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Use @Embedabble in object and @Embedded (optional) in entity.
 * 
 * Use @AttributeOverrides({@AttributeOverride}) to avoid
 * field name conflict.
 * 
 * @author Toffer
 */
@Entity(name = "Individual_Embedabble_Entity")
public class EntityEmbedabble implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Use defaults as long as there is no field name conflict.
	 */
	private EmbedabbleObject1 object1;

	/**
	 * Override to avoid collision/repetition of fields.
	 */
	@AttributeOverrides({ @AttributeOverride(name = "field", column = @Column(name = "Object2AField")) })
	private EmbedabbleObject2 object2A;

	@AttributeOverrides({ @AttributeOverride(name = "field", column = @Column(name = "Object2BField")) })
	private EmbedabbleObject2 object2B;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EmbedabbleObject1 getObject1() {
		return object1;
	}

	public void setObject1(EmbedabbleObject1 object1) {
		this.object1 = object1;
	}

	public EmbedabbleObject2 getObject2A() {
		return object2A;
	}

	public void setObject2A(EmbedabbleObject2 object2a) {
		object2A = object2a;
	}

	public EmbedabbleObject2 getObject2B() {
		return object2B;
	}

	public void setObject2B(EmbedabbleObject2 object2b) {
		object2B = object2b;
	}
}

/*
 * CREATE TABLE "INDIVIDUALEMBEDABBLEENTITY1" ( "ID" NUMBER(19,0) NOT NULL
 * ENABLE, "FIELD" VARCHAR2(255 CHAR), "OBJECT2AFIELD" VARCHAR2(255 CHAR),
 * "OBJECT2BFIELD" VARCHAR2(255 CHAR), PRIMARY KEY ("ID") ENABLE )
 */
