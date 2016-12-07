package circular.good;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SafeBeanB {

	private final SafeBeanA beanA;

	@Autowired
	public SafeBeanB(SafeBeanA beanA) {
		this.beanA = beanA;
	}

	@Override
	public String toString() {
		return beanA.getClass().getSimpleName() + " from "
				+ this.getClass().getSimpleName();
	}

}