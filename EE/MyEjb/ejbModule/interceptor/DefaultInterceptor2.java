package interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class DefaultInterceptor2 extends SuperDefaultInterceptor2 {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

	String trace = (String) context.getContextData().get("TRACE");
	if (trace == null) {
	    trace = "";
	}
	trace += "default 2, ";
	context.getContextData().put("TRACE", trace);

	return context.proceed();
    }
}
