package individual.simple;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Individual_Simple_Entity")
public class SimpleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// No default access type. Default is set by how Id is set.
	private Long id;

	// Uses 255 as default length
	private String stringField;

	// use property access instead of property
	@Access(AccessType.PROPERTY)
	@Column(length = 6)
	private String stringFieldLength;

	@Temporal(TemporalType.TIME)
	// Time field is not shown in Oracle SQLDeveloper, but it is stored and
	// retrieved correctly.
	private Date timeField;

	@Temporal(TemporalType.DATE)
	private Date dateField;
	
	// Can only use java.util.Date and java.util.Calendar for @Temporal
	// By default, is TemporalType.TIMESTAMP
	private Calendar timeStampField;
/*		
	@Temporal(TemporalType.DATE)
	private LocalDate localDateField;

	@Temporal(TemporalType.TIME)
	private LocalTime localTimeField;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime localDateTimeField;
*/
	@Enumerated(EnumType.STRING)
	private GenderEnum enumStringField;

	// Ordinal starts at 0.
	@Enumerated(EnumType.ORDINAL)
	private GenderEnum enumOrdinalField;

	@Lob
	private String clobField;

	@Lob
	private byte[] blobField;

	private boolean booleanField;

	private int intField;

	private double doubleField;

	// Declaring as double will make db field float and will not check precision
	@Column(precision = 3, scale = 2)
	private BigDecimal bigDecimalField;

	@Column(precision = 1, scale = 0)
	private BigInteger bigIntegerField;

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

	public String getStringFieldLength() {
		return stringFieldLength;
	}

	public void setStringFieldLength(String stringFieldLength) {
		this.stringFieldLength = stringFieldLength;
	}

	public Date getTimeField() {
		return timeField;
	}

	public void setTimeField(Date timeField) {
		this.timeField = timeField;
	}

	public Date getDateField() {
		return dateField;
	}

	public void setDateField(Date dateField) {
		this.dateField = dateField;
	}

	public Calendar getTimeStampField() {
		return timeStampField;
	}

	public void setTimeStampField(Calendar timeStampField) {
		this.timeStampField = timeStampField;
	}

	public GenderEnum getEnumStringField() {
		return enumStringField;
	}

	public void setEnumStringField(GenderEnum enumStringField) {
		this.enumStringField = enumStringField;
	}

	public GenderEnum getEnumOrdinalField() {
		return enumOrdinalField;
	}

	public void setEnumOrdinalField(GenderEnum enumOrdinalField) {
		this.enumOrdinalField = enumOrdinalField;
	}

	public String getClobField() {
		return clobField;
	}

	public void setClobField(String clobField) {
		this.clobField = clobField;
	}

	public byte[] getBlobField() {
		return blobField;
	}

	public void setBlobField(byte[] blobField) {
		this.blobField = blobField;
	}

	public boolean isBooleanField() {
		return booleanField;
	}

	public void setBooleanField(boolean booleanField) {
		this.booleanField = booleanField;
	}

	public int getIntField() {
		return intField;
	}

	public void setIntField(int intField) {
		this.intField = intField;
	}

	public double getDoubleField() {
		return doubleField;
	}

	public void setDoubleField(double doubleField) {
		this.doubleField = doubleField;
	}

	public BigDecimal getBigDecimalField() {
		return bigDecimalField;
	}

	public void setBigDecimalField(BigDecimal bigDecimalField) {
		this.bigDecimalField = bigDecimalField;
	}

	public BigInteger getBigIntegerField() {
		return bigIntegerField;
	}

	public void setBigIntegerField(BigInteger bigIntegerField) {
		this.bigIntegerField = bigIntegerField;
	}

}

/*
 * CREATE TABLE "INDIVIDUALSIMPLEENTITY1" ( "ID" NUMBER(19,0) NOT NULL ENABLE,
 * "STRINGFIELD" VARCHAR2(255 CHAR), "STRINGFIELDLENGTH" VARCHAR2(6 CHAR),
 * "TIMEFIELD" DATE, "DATEFIELD" DATE, "TIMESTAMPFIELD" TIMESTAMP (6),
 * "ENUMSTRINGFIELD" VARCHAR2(255 CHAR), "ENUMORDINALFIELD" NUMBER(10,0),
 * "CLOBFIELD" CLOB, "BLOBFIELD" BLOB, "BOOLEANFIELD" NUMBER(1,0) NOT NULL
 * ENABLE, "DOUBLEFIELD" FLOAT(126) NOT NULL ENABLE, "BIGDECIMALFIELD"
 * NUMBER(3,2), "BIGINTEGERFIELD" NUMBER(1,0), "VERSION" NUMBER(10,0) NOT NULL
 * ENABLE, PRIMARY KEY ("ID") ENABLE )
 */

