package com.tougher.intranet.employee.unit.testing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

import com.tougher.util.FileUtil;
import com.tougher.util.HibernateUtil;
import com.tougher.util.ProgramAttributes;
import com.tougher.intranet.employee.unit.SearchUnit;
import com.tougher.intranet.employee.unit.Unit;
import com.tougher.intranet.employee.unit.UnitFacade;

public class UnitFacadeTest extends TestCase {
	public void setUp() throws Exception {
		String sqlDir = ProgramAttributes.getProperty("sqlDir");
		HibernateUtil.runStatement("DELETE FROM Unit ORDER BY unitCode DESC");
		String statement = FileUtil.fileToString(new File(sqlDir + "Unit.sql"));
		HibernateUtil.runStatement(statement);
	}
	public void testgetUnit() throws Exception {
		Unit unit= UnitFacade.getUnit("1");
		assertEquals("ABS-OSD", unit.getUnitName());
		unit= UnitFacade.getUnit("2");
		assertEquals("Tech Team", unit.getUnitName());
		assertEquals("ABS-OSD", unit.getSuperUnit().getUnitName());
	}
	
	public void testgetUnit2() throws Exception {
		Unit unit= UnitFacade.getUnit("1");
		assertEquals("ABS-OSD", unit.getUnitName());
		assertEquals(4, unit.getSubUnits().size());
		ArrayList al=new ArrayList(unit.getSubUnits());
		unit=(Unit)al.get(0);
		assertEquals("Assistant Vice President", unit.getUnitName());
		unit=(Unit)al.get(3);
		assertEquals("Tech Team", unit.getUnitName());		
		assertEquals("ABS-OSD", unit.getSuperUnit().getUnitName());
	}

	public void testgetUnitList() throws Exception {
		List l = UnitFacade.getUnitList();
		assertEquals(11, l.size());
		Unit u = (Unit) l.get(0);
		assertEquals("ABS-OSD", u.getUnitName());
		assertEquals(null, u.getSuperUnit());
		u = (Unit) l.get(10);
		assertEquals("Tech Team", u.getUnitName());
		assertEquals("ABS-OSD", u.getSuperUnit().getUnitName());

		HibernateUtil.runStatement("DELETE FROM Unit ORDER BY unitCode DESC");
		l = UnitFacade.getUnitList();
		assertEquals(0, l.size());
	}
	public void testgetUnitListSubset() throws Exception {
		String sqlDir = ProgramAttributes.getProperty("sqlDir");
		HibernateUtil.runStatement("DELETE FROM Unit ORDER BY unitCode DESC");
		String statement = FileUtil.fileToString(new File(sqlDir
				+ "Unit-Big.sql"));
		HibernateUtil.runStatement(statement);

		SearchUnit su = new SearchUnit();
		su.setUnitSubstring("");
		su.setSearchType(SearchUnit.SEARCH_BY_UNIT_SUBSTRING);
		su.setItemNumber("0");
		Integer recordCount = UnitFacade.getUnitListSubsetCount(su);
		assertEquals(95, recordCount.intValue());
		List l = UnitFacade.getUnitListSubset(su);
		assertEquals(10, l.size());
		Unit u = (Unit) l.get(0);
		assertEquals("01", u.getUnitName());
		u = (Unit) l.get(9);
		assertEquals("10", u.getUnitName());

		su.setItemNumber("90");
		l = UnitFacade.getUnitListSubset(su);
		assertEquals(5, l.size());
		u = (Unit) l.get(0);
		assertEquals("91", u.getUnitName());
		u = (Unit) l.get(4);
		assertEquals("95", u.getUnitName());
	}

	public void testgetUnitListSubsetBySuperUnit() throws Exception {
		SearchUnit su = new SearchUnit();

		su.setSearchType(SearchUnit.SEARCH_BY_SUPER_UNIT);
		su.setItemNumber("0");
		su.setSuperUnitCode("1");
		List l = UnitFacade.getUnitListSubset(su);
		assertEquals(4, l.size());
		Unit u = (Unit) l.get(0);
		assertEquals("Assistant Vice President", u.getUnitName());
		u = (Unit) l.get(3);
		assertEquals("Tech Team", u.getUnitName());

		su.setSuperUnitCode("0");
		l = UnitFacade.getUnitListSubset(su);
		assertEquals(1, l.size());
		u = (Unit) l.get(0);
		assertEquals("ABS-OSD", u.getUnitName());

	}

	public void testAddUnit() {
		Unit unit = new Unit();
		unit.setUnitName("New Unit");
		UnitFacade.addUnit(unit);
		Long unitCode = unit.getUnitCode();

		unit = UnitFacade.getUnit(unitCode.toString());
		assertEquals("New Unit", unit.getUnitName());
		assertEquals(null, unit.getSuperUnit());
		assertEquals(null, unit.getSuperUnitCode());
	}

	public void testAddUnit2() {
		Unit unit = new Unit();
		unit.setUnitName("Technical Support");
		unit.setSuperUnitCode("1");
		UnitFacade.addUnit(unit);
		Long unitCode = unit.getUnitCode();

		unit = UnitFacade.getUnit(unitCode.toString());
		assertEquals("Technical Support", unit.getUnitName());
		//Unit su=UnitFacade.getUnit("1");
		//assertEquals(su,unit.getSuperUnit());
		assertEquals("1", unit.getSuperUnit().getUnitCode().toString());
	}

}
