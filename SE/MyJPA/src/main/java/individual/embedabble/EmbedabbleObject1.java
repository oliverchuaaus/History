package individual.embedabble;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * Stand-alone object that will be included in other classes.
 * 
 * @author Toffer
 */
@Embeddable
public class EmbedabbleObject1 implements Serializable {
	private static final long serialVersionUID = 1L;

	private String field;

	public void setField(String field) {
		this.field = field;
	}

	public String getField() {
		return field;
	}
}
