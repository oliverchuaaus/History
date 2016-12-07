package association.secondary;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * First secondary table shows how to specify field name for foreign key. Second
 * secondary table shows how to specify unique constraint.
 * 
 * @author Toffer
 * 
 */
@Entity
@Table(name = "Secondary_Entity")
@SecondaryTables({
		@SecondaryTable(name = "Secondary_Table1", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "table_id", referencedColumnName = "id") }),
		@SecondaryTable(name = "Secondary_Table2", uniqueConstraints = { @UniqueConstraint(columnNames = { "storyPart2" }) }) })
public class SecondaryTableEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String field;

	@Column(table = "Secondary_Table1")
	private String storyPart1;

	@Column(table = "Secondary_Table2")
	private String storyPart2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getStoryPart1() {
		return storyPart1;
	}

	public void setStoryPart1(String storyPart1) {
		this.storyPart1 = storyPart1;
	}

	public String getStoryPart2() {
		return storyPart2;
	}

	public void setStoryPart2(String storyPart2) {
		this.storyPart2 = storyPart2;
	}
}
/*
 * CREATE TABLE "ASSOCIATION_SECONDARY_ENTITY" ( "ID" NUMBER(19,0) NOT NULL
 * ENABLE, "FIELD" VARCHAR2(255 CHAR), PRIMARY KEY ("ID") ENABLE )
 * 
 * CREATE TABLE "ASSOCIATION_SECONDARY_TABLE1" ( "STORYPART1" VARCHAR2(255
 * CHAR), "TABLE_ID" NUMBER(19,0) NOT NULL ENABLE, PRIMARY KEY ("TABLE_ID")
 * ENABLE )
 * 
 * CREATE TABLE "ASSOCIATION_SECONDARY_TABLE2" ( "STORYPART2" VARCHAR2(255
 * CHAR), "ID" NUMBER(19,0) NOT NULL ENABLE, PRIMARY KEY ("ID") ENABLE, UNIQUE
 * ("STORYPART2") ENABLE
 */
