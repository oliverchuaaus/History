package em.containerManaged;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import common.MyEntity;

@Stateful
public class ConManagedExtendedEMServiceImpl implements
	ConManagedExtendedEMService {
    @PersistenceContext(unitName = "ejbSandbox", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public MyEntity add(MyEntity entity) {
	em.persist(entity);
	return entity;
    }

    public String find(Long id) {
	MyEntity entity = em.find(MyEntity.class, id);
	System.out.println(entity.toString());
	return entity.toString();
    }

}
