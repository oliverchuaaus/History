package com.tougher.intranet.employee.unit;

import java.io.Serializable;

public class SearchUnit implements Serializable{
	public static final String SEARCH_BY_UNIT_SUBSTRING="SEARCH_BY_UNIT_SUBSTRING";
	public static final String SEARCH_BY_SUPER_UNIT="SEARCH_BY_SUPER_UNIT";
	
	private String searchType=SEARCH_BY_UNIT_SUBSTRING;
	private String unitSubstring="";
	private String superUnitCode;
	private String itemNumber="0";
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSuperUnitCode() {
		return superUnitCode;
	}
	public void setSuperUnitCode(String superUnitCode) {
		this.superUnitCode = superUnitCode;
	}
	public String getUnitSubstring() {
		return unitSubstring;
	}
	public void setUnitSubstring(String unitSubstring) {
		this.unitSubstring = unitSubstring;
	}
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	public int getItemNumberAsInt() {
		return Integer.parseInt(itemNumber);
	}

}
