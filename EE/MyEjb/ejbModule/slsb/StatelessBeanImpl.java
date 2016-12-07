package slsb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

@Stateless
public class StatelessBeanImpl implements StatelessBean, StatelessBeanLocal {
    public String statelessMethod() {
	return "statelessMethod";
    }

    @PostConstruct
    public void afterCreate() {
	
    }

    @PreDestroy
    public void beforeDestroy() {

    }

}
