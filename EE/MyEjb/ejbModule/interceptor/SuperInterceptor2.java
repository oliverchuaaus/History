package interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class SuperInterceptor2 {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

	String trace = (String) context.getContextData().get("TRACE");
	if (trace == null) {
	    trace = "";
	}
	trace += "super interceptor override 2, ";
	context.getContextData().put("TRACE", trace);

	return context.proceed();
    }
    
    @AroundInvoke
    public Object superIntercept(InvocationContext context) throws Exception {

	String trace = (String) context.getContextData().get("TRACE");
	if (trace == null) {
	    trace = "";
	}
	trace += "super interceptor 2, ";
	context.getContextData().put("TRACE", trace);

	return context.proceed();
    }
}
