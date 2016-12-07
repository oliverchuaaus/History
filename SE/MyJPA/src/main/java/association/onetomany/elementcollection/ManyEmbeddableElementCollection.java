package association.onetomany.elementcollection;

import javax.persistence.Embeddable;

@Embeddable
public class ManyEmbeddableElementCollection {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
