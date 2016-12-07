package com.tougher.intranet.unit.model.service;

import java.io.Serializable;

import com.tougher.intranet.unit.model.domain.Unit;

public class UnitSearchDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private UnitSearchType unitSearchType = UnitSearchType.SEARCH_BY_UNIT_SUBSTRING;
	private String unitSubstring;
	private Unit superUnit;
	private Integer firstResult = Integer.valueOf(0);
	private Integer maxResults = Integer.valueOf(10);;

	public void setUnitSearchType(UnitSearchType unitSearchType) {
		this.unitSearchType = unitSearchType;
	}

	public UnitSearchType getUnitSearchType() {
		return unitSearchType;
	}

	public String getUnitSubstring() {
		return unitSubstring;
	}

	public void setUnitSubstring(String unitSubstring) {
		this.unitSubstring = unitSubstring;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public void setSuperUnit(Unit superUnit) {
		this.superUnit = superUnit;
	}

	public Unit getSuperUnit() {
		return superUnit;
	}

}
