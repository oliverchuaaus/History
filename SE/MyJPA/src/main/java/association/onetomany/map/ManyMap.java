package association.onetomany.map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "OneToMany_Map_Many")
public class ManyMap {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private OneMap one;

	private String manyField;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public OneMap getOne() {
		return one;
	}

	public void setOne(OneMap one) {
		this.one = one;
	}

	public String getManyField() {
		return manyField;
	}

	public void setManyField(String manyField) {
		this.manyField = manyField;
	}
}
/*
 * CREATE TABLE "SANDBOX"."ONETOMANY_MANYMAP" ( "ID" NUMBER(19,0) NOT NULL
 * ENABLE, "MANYFIELD" VARCHAR2(255 CHAR), "ONE_ID" NUMBER(19,0), PRIMARY KEY
 * ("ID") ENABLE, CONSTRAINT "FKB4D91E7EF03A6457" FOREIGN KEY ("ONE_ID")
 * REFERENCES "SANDBOX"."ONETOMANY_ONEMAP" ("ID") ENABLE )
 */