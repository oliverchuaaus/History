package com.tougher.intranet.unit.model.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity(name = "Unit")
@SequenceGenerator(name = "SEQ_UNIT", sequenceName = "SEQ_UNIT", allocationSize = 1, initialValue = 200)
public class Unit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_UNIT")
    @Column(name = "UNIT_CODE")
    private Long unitCode;
    @Column(name = "UNIT_NAME", unique = true)
    private String unitName;
    @ManyToOne
    @JoinColumn(name = "SUPER_UNIT_CODE")
    private Unit superUnit;
    @OneToMany(mappedBy = "superUnit")
    private Set<Unit> subUnits;

    public Long getUnitCode() {
	return unitCode;
    }

    public void setUnitCode(Long unitCode) {
	this.unitCode = unitCode;
    }

    public String getUnitName() {
	return unitName;
    }

    public void setUnitName(String unitName) {
	this.unitName = unitName;
    }

    public Unit getSuperUnit() {
	return superUnit;
    }

    public void setSuperUnit(Unit superUnit) {
	this.superUnit = superUnit;
    }

    public Set<Unit> getSubUnits() {
	return subUnits;
    }

    public void setSubUnits(Set<Unit> subUnits) {
	this.subUnits = subUnits;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((superUnit == null) ? 0 : superUnit.hashCode());
	result = prime * result
		+ ((unitCode == null) ? 0 : unitCode.hashCode());
	result = prime * result
		+ ((unitName == null) ? 0 : unitName.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Unit other = (Unit) obj;
	if (superUnit == null) {
	    if (other.superUnit != null)
		return false;
	} else if (!superUnit.equals(other.superUnit))
	    return false;
	if (unitCode == null) {
	    if (other.unitCode != null)
		return false;
	} else if (!unitCode.equals(other.unitCode))
	    return false;
	if (unitName == null) {
	    if (other.unitName != null)
		return false;
	} else if (!unitName.equals(other.unitName))
	    return false;
	return true;
    }
}
