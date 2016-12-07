package circular.good;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.inject.Provider;

@Component
public class SafeBeanA {

	private final Provider<SafeBeanB> beanB;

	@Autowired
	public SafeBeanA(Provider<SafeBeanB> beanB) {
		this.beanB = beanB;
	}

	@Override
	public String toString() {
		return beanB.getClass().getSimpleName() + " from "
				+ this.getClass().getSimpleName();
	}

}
