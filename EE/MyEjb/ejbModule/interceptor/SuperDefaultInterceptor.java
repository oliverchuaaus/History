package interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class SuperDefaultInterceptor {

    public Object intercept(InvocationContext context) throws Exception {

	String trace = (String) context.getContextData().get("TRACE");
	if (trace == null) {
	    trace = "";
	}
	trace += "super default override, ";
	context.getContextData().put("TRACE", trace);

	return context.proceed();
    }
    
    @AroundInvoke
    public Object superIntercept(InvocationContext context) throws Exception {

	String trace = (String) context.getContextData().get("TRACE");
	if (trace == null) {
	    trace = "";
	}
	trace += "super default, ";
	context.getContextData().put("TRACE", trace);

	return context.proceed();
    }
}
