package association.onetomany.map;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

/**
 * OneToMany Map Bidirectional
 * 
 * Composite
 */
@Entity(name = "OneToMany_Map_One")
public class OneMap {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy = "one")
	@MapKey(name = "manyField")
	Map<String, ManyMap> many = new HashMap<String, ManyMap>();

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Map<String, ManyMap> getMany() {
		return many;
	}
}
/*
 * CREATE TABLE "SANDBOX"."ONETOMANY_ONEMAP" ( "ID" NUMBER(19,0) NOT NULL
 * ENABLE, PRIMARY KEY ("ID") ENABLE )
 */