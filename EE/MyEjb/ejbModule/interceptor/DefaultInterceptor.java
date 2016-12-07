package interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class DefaultInterceptor extends SuperDefaultInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

	String trace = (String) context.getContextData().get("TRACE");
	if (trace == null) {
	    trace = "";
	}
	trace += "default, ";
	context.getContextData().put("TRACE", trace);

	return context.proceed();
    }
}
