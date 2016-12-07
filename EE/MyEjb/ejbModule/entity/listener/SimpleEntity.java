package entity.listener;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity(name="Listener_SimpleEntity")
public class SimpleEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Transient
    private String audit = "";
    private String field;
    @Version
    private Long version;

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#getId()
     */
    public Long getId() {
	return id;
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#setId(java.lang.Long)
     */
    public void setId(Long id) {
	this.id = id;
    }

    public String getAudit() {
	return audit;
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#getField()
     */
    public String getField() {
	return field;
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#setField(java.lang.String)
     */
    public void setField(String field) {
	this.field = field;
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#prePersist()
     */
    @PrePersist
    public void prePersist() {
	System.out.println("prePersist");
	audit += "prePersist, ";
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#posPersist()
     */
    @PostPersist
    public void postPersist() {
	System.out.println("postPersist");
	audit += "postPersist, ";
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#preRemove()
     */
    @PreRemove
    public void preRemove() {
	System.out.println("preRemove");
	audit += "preRemove, ";
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#postRemove()
     */
    @PostRemove
    public void postRemove() {
	System.out.println("postRemove");
	audit += "postRemove, ";
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#preUpdate()
     */
    @PreUpdate
    public void preUpdate() {
	System.out.println("preUpdate");
	audit += "preUpdate, ";
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#postUpdate()
     */
    @PostUpdate
    public void postUpdate() {
	System.out.println("postUpdate");
	audit += "postUpdate, ";
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#postLoad()
     */
    @PostLoad
    public void postLoad() {
	System.out.println("postLoad");
	audit += "postLoad, ";
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
