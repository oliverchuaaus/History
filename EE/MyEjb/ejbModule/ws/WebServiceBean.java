package ws;

import javax.ejb.Remote;

@Remote
public interface WebServiceBean {
    public String statelessMethod();
}
