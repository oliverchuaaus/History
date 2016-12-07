package com.tougher.intranet.unit.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.tougher.intranet.unit.model.domain.Unit;
import com.tougher.intranet.unit.model.service.UnitService;
import com.tougher.util.JSFUtil;
import com.tougher.util.SpringUtil;

@ManagedBean
@RequestScoped
public class UnitAddBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Unit unit = new Unit();

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Unit getUnit() {
		return unit;
	}

	public String addUnit() {
		UnitService unitService = SpringUtil.getBeanByClass(UnitService.class);
		if (unitService.checkExisting(unit)) {
			FacesContext ctx = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(JSFUtil.getProperty("unitProp",
					"unitNameExisting"));
			ctx.addMessage(JSFUtil.findClientId("unitName"), msg);
			return null;
		}
		unitService.addUnit(unit);
		return "UnitSearch?faces-redirect=true";
	}
}
