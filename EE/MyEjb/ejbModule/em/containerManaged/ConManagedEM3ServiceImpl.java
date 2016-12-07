package em.containerManaged;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import util.JpaUtil;

import common.MyEntity;

@Stateless
public class ConManagedEM3ServiceImpl implements ConManagedEM3Service {
    @PersistenceContext(unitName = "ejbSandbox")
    private EntityManager em;

    public MyEntity find(Long id) {
	MyEntity entity = em.find(MyEntity.class, id);
	return entity;
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
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void requiresNew() {
	MyEntity entity = new MyEntity();
	entity.setStringField("Requires New");
	em.persist(entity);
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void never() {
	MyEntity entity = new MyEntity();
	entity.setStringField("Never");
	em.persist(entity);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void notSupported() {
	MyEntity entity = new MyEntity();
	entity.setStringField("Not Supported");
	em.persist(entity);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void supports() {
	MyEntity entity = new MyEntity();
	entity.setStringField("Supports");
	em.persist(entity);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void mandatory() {
	MyEntity entity = new MyEntity();
	entity.setStringField("Mandatory");
	em.persist(entity);
    }

}
