package interceptor;

import javax.ejb.Remote;

@Remote
public interface ExcludeInterceptee {
    public String method(String str);

    public String method(String str1, String str2);

}