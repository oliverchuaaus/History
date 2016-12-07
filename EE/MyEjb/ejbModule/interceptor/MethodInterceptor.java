package interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class MethodInterceptor extends SuperMethodInterceptor{

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

	String trace = (String) context.getContextData().get("TRACE");
	if (trace == null) {
	    trace = "";
	}
	trace += "method interceptor, ";
	context.getContextData().put("TRACE", trace);

	return context.proceed();
    }
}
