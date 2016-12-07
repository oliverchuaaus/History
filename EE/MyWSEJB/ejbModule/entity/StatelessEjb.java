package entity;

import javax.jws.WebMethod;

public interface StatelessEjb {

    @WebMethod
    public abstract String echo(String input);

}