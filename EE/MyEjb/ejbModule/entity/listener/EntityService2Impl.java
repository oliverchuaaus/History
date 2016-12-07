package entity.listener;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import util.JpaUtil;

@Stateless
public class EntityService2Impl implements EntityService2 {

    @PersistenceContext(unitName = "ejbSandbox")
    private EntityManager em;

    /* (non-Javadoc)
     * @see entity.listener.EntityService#add(entity.listener.SimpleEntity2Impl)
     */
    public SimpleEntity2 add(SimpleEntity2 entity) {
	em.persist(entity);
	return entity;
    }

    /* (non-Javadoc)
     * @see entity.listener.EntityService#update(entity.listener.SimpleEntity2Impl)
     */
    public SimpleEntity2 update(SimpleEntity2 entity) {
	entity = em.merge(entity);
	return entity;
    }

    /* (non-Javadoc)
     * @see entity.listener.EntityService#remove(entity.listener.SimpleEntity2Impl)
     */
    public SimpleEntity2 remove(SimpleEntity2 entity) {
	entity = em.merge(entity);
	em.remove(entity);
	return entity;
    }

    /* (non-Javadoc)
     * @see entity.listener.EntityService#find(java.lang.Long)
     */
    public SimpleEntity2 find(Long id) {
	return em.find(SimpleEntity2.class, id);
    }

    /* (non-Javadoc)
     * @see entity.listener.EntityService#find()
     */
    public List<SimpleEntity2> find() {
	return JpaUtil.castList(SimpleEntity2.class, em.createQuery(
		"SELECT ent FROM Listener_SimpleEntity2 ent").getResultList());
    }

}
