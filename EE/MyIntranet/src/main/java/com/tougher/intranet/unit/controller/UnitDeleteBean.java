package com.tougher.intranet.unit.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tougher.intranet.unit.model.domain.Unit;
import com.tougher.intranet.unit.model.service.UnitService;
import com.tougher.util.JSFUtil;
import com.tougher.util.SpringUtil;

@ManagedBean
@SessionScoped
public class UnitDeleteBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Unit unit = new Unit();

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Unit getUnit() {
		return unit;
	}

	public String loadUnit() {
		String unitCode = JSFUtil.getParameter("unitCode");
		UnitService unitService = SpringUtil.getBeanByClass(UnitService.class);
		unit = unitService.getUnit(Long.valueOf(unitCode));
		return "UnitDelete";
	}

	public String deleteUnit() {
		UnitService unitService = SpringUtil.getBeanByClass(UnitService.class);
		if (unitService.checkNoSubunits(unit)) {
			FacesContext ctx = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(JSFUtil.getProperty("unitProp",
					"unitHasSubunits"));
			ctx.addMessage(null, msg);
			return null;
		}
		unitService.deleteUnit(unit);
		return "UnitSearch?faces-redirect=true";
	}
}
