package com.tougher.intranet.struts.employee.unit;

import org.apache.struts.validator.ValidatorForm;

import com.tougher.intranet.employee.unit.Unit;

/**
 * @struts.form name="AddUnitForm"
 */
public class AddUnitForm extends ValidatorForm{
	protected Unit bean=new Unit();

	public Unit getBean() {
		return bean;
	}

	public void setBean(Unit bean) {
		this.bean = bean;
	}

	public Unit getSuperUnit() {
		return bean.getSuperUnit();
	}

	public Long getUnitCode() {
		return bean.getUnitCode();
	}

	public String getUnitName() {
		return bean.getUnitName();
	}

	public void setSuperUnit(Unit superUnit) {
		bean.setSuperUnit(superUnit);
	}

	public void setUnitCode(Long unitCode) {
		bean.setUnitCode(unitCode);
	}
	/**
	 * @struts.validator type="required" msgkey="errors.required"
	 */
	public void setUnitName(String unitName) {
		bean.setUnitName(unitName);
	}

	public String getSuperUnitCode() {
		return bean.getSuperUnitCode();
	}

	public void setSuperUnitCode(String superUnitCode) {
		bean.setSuperUnitCode(superUnitCode);
	}



}
