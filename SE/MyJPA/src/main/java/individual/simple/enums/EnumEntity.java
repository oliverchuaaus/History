package individual.simple.enums;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name = "Individual_Enum_Entity")
/**
 * Shows how to map a custom enum field to the db.
 * This requires get and set methods that convert fields to db.
 */
public class EnumEntity {
	private Long id;

	private MaritalStatusEnum maritalStatusEnum;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Transient
	public MaritalStatusEnum getMaritalStatusEnum() {
		return maritalStatusEnum;
	}

	public void setMaritalStatusEnum(MaritalStatusEnum maritalStatusEnum) {
		this.maritalStatusEnum = maritalStatusEnum;
	}

	@Column(name = "maritalStatusEnum", length = 1)
	String getMaritalStatusString() {
		return maritalStatusEnum.getCode();
	}

	void setMaritalStatusString(String maritalStatusString) {
		this.maritalStatusEnum = MaritalStatusEnum
				.valueOfCode(maritalStatusString);
	}
}

/*
 * CREATE TABLE "INDIVIDUAL_ENUM_ENTITY1" ( "ID" NUMBER(19,0) NOT NULL ENABLE,
 * "MARITALSTATUSENUM" VARCHAR2(1 CHAR), PRIMARY KEY ("ID") ENABLE )
 */