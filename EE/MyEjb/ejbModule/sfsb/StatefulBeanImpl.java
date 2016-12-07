package sfsb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import slsb.StatelessBeanLocal;

@Stateful
@Remote(StatefulBean.class)
@Local(StatefulBeanLocal.class)
public class StatefulBeanImpl implements StatefulBean, StatefulBeanLocal{
    private int count;
    private String lifecycle;
    @EJB
    private StatelessBeanLocal stateless;

    public void increment() {
	count++;
	stateless.statelessMethod();
    }

    public void decrement() {
	count--;
    }
    
    public int getCount() {
	return count;
    }
    
    public String getLifecycle() {
	return lifecycle;
    }
    
    @PostConstruct
    public void afterCreate() {
	System.out.println("afterCreate");
	lifecycle+="afterCreate, ";
    }

    @PreDestroy
    public void beforeDestroy() {
	System.out.println("beforeDestroy");
	lifecycle+="afterCreate, ";
    }
    
    @PostActivate
    public void afterActivate() {
	System.out.println("activate");
	lifecycle+="activate, ";
    }

    @PrePassivate
    public void beforePassivate() {
	System.out.println("passivate");
	lifecycle+="passivate, ";
    }
    
    @Remove
    public void remove(){
	System.out.println("remove");
	lifecycle+="remove, ";
    }
}
