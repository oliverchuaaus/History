package slsb;

import javax.ejb.Remote;

@Remote
public interface StatelessBean {
    public String statelessMethod();
}
