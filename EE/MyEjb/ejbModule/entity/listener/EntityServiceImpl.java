package entity.listener;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import util.JpaUtil;

@Stateless
public class EntityServiceImpl implements EntityService {

    @PersistenceContext(unitName = "ejbSandbox")
    private EntityManager em;

    /* (non-Javadoc)
     * @see entity.listener.EntityService#add(entity.listener.SimpleEntityImpl)
     */
    public SimpleEntity add(SimpleEntity entity) {
	em.persist(entity);
	return entity;
    }

    /* (non-Javadoc)
     * @see entity.listener.EntityService#update(entity.listener.SimpleEntityImpl)
     */
    public SimpleEntity update(SimpleEntity entity) {
	entity = em.merge(entity);
	return entity;
    }

    /* (non-Javadoc)
     * @see entity.listener.EntityService#remove(entity.listener.SimpleEntityImpl)
     */
    public SimpleEntity remove(SimpleEntity entity) {
	entity = em.merge(entity);
	em.remove(entity);
	return entity;
    }

    /* (non-Javadoc)
     * @see entity.listener.EntityService#find(java.lang.Long)
     */
    public SimpleEntity find(Long id) {
	return em.find(SimpleEntity.class, id);
    }

    /* (non-Javadoc)
     * @see entity.listener.EntityService#find()
     */
    public List<SimpleEntity> find() {
	return JpaUtil.castList(SimpleEntity.class, em.createQuery(
		"SELECT ent FROM Listener_SimpleEntity ent").getResultList());
    }

}
