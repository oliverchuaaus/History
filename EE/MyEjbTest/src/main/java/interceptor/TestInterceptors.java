package interceptor;

import javax.naming.NamingException;

import junit.framework.TestCase;
import util.EjbUtil;

public class TestInterceptors extends TestCase {
    private Interceptee getBean() {
	try {
	    return (Interceptee) EjbUtil.getBean("IntercepteeImpl/remote");
	} catch (NamingException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    private ExcludeInterceptee getExcludeBean() {
	try {
	    return (ExcludeInterceptee) EjbUtil
		    .getBean("ExcludeIntercepteeImpl/remote");
	} catch (NamingException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public void testInterceptor() throws NamingException {
	Interceptee bean = getBean();
	String expected = "super default, default, super default 2, default 2, super interceptor, interceptor, super interceptor 2, interceptor 2, super method interceptor, method interceptor, super method interceptor 2, method interceptor 2, super bean, bean, ";
	assertEquals(expected, bean.method("something"));
    }

    public void testMethod2() throws NamingException {
	Interceptee bean = getBean();
	String expected = "super default, default, super default 2, default 2, super interceptor, interceptor, super interceptor 2, interceptor 2, super bean, bean, ";
	assertEquals(expected, bean.method2("something"));
    }

    public void testMethod() throws NamingException {
	Interceptee bean = getBean();
	String expected = "super default, default, super default 2, default 2, super interceptor, interceptor, super interceptor 2, interceptor 2, super bean, bean, ";
	assertEquals(expected, bean.method("something", "something"));
    }
    
    public void testExcludeInterceptor() throws NamingException {
	ExcludeInterceptee bean = getExcludeBean();
	String expected = "bean, ";
	assertEquals(expected, bean.method("something"));
    }

    public void testExcludeMethod() throws NamingException {
	ExcludeInterceptee bean = getExcludeBean();
	String expected = "bean, ";
	assertEquals(expected, bean.method("something", "something"));
    }
}