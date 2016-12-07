package em;

import javax.naming.NamingException;

import junit.framework.TestCase;
import util.EjbUtil;

import common.MyEntity;

import em.applicationManaged.AppManagedEMStatefulService;
import em.applicationManaged.AppManagedEMStatelessService;
import em.containerManaged.ConManagedEMService;
import em.containerManaged.ConManagedExtendedEMService;

public class TestEm extends TestCase {
    public void testContainerManagedTransactionScopedEM()
	    throws NamingException {
	ConManagedEMService service = (ConManagedEMService) EjbUtil
		.getBean("ConManagedEMServiceImpl/remote");

	MyEntity entity = new MyEntity();
	entity = service.add(entity);

	Long entityId = entity.getId();
	entity = service.find(entityId);
	MyEntity entity2 = service.find(entityId);
	assertFalse(entity == entity2);
	assertTrue(service.find2(entityId));
    }

    public void testContainerManagedExtendedScopedEM() throws NamingException {
	ConManagedExtendedEMService service = (ConManagedExtendedEMService) EjbUtil
		.getBean("ConManagedExtendedEMServiceImpl/remote");
	MyEntity entity = new MyEntity();
	entity = service.add(entity);

	Long entityId = entity.getId();
	String hashCode = service.find(entityId);
	String hashCode2 = service.find(entityId);
	assertTrue(hashCode.equals(hashCode2));
    }

    public void testApplicationManagedExtendedScopedEMStateful()
	    throws NamingException {
	AppManagedEMStatefulService service = (AppManagedEMStatefulService) EjbUtil
		.getBean("AppManagedEMStatefulServiceImpl/remote");

	MyEntity entity = new MyEntity();
	entity = service.add(entity);

	Long entityId = entity.getId();
	String hashCode = service.find(entityId);
	String hashCode2 = service.find(entityId);
	assertTrue(hashCode.equals(hashCode2));
	service.destroy();
    }

    public void testApplicationManagedExtendedScopedEMStateless()
	    throws NamingException {
	AppManagedEMStatelessService service = (AppManagedEMStatelessService) EjbUtil
		.getBean("AppManagedEMStatelessServiceImpl/remote");

	MyEntity entity = new MyEntity();
	entity = service.add(entity);

	Long entityId = entity.getId();
	String hashCode = service.find(entityId);
	String hashCode2 = service.find(entityId);
	assertFalse(hashCode.equals(hashCode2));
	service.destroy();
    }

}
