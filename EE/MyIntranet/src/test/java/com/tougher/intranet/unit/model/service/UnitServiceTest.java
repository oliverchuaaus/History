package com.tougher.intranet.unit.model.service;

import static junit.framework.Assert.assertEquals;

import java.util.List;

import javax.faces.model.SelectItem;

import org.junit.Test;

import com.tougher.intranet.unit.model.domain.Unit;
import com.tougher.util.BaseTest;
import com.tougher.util.DBUtil;
import com.tougher.util.JSFUtil;
import com.tougher.util.SpringUtil;

public class UnitServiceTest extends BaseTest {

	private UnitService us = SpringUtil.getBeanByClass(UnitService.class);

	@Test
	public void testCreate() {
		Unit u = new Unit();
		u.setUnitName("unitName");
		us.addUnit(u);
		u = us.getUnit(u.getUnitCode());
		assertEquals("unitName", u.getUnitName());
	}

	@Test
	public void testGetUnit() throws Exception {
		DBUtil.populate("com/tougher/intranet/unit/unit.xml");
		Unit unit = us.getUnit(101L);
		assertEquals("ABS-OSD", unit.getUnitName());
		unit = us.getUnit(102L);
		assertEquals("Tech Team", unit.getUnitName());
		assertEquals("ABS-OSD", unit.getSuperUnit().getUnitName());
	}

	@Test
	public void testGetUnitBig() throws Exception {
		DBUtil.populate("com/tougher/intranet/unit/unit-big.xml");

		UnitSearchDto searchUnitDto = new UnitSearchDto();
		searchUnitDto.setFirstResult(0);
		searchUnitDto.setMaxResults(10);

		Long count = us.getUnitListSubsetCount(searchUnitDto);
		assertEquals(95L, count.intValue());

		List<Unit> unitList = us.getUnitListSubset(searchUnitDto);
		assertEquals(10, unitList.size());
		Unit unit = unitList.get(0);
		assertEquals(1L, unit.getUnitCode().longValue());
		unit = unitList.get(9);
		assertEquals(10L, unit.getUnitCode().longValue());

		searchUnitDto.setFirstResult(10);
		searchUnitDto.setMaxResults(5);
		unitList = us.getUnitListSubset(searchUnitDto);
		assertEquals(5, unitList.size());
		unit = unitList.get(0);
		assertEquals(11L, unit.getUnitCode().longValue());
		unit = unitList.get(4);
		assertEquals(15L, unit.getUnitCode().longValue());
	}

	@Test
	public void testGetUnitSelectItem() throws Exception {
		DBUtil.populate("com/tougher/intranet/unit/unit.xml");
		List<Unit> unitList = us.getUnitList();
		List<SelectItem> itemList = JSFUtil.convertToSelectItems(unitList,
				"unitName");
		assertEquals(unitList.size(), itemList.size());
	}
}
