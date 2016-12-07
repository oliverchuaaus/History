package com.tougher.intranet.unit.controller;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;

import com.tougher.intranet.unit.model.domain.Unit;
import com.tougher.intranet.unit.model.service.UnitService;
import com.tougher.util.SpringUtil;

@FacesConverter(value="unitConverter")
public class UnitConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		UnitService unitService = SpringUtil.getBeanByClass(UnitService.class);
		if (StringUtils.isEmpty(arg2)){
			return null;
		}
		return unitService.getUnit(Long.valueOf(arg2));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof String) return null;
		return ((Unit)arg2).getUnitCode().toString();
	}

}
