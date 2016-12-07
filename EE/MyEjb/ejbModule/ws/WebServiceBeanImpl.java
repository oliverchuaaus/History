package ws;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

@Stateless
public class WebServiceBeanImpl implements WebServiceBean {
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
