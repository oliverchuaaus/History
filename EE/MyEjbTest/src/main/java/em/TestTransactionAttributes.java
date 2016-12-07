package em;

import java.util.List;

import javax.naming.NamingException;

import junit.framework.TestCase;
import util.EjbUtil;

import common.MyEntity;

import em.containerManaged.ConManagedEM3Service;
import em.containerManaged.ConManagedEMService;

public class TestTransactionAttributes extends TestCase {
    public void testRequired() throws NamingException {
	ConManagedEMService service = (ConManagedEMService) EjbUtil
		.getBean("ConManagedEMServiceImpl/remote");

	service.clearAll();
	service.required();
	List<MyEntity> list = service.findAll();
	assertEquals(0, list.size());
    }

    public void testRequiresNew() throws NamingException {
	ConManagedEMService service = (ConManagedEMService) EjbUtil
		.getBean("ConManagedEMServiceImpl/remote");

	service.clearAll();
	service.requiresNew();
	List<MyEntity> list = service.findAll();
	assertEquals(1, list.size());
	for (MyEntity myEntity : list) {
	    assertEquals("Requires New", myEntity.getStringField());
	}
    }

    public void testNever() throws NamingException {
	ConManagedEMService service = (ConManagedEMService) EjbUtil
		.getBean("ConManagedEMServiceImpl/remote");

	service.clearAll();
	try {
	    service.never();
	} catch (Exception e) {
	    assertTrue(e.getMessage().indexOf(
		    "Transaction present on server in Never call") != -1);
	}
    }

    public void testNotSupported() throws NamingException {
	ConManagedEMService service = (ConManagedEMService) EjbUtil
		.getBean("ConManagedEMServiceImpl/remote");

	service.clearAll();
	try {
	    service.notSupported();
	} catch (Exception e) {
	    assertTrue(e.getMessage().indexOf(
		    "EntityManager must be access within a transaction") != -1);

	}
    }

    public void testSupports() throws NamingException {
	ConManagedEMService service = (ConManagedEMService) EjbUtil
		.getBean("ConManagedEMServiceImpl/remote");

	service.clearAll();
	service.supports();
	List<MyEntity> list = service.findAll();
	assertEquals(0, list.size());
    }

    public void testMandatory() throws NamingException {
	ConManagedEMService service = (ConManagedEMService) EjbUtil
		.getBean("ConManagedEMServiceImpl/remote");

	service.clearAll();
	service.mandatory();
	List<MyEntity> list = service.findAll();
	assertEquals(0, list.size());
    }
    
    public void testRequiredNoTxn() throws NamingException {
	ConManagedEM3Service service = (ConManagedEM3Service) EjbUtil
		.getBean("ConManagedEM3ServiceImpl/remote");
	service.clearAll();
	service.required();
	List<MyEntity> list = service.findAll();
	assertEquals(1, list.size());
	for (MyEntity myEntity : list) {
	    assertEquals("Required", myEntity.getStringField());
	}
    }

    public void testRequiresNewNoTxn() throws NamingException {
	ConManagedEM3Service service = (ConManagedEM3Service) EjbUtil
		.getBean("ConManagedEM3ServiceImpl/remote");

	service.clearAll();
	service.requiresNew();
	List<MyEntity> list = service.findAll();
	assertEquals(1, list.size());
	for (MyEntity myEntity : list) {
	    assertEquals("Requires New", myEntity.getStringField());
	}
    }

    public void testNeverNoTxn() throws NamingException {
	ConManagedEM3Service service = (ConManagedEM3Service) EjbUtil
		.getBean("ConManagedEM3ServiceImpl/remote");

	service.clearAll();
	try {
	    service.never();
	} catch (Exception e) {
	    assertTrue(e.getMessage().indexOf(
		    "EntityManager must be access within a transaction") != -1);
	}
    }

    public void testNotSupportedNoTxn() throws NamingException {
	ConManagedEM3Service service = (ConManagedEM3Service) EjbUtil
		.getBean("ConManagedEM3ServiceImpl/remote");

	service.clearAll();
	try {
	    service.notSupported();
	} catch (Exception e) {
	    assertTrue(e.getMessage().indexOf(
		    "EntityManager must be access within a transaction") != -1);

	}
    }

    public void testSupportsNoTxn() throws NamingException {
	ConManagedEM3Service service = (ConManagedEM3Service) EjbUtil
		.getBean("ConManagedEM3ServiceImpl/remote");

	service.clearAll();
	try {
	    service.supports();
	} catch (Exception e) {
	    assertTrue(e.getMessage().indexOf(
		    "EntityManager must be access within a transaction") != -1);

	}
    }

    public void testMandatoryNoTxn() throws NamingException {
	ConManagedEM3Service service = (ConManagedEM3Service) EjbUtil
		.getBean("ConManagedEM3ServiceImpl/remote");

	service.clearAll();
	try {
	    service.mandatory();
	} catch (Exception e) {
	    assertTrue(e.getMessage().indexOf(
		    "public void em.containerManaged.ConManagedEM3ServiceImpl.mandatory()") != -1);

	}
    }
}
