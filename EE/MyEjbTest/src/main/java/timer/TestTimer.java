package timer;

import javax.naming.NamingException;

import junit.framework.TestCase;
import util.EjbUtil;

public class TestTimer extends TestCase {
    public void testTimer() throws NamingException {
	MyTimerService bean = (MyTimerService) EjbUtil
		.getBean("MyTimerServiceImpl/remote");
	bean.doSomething(10000);
    }
    
    public void testStopTimer() throws NamingException {
	MyTimerService bean = (MyTimerService) EjbUtil
		.getBean("MyTimerServiceImpl/remote");
	bean.stopAll();
    }
}
