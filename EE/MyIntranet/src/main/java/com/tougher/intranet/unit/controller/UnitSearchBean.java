package com.tougher.intranet.unit.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.tougher.intranet.unit.model.domain.Unit;
import com.tougher.intranet.unit.model.service.UnitSearchDto;
import com.tougher.intranet.unit.model.service.UnitSearchType;
import com.tougher.intranet.unit.model.service.UnitService;
import com.tougher.util.JSFUtil;
import com.tougher.util.SpringUtil;

@ManagedBean
@SessionScoped
public class UnitSearchBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private UnitSearchDto unitSearchDto = new UnitSearchDto();
    private List<Unit> unitSearchList;
    private SelectItem[] unitSearchTypes;
    private Long recordCount;
    private HtmlDataTable unitTable;

    public HtmlDataTable getUnitTable() {
	return unitTable;
    }

    public void setUnitTable(HtmlDataTable unitTable) {
	this.unitTable = unitTable;
    }

    public UnitSearchDto getUnitSearchDto() {
	return unitSearchDto;
    }

    public SelectItem[] getUnitSearchTypes() {
	if (unitSearchTypes == null) {
	    unitSearchTypes = new SelectItem[UnitSearchType.values().length];
	    for (int i = 0; i < unitSearchTypes.length; i++) {
		String label = "";
		if (i == 0) {
		    label = JSFUtil.getProperty("unitProp", "unitName");
		} else {
		    label = JSFUtil.getProperty("unitProp", "superUnit");
		}
		unitSearchTypes[i] = new SelectItem(
			UnitSearchType.values()[i].name(), label);
	    }
	}
	return unitSearchTypes;

    }

    public String searchUnit() {
	FacesContext ctx = FacesContext.getCurrentInstance();
	Map<String, Object> map = ctx.getExternalContext()
			.getRequestMap();
	UnitSearchDto dto = (UnitSearchDto)map.get("unitSearchDto");
	
	
	String firstResult = JSFUtil.getParameter("firstResult");
	if (firstResult != null) {
	    unitSearchDto.setFirstResult(Integer.parseInt(firstResult));
	}

	UnitService unitService = SpringUtil.getBeanByClass(UnitService.class);
	this.setUnitSearchList(unitService.getUnitListSubset(unitSearchDto));
	this.setRecordCount(unitService.getUnitListSubsetCount(unitSearchDto));
	return "UnitSearch";
    }

    public void setUnitSearchList(List<Unit> unitSearchList) {
	this.unitSearchList = unitSearchList;
    }

    public List<Unit> getUnitSearchList() {
	return unitSearchList;
    }

    public Long getRecordCount() {
	return recordCount;
    }

    public void setRecordCount(Long recordCount) {
	this.recordCount = recordCount;
    }

    public List<SelectItem> getUnitList() {
	UnitService unitService = SpringUtil.getBeanByClass(UnitService.class);
	List<Unit> unitList = unitService.getUnitList();
	return JSFUtil.convertToSelectItems(unitList, "unitName");
    }
}
