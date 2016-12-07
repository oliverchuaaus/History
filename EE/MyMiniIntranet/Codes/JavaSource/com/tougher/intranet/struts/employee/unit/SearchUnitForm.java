package com.tougher.intranet.struts.employee.unit;

import org.apache.struts.validator.ValidatorForm;
import com.tougher.intranet.employee.unit.SearchUnit;

/**
 * @struts.form name="SearchUnitForm"
 */
public class SearchUnitForm extends ValidatorForm {
	private SearchUnit bean=new SearchUnit();

	public SearchUnit getBean() {
		return bean;
	}

	public void setBean(SearchUnit bean) {
		this.bean = bean;
	}

	public String getItemNumber() {
		return bean.getItemNumber();
	}

	public int getItemNumberAsInt() {
		return bean.getItemNumberAsInt();
	}

	public void setItemNumber(String itemNumber) {
		bean.setItemNumber(itemNumber);
	}

	public String getSearchType() {
		return bean.getSearchType();
	}

	public String getSuperUnitCode() {
		return bean.getSuperUnitCode();
	}

	public String getUnitSubstring() {
		return bean.getUnitSubstring();
	}
	public void setSearchType(String searchType) {
		bean.setSearchType(searchType);
	}

	public void setSuperUnitCode(String superUnitCode) {
		bean.setSuperUnitCode(superUnitCode);
	}

	public void setUnitSubstring(String unitSubstring) {
		bean.setUnitSubstring(unitSubstring);
	}
}
