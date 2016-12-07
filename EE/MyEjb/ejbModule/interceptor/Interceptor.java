package interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Interceptor extends SuperInterceptor{

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

	String trace = (String) context.getContextData().get("TRACE");
	if (trace == null) {
	    trace = "";
	}
	trace += "interceptor, ";
	context.getContextData().put("TRACE", trace);

	return context.proceed();
    }
}
