package resource;

import javax.naming.NamingException;

import common.MyEntity;

import junit.framework.TestCase;
import resource.bmt.ResourceBMTService;
import resource.cmt.ResourceCMTService;
import util.EjbUtil;

public class TestResource extends TestCase {
    public void testResourceCMT() throws NamingException {
	ResourceCMTService service = (ResourceCMTService) EjbUtil
		.getBean("ResourceCMTServiceImpl/remote");

	MyEntity entity = new MyEntity();
	entity.setId(1000L);
	entity.setStringField("Hola");
	service.clear();
	service.add(entity);
	entity = service.find();
	assertEquals(1000L, entity.getId().longValue());
	assertEquals("Hola", entity.getStringField());
    }
    
    public void testResourceCMTRollback() throws NamingException {
	ResourceCMTService service = (ResourceCMTService) EjbUtil
		.getBean("ResourceCMTServiceImpl/remote");

	MyEntity entity = new MyEntity();
	entity.setId(1000L);
	entity.setStringField("Hola");
	service.clear();
	service.addRollback(entity);
	entity = service.find();
	assertNull(entity);
    }

    public void testResourceBMT() throws NamingException {
	ResourceBMTService service = (ResourceBMTService) EjbUtil
		.getBean("ResourceBMTServiceImpl/remote");
	MyEntity entity = new MyEntity();
	entity.setId(1000L);
	entity.setStringField("Hola");
	service.clear();
	service.add(entity);
	entity = service.find();
	assertEquals(1000L, entity.getId().longValue());
	assertEquals("Hola", entity.getStringField());
    }
}
