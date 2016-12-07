package entity;

import javax.naming.NamingException;

import entity.listener.EntityService;
import entity.listener.SimpleEntity;

import junit.framework.TestCase;
import util.EjbUtil;

public class TestEntityVersion extends TestCase {
    private EntityService getBean() {
	try {
	    return (EntityService) EjbUtil.getBean("EntityServiceImpl/remote");
	} catch (NamingException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public void testVersion() throws NamingException {
	EntityService service = getBean();
	EntityService service2 = getBean();
	SimpleEntity entity = new SimpleEntity();
	entity = service.add(entity);

	Long id = entity.getId();
	entity = service.find(id);

	SimpleEntity entity2 = service2.find(id);

	entity.setField("field");
	service.update(entity);

	try {
	    entity2.setField("field");
	    service.update(entity2);
	    fail();
	} catch (RuntimeException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	    fail();
	}
    }
}