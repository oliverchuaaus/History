package pk.composite;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EmbeddedIdPK implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long mobile;
	private Long pin;

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setPin(Long pin) {
		this.pin = pin;
	}

	public Long getPin() {
		return pin;
	}

	// Hibernate uses EmbeddedId class as a cache key to track an object's
	// identity, so we need to implement equals and hashCode.
	@Override
	public boolean equals(Object arg0) {
		EmbeddedIdPK pk = (EmbeddedIdPK) arg0;
		return pk.mobile == mobile && pk.pin == pin;
	}

	@Override
	public int hashCode() {
		return (int) (mobile + pin);
	}
}
