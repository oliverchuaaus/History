package interceptor;

import javax.ejb.Remote;

@Remote
public interface Interceptee {

    public abstract String method(String str);

    public abstract String method(String str1, String str2);

    public abstract String method2(String str);

}