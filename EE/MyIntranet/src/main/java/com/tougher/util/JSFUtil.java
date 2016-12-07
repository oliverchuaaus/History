package com.tougher.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.beanutils.BeanUtils;

public class JSFUtil {
	public static List<SelectItem> convertToSelectItems(List<?> objectList,
			String fieldName) {
		List<SelectItem> selectItems = new ArrayList<SelectItem>(
				objectList.size());
		for (Object object : objectList) {
			try {
				String value = BeanUtils.getProperty(object, fieldName);
				selectItems.add(new SelectItem(object, value));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return selectItems;
	}

	public static String getProperty(String propertyFile, String propertyName) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ResourceBundle rc = ctx.getApplication().getResourceBundle(ctx,
				propertyFile);
		return rc.getString(propertyName);
	}

	public static String findClientId(String id) {
		FacesContext context = FacesContext.getCurrentInstance();
		UIComponent component = findComponent(context.getViewRoot(), id);
		return component.getClientId();
	}

	private static UIComponent findComponent(UIComponent parent, String id) {
		if (id.equals(parent.getId())) {
			return parent;
		}
		Iterator<UIComponent> kids = parent.getFacetsAndChildren();
		while (kids.hasNext()) {
			UIComponent kid = kids.next();
			UIComponent found = findComponent(kid, id);
			if (found != null) {
				return found;
			}
		}
		return null;
	}

	public static String getParameter(String parameterName){
		FacesContext ctx = FacesContext.getCurrentInstance();
		Map<String, String> map = ctx.getExternalContext()
				.getRequestParameterMap();
		return map.get(parameterName);
	}
}
