package em.applicationManaged;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import common.MyEntity;

@Stateless
public class AppManagedEMStatelessServiceImpl implements
	AppManagedEMStatelessService {
    @PersistenceUnit(unitName = "ejbSandbox")
    private EntityManagerFactory emf;
    private EntityManager em;

    public String find(Long id) {
	MyEntity entity = em.find(MyEntity.class, id);
	return entity.toString();
    }

    public MyEntity add(MyEntity entity) {
	em.persist(entity);
	return entity;
    }

    @PostConstruct
    public void init() {
	em = emf.createEntityManager();
    }

    @PreDestroy
    public void destroy() {
	if (em.isOpen()) {
	    em.close();
	}
    }

}
