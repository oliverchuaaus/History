package com.tougher.intranet.unit.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import com.tougher.intranet.unit.model.domain.Unit;
import com.tougher.intranet.unit.model.service.UnitService;
import com.tougher.util.JSFUtil;
import com.tougher.util.SpringUtil;

@ManagedBean
@ApplicationScoped
public class UnitCommonBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public List<SelectItem> getUnitList() {
		UnitService unitService = SpringUtil.getBeanByClass(UnitService.class);
		List<Unit> unitList = unitService.getUnitList();
		return JSFUtil.convertToSelectItems(unitList, "unitName");
	}
	
}
