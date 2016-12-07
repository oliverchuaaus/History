package pk.composite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * 1 of 2 Annotate the class as @IdClass annotate each property of the entity
 * involved in the primary key with @Id
 * 
 * @author Toffer
 */
@Entity(name = "Pk_Composite_IdClassPK")
@IdClass(IdClassPK.class)
public class IdClassPKEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long mobile;

	@Id
	private Long pin;

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Long getPin() {
		return pin;
	}

	public void setPin(Long pin) {
		this.pin = pin;
	}
}

/*
 * CREATE TABLE "PK_COMPOSITE_IDCLASSPK" ( "MOBILE" NUMBER(19,0) NOT NULL
 * ENABLE, "PIN" NUMBER(19,0) NOT NULL ENABLE, PRIMARY KEY ("MOBILE", "PIN")
 * ENABLE )
 */