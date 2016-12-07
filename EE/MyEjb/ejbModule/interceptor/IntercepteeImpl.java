package interceptor;

import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;

@Stateless
@Interceptors( { Interceptor.class, Interceptor2.class })
public class IntercepteeImpl extends SuperInterceptee implements Interceptee {

    /* (non-Javadoc)
     * @see interceptor.Interceptee#method(java.lang.String)
     */
    @Interceptors( { MethodInterceptor.class, MethodInterceptor2.class })
    public String method(String str) {
	return str;
    }

    /* (non-Javadoc)
     * @see interceptor.Interceptee#method()
     */
    public String method(String str1, String str2) {
	return str1;
    }

    /* (non-Javadoc)
     * @see interceptor.Interceptee#method2()
     */
    public String method2(String str) {
	return str;
    }

    /* (non-Javadoc)
     * @see interceptor.Interceptee#intercept(javax.interceptor.InvocationContext)
     */
    @AroundInvoke
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
