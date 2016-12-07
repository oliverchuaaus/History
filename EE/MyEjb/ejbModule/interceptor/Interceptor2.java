package interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Interceptor2 extends SuperInterceptor2{

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

	String trace = (String) context.getContextData().get("TRACE");
	if (trace == null) {
	    trace = "";
	}
	trace += "interceptor 2, ";
	context.getContextData().put("TRACE", trace);

	return context.proceed();
    }
}
