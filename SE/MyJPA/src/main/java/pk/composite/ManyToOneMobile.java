package pk.composite;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity(name = "Pk_Composite_ManyToOne_Mobile")
public class ManyToOneMobile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long mobile;

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
}
