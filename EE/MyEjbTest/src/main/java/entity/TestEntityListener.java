package entity;

import java.util.List;

import javax.naming.NamingException;

import entity.listener.EntityService;
import entity.listener.SimpleEntity;

import junit.framework.TestCase;
import util.EjbUtil;

public class TestEntityListener extends TestCase {
    private String PERSIST = "prePersist, postPersist, ";
    private String UPDATE_NO_CHANGE = "postLoad, ";
    private String UPDATE = "postLoad, preUpdate, postUpdate, ";
    private String DELETE = "postLoad, preRemove, postRemove, ";
    private String LOAD = "postLoad, ";

    private EntityService getBean() {
	try {
	    return (EntityService) EjbUtil.getBean("EntityServiceImpl/remote");
	} catch (NamingException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public void testAdd() throws NamingException {
	EntityService service = getBean();
	SimpleEntity entity = new SimpleEntity();
	SimpleEntity entity2 = service.add(entity);
	assertNull(entity.getId());
	assertNotNull(entity2.getId());
	assertEquals(PERSIST, entity2.getAudit());
    }

    public void testUpdate() throws NamingException {
	EntityService service = getBean();
	SimpleEntity entity = new SimpleEntity();
	entity = service.add(entity);
	entity = service.update(entity);
	assertEquals(UPDATE_NO_CHANGE, entity.getAudit());
	
	entity.setField("field");
	entity = service.update(entity);
	assertEquals(UPDATE, entity.getAudit());
    }
    
    public void testDelete() throws NamingException {
	EntityService service = getBean();
	SimpleEntity entity = new SimpleEntity();
	entity = service.add(entity);
	entity = service.remove(entity);
	assertEquals(DELETE, entity.getAudit());
    }
    
    public void testFind() throws NamingException {
	EntityService service = getBean();
	SimpleEntity entity = new SimpleEntity();
	entity = service.add(entity);
	entity = service.find(entity.getId());
	assertEquals(LOAD, entity.getAudit());
	
	List<SimpleEntity> entities = service.find();
	for (SimpleEntity simpleEntity : entities) {
	    assertEquals(LOAD, simpleEntity.getAudit());
	}
    }
}