package com.tougher.intranet.struts.employee.unit;


/**
 * @struts.form name="UpdateUnitForm"
 */
public class UpdateUnitForm extends AddUnitForm {

	/**
	 * @struts.validator type="required" msgkey="errors.required"
	 */
	public void setUnitName(String unitName) {
		super.setUnitName(unitName);
	}
	
}
