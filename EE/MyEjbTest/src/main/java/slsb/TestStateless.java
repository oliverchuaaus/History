package slsb;

import javax.naming.NamingException;

import junit.framework.TestCase;
import util.EjbUtil;

public class TestStateless extends TestCase {
    public void testStateless() throws NamingException {
	StatelessBean bean = (StatelessBean) EjbUtil
		.getBean("StatelessBeanImpl/remote");
	assertEquals("statelessMethod", bean.statelessMethod());
    }
}
