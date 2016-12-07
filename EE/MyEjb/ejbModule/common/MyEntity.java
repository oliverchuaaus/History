package common;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

@Entity
public class MyEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // Uses 255 as default length
    private String stringField;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getStringField() {
	return stringField;
    }

    public void setStringField(String stringField) {
	this.stringField = stringField;
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#prePersist()
     */
    @PrePersist
    public void prePersist() {
	System.out.println("prePersist");
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#posPersist()
     */
    @PostPersist
    public void postPersist() {
	System.out.println("postPersist");
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#preRemove()
     */
    @PreRemove
    public void preRemove() {
	System.out.println("preRemove");
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#postRemove()
     */
    @PostRemove
    public void postRemove() {
	System.out.println("postRemove");
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#preUpdate()
     */
    @PreUpdate
    public void preUpdate() {
	System.out.println("preUpdate");
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#postUpdate()
     */
    @PostUpdate
    public void postUpdate() {
	System.out.println("postUpdate");
    }

    /* (non-Javadoc)
     * @see entity.listener.SimpleEntity#postLoad()
     */
    @PostLoad
    public void postLoad() {
	System.out.println("postLoad");
    }
}