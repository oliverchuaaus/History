package entity;

import java.util.List;

import javax.naming.NamingException;

import entity.listener.EntityService2;
import entity.listener.SimpleEntity2;

import junit.framework.TestCase;
import util.EjbUtil;

public class TestEntityListenerExternal extends TestCase {
    private String PERSIST = "prePersist, postPersist, ";
    private String UPDATE_NO_CHANGE = "postLoad, ";
    private String UPDATE = "postLoad, preUpdate, postUpdate, ";
    private String DELETE = "postLoad, preRemove, postRemove, ";
    private String LOAD = "postLoad, ";

    private EntityService2 getBean() {
	try {
	    return (EntityService2) EjbUtil.getBean("EntityService2Impl/remote");
	} catch (NamingException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public void testAdd() throws NamingException {
	EntityService2 service = getBean();
	SimpleEntity2 entity = new SimpleEntity2();
	SimpleEntity2 entity2 = service.add(entity);
	assertNull(entity.getId());
	assertNotNull(entity2.getId());
	assertEquals(PERSIST, entity2.getAudit());
    }

    public void testUpdate() throws NamingException {
	EntityService2 service = getBean();
	SimpleEntity2 entity = new SimpleEntity2();
	entity = service.add(entity);
	entity = service.update(entity);
	assertEquals(UPDATE_NO_CHANGE, entity.getAudit());
	
	entity.setField("field");
	entity = service.update(entity);
	assertEquals(UPDATE, entity.getAudit());
    }
    
    public void testDelete() throws NamingException {
	EntityService2 service = getBean();
	SimpleEntity2 entity = new SimpleEntity2();
	entity = service.add(entity);
	entity = service.remove(entity);
	assertEquals(DELETE, entity.getAudit());
    }
    
    public void testFind() throws NamingException {
	EntityService2 service = getBean();
	SimpleEntity2 entity = new SimpleEntity2();
	entity = service.add(entity);
	entity = service.find(entity.getId());
	assertEquals(LOAD, entity.getAudit());
	
	List<SimpleEntity2> entities = service.find();
	for (SimpleEntity2 simpleEntity : entities) {
	    assertEquals(LOAD, simpleEntity.getAudit());
	}
    }
}