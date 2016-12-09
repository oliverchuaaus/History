package com.tougher.intranet.global.controller;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=String.class)
public class StringTrimConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component,
			String newValue)  {
		if (newValue == null) {
			return newValue;
		}
		return ((String) newValue).trim();
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object newValue) {
		if (newValue == null) {
			return null;
		}
		String str = (String) getAsObject(context, component,
				newValue.toString());
		return str;
	}
	
	
}
