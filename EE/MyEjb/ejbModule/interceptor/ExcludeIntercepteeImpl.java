package interceptor;

import javax.ejb.Stateless;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;

@Stateless
@ExcludeDefaultInterceptors
@Interceptors( { Interceptor.class, Interceptor2.class })
public class ExcludeIntercepteeImpl implements ExcludeInterceptee {

    @ExcludeDefaultInterceptors
    @ExcludeClassInterceptors
    public String method(String str) {
	return str;
    }

    public String method(String str1, String str2) {
	return str1;
    }

    public Object intercept(InvocationContext context) throws Exception {

	String trace = (String) context.getContextData().get("TRACE");
	if (trace == null) {
	    trace = "";
	}
	trace += "bean, ";
	context.getContextData().put("TRACE", trace);
	// last one in the chain
	System.out.println("trace:" + trace);

	Object[] params = context.getParameters();
	if (params.length > 0) {
	    params[0] = trace;
	    context.setParameters(params);
	}
	return context.proceed();
    }
}
