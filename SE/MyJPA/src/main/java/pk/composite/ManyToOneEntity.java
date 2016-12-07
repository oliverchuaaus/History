package pk.composite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * ManyToOne where the referenced object is part of the PK
 * @author Toffer
 */
@Entity(name = "Pk_Composite_ManyToOne")
@IdClass(IdClassPK.class)
public class ManyToOneEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "MOBILE", referencedColumnName = "MOBILE")
	private ManyToOneMobile mobile;

	@Id
	private Long pin;

	public Long getPin() {
		return pin;
	}

	public void setPin(Long pin) {
		this.pin = pin;
	}

	public ManyToOneMobile getMobile() {
		return mobile;
	}

	public void setMobile(ManyToOneMobile mobile) {
		this.mobile = mobile;
	}
}
