package com.tougher.intranet.employee.unit;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Set;

/**
 * @hibernate.class table="Unit"
 * @hibernate.query name="getUnit" query="FROM Unit WHERE unitCode=?"
 * @hibernate.query name="getUnitList" query="FROM Unit ORDER BY unitName"
 * @hibernate.query name="getUnitListBySubstring" query="FROM Unit WHERE unitName LIKE ? ORDER BY unitName"
 * @hibernate.query name="getUnitListBySubstringCount" query="SELECT COUNT(*) FROM Unit WHERE unitName LIKE ? ORDER BY unitName"
 * @hibernate.query name="getUnitListBySuperUnit" query="FROM Unit u WHERE u.superUnit.unitCode=? ORDER BY unitName"
 * @hibernate.query name="getUnitListBySuperUnitCount" query="SELECT COUNT(*) FROM Unit u WHERE u.superUnit.unitCode=? ORDER BY unitName"
 * @hibernate.query name="getUnitListBySuperUnitNull" query="FROM Unit u WHERE u.superUnit IS NULL ORDER BY unitName"
 * @hibernate.query name="getUnitListBySuperUnitNullCount" query="SELECT COUNT(*) FROM Unit u WHERE u.superUnit IS NULL ORDER BY unitName"
 */
public class Unit implements Serializable, Comparator {
	public static final String NONE = "0";

	private Long unitCode;
	private String unitName;
	private Unit superUnit;
	private String superUnitCode;
	private Set subUnits;

	/**
	 * @hibernate.id generator-class="native"
	 */
	public Long getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(Long unitCode) {
		this.unitCode = unitCode;
	}
	/**
	 * @hibernate.property
	 */
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	/**
	 * @hibernate.many-to-one column="superUnitCode"
	 */
	public Unit getSuperUnit() {
		return superUnit;
	}
	public void setSuperUnit(Unit superUnit) {
		this.superUnit = superUnit;
	}
	public String getSuperUnitCode() {
		if (this.superUnit != null) {
			this.superUnitCode = superUnit.getUnitCode().toString();
		}
		return this.superUnitCode;
	}
	public void setSuperUnitCode(String superUnitCode) {
		this.superUnitCode = superUnitCode;
		if (superUnitCode != null && !NONE.equals(superUnitCode)) {
			this.superUnit = UnitFacade.getUnit(superUnitCode);
		}
	}
	/**
	 * @hibernate.set cascade="save-update" sort="com.tougher.intranet.employee.unit.Unit"
	 * @hibernate.collection-one-to-many class="com.tougher.intranet.employee.unit.Unit"
	 * @hibernate.collection-key column="superUnitCode"
	 * @return
	 */
	public Set getSubUnits() {
		return subUnits;
	}
	public void setSubUnits(Set subUnits) {
		this.subUnits = subUnits;
	}
	public int compare(Object o1, Object o2) {
		Unit u1 = (Unit) o1;
		Unit u2 = (Unit) o2;
		if (u1 != null && u2 != null) {
			if (u1.unitName != null && u2.unitName != null) {
				return u1.unitName.compareTo(u2.unitName);
			}
		}
		return 0;
	}

}
