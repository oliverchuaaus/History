package em.containerManaged;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import util.JpaUtil;

import common.MyEntity;

@Stateless
public class ConManagedEMServiceImpl implements ConManagedEMService {
    @PersistenceContext(unitName = "ejbSandbox")
    private EntityManager em;
    @EJB
    private ConManagedEM2Service service;

    public MyEntity add(MyEntity entity) {
	em.persist(entity);
	return entity;
    }

    public MyEntity find(Long id) {
	MyEntity entity = em.find(MyEntity.class, id);
	System.out.println(entity.toString());
	return entity;
    }

    public boolean find2(Long id) {
	MyEntity entity = em.find(MyEntity.class, id);
	MyEntity entity2 = service.find(id);
	//Will only work if Local, but not Remote
	return entity == entity2;
    }

    public void clearAll() {
	Query q = em.createQuery("DELETE FROM MyEntity");
	q.executeUpdate();
    }

    public List<MyEntity> findAll() {
	Query q = em.createQuery("SELECT e FROM MyEntity e ORDER BY e.id");
	return JpaUtil.castList(MyEntity.class, q.getResultList());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void required() {
	MyEntity entity = new MyEntity();
	entity.setStringField("Required");
	em.persist(entity);
	try {
	    service.required();
	} catch (SimulatedException e) {
	}
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void requiresNew() {
	MyEntity entity = new MyEntity();
	entity.setStringField("Requires New");
	em.persist(entity);
	try {
	    service.requiresNew();
	} catch (SimulatedException e) {
	}
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void never() {
	MyEntity entity = new MyEntity();
	entity.setStringField("Never");
	em.persist(entity);
	try {
	    service.never();
	} catch (SimulatedException e) {
	}
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void notSupported() {
	MyEntity entity = new MyEntity();
	entity.setStringField("Not Supported");
	em.persist(entity);
	try {
	    service.notSupported();
	} catch (SimulatedException e) {
	}
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void supports() {
	MyEntity entity = new MyEntity();
	entity.setStringField("Supports");
	em.persist(entity);
	try {
	    service.supports();
	} catch (SimulatedException e) {
	}
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void mandatory() {
	MyEntity entity = new MyEntity();
	entity.setStringField("Mandatory");
	em.persist(entity);
	try {
	    service.mandatory();
	} catch (SimulatedException e) {
	}
    }

}
