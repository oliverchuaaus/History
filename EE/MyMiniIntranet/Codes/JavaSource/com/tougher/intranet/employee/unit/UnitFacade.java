package com.tougher.intranet.employee.unit;

import java.util.List;

import com.tougher.intranet.employee.unit.dao.UnitDAOHibernate;
import com.tougher.util.SpringUtil;

public class UnitFacade {

	private static UnitDAOHibernate dao = (UnitDAOHibernate)SpringUtil.getContext().getBean("unitDAO");

	public static List getUnitList() {
		return dao.getUnitList();
	}
	
	public static List getUnitListWithNone() {
		List l=dao.getUnitList();
		Unit u=new Unit();
		u.setUnitCode(new Long(Unit.NONE));
		u.setUnitName("None");
		l.add(0,u);
		return l;
	}	

	public static List getUnitListSubset(SearchUnit searchUnit) {
		return dao.getUnitListSubset(searchUnit);
	}

	public static Integer getUnitListSubsetCount(SearchUnit searchUnit) {
		return dao.getUnitListSubsetCount(searchUnit);
	}
	
	public static void addUnit(Unit unit) {
		dao.addUnit(unit);
	}

	public static void updateUnit(Unit unit) {
		Unit u2=getUnit(""+unit.getUnitCode());
		u2.setSuperUnit(unit.getSuperUnit());
		u2.setUnitName(unit.getUnitName());
		dao.updateUnit(u2);
	}

	public static void deleteUnit(Unit unit) {
		Unit u2=getUnit(unit.getUnitCode().toString());
		dao.deleteUnit(u2);
	}
	
	public static Unit getUnit(String unitCode) {
		return dao.getUnit(unitCode);
	}

}
