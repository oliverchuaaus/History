package entity.listener;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name = "Listener_SimpleEntity2")
@EntityListeners( { EntityListener.class })
public class SimpleEntity2 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Transient
    private String audit = "";
    private String field;

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

    public void addAudit(String newString) {
	audit += newString;
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

}
