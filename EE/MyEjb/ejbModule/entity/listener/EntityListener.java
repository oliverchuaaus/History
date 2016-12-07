package entity.listener;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class EntityListener {

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity2#prePersist()
     */
    @PrePersist
    public void prePersist(SimpleEntity2 entity) {
	System.out.println("prePersist");
	entity.addAudit("prePersist, ");
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity2#posPersist()
     */
    @PostPersist
    public void postPersist(SimpleEntity2 entity) {
	System.out.println("postPersist");
	entity.addAudit("postPersist, ");
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity2#preRemove()
     */
    @PreRemove
    public void preRemove(SimpleEntity2 entity) {
	System.out.println("preRemove");
	entity.addAudit("preRemove, ");
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity2#postRemove()
     */
    @PostRemove
    public void postRemove(SimpleEntity2 entity) {
	System.out.println("postRemove");
	entity.addAudit("postRemove, ");
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity2#preUpdate()
     */
    @PreUpdate
    public void preUpdate(SimpleEntity2 entity) {
	System.out.println("preUpdate");
	entity.addAudit("preUpdate, ");
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity2#postUpdate()
     */
    @PostUpdate
    public void postUpdate(SimpleEntity2 entity) {
	System.out.println("postUpdate");
	entity.addAudit("postUpdate, ");
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity2#postLoad()
     */
    @PostLoad
    public void postLoad(SimpleEntity2 entity) {
	System.out.println("postLoad");
	entity.addAudit("postLoad, ");
    }

}
