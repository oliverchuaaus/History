package sfsb;

import javax.naming.NamingException;

import junit.framework.TestCase;
import sfsb.StatefulBean;
import util.EjbUtil;

public class TestStateful extends TestCase {
    public void testStateful() throws NamingException {
	StatefulBean bean = (StatefulBean) EjbUtil
		.getBean("StatefulBeanImpl/remote");
	bean.increment();
	assertEquals(1, bean.getCount());
	bean.remove();

	StatefulBean bean2 = (StatefulBean) EjbUtil
		.getBean("StatefulBeanImpl/remote");
	assertEquals(0, bean2.getCount());
	bean2.remove();
    }

    public void testLifecycle() throws NamingException {
	//Fails at 10000
	StatefulBean beans[] = new StatefulBean[10];
	for (int i = 0; i < 10; i++) {
	    System.out.println(i);
	    beans[i] = (StatefulBean) EjbUtil
		    .getBean("StatefulBeanImpl/remote");
	    //assertEquals("afterCreate, ", beans[i].getLifecycle());
	}
    }

    public void testBadDeleted() {
	try {
	    StatefulBean bean = (StatefulBean) EjbUtil
		    .getBean("StatefulBeanImpl/remote");
	    bean.remove();
	    bean.increment();
	    fail();
	} catch (RuntimeException e) {
	    e.printStackTrace();
	    assertTrue(e.getMessage().indexOf("Could not find stateful bean") != -1);
	} catch (Exception e) {
	    fail();
	}
    }

    public void testBadLocal() {
	try {
	    EjbUtil.getBean("StatefulBeanImpl/local");
	    fail();
	} catch (NamingException e) {
	    e.printStackTrace();
	    assertTrue(e.getMessage().indexOf("Could not dereference object") != -1);
	} catch (Exception e) {
	    fail();
	}
    }
}
