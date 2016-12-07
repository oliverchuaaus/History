package com.tougher.intranet.unit.model.dao;

import static junit.framework.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.tougher.intranet.unit.model.domain.Unit;
import com.tougher.util.BaseTest;
import com.tougher.util.DBUtil;
import com.tougher.util.SpringUtil;

public class UnitDaoTest extends BaseTest {

	@Test
	public void testCreate() {
		Unit u = new Unit();
		u.setUnitName("unitName");
		UnitDao dao = SpringUtil.getBeanByClass(UnitDao.class);
		dao.persist(u);

		u = dao.findById(u.getUnitCode());
		assertEquals("unitName", u.getUnitName());
	}

	@Test
	public void testList() {
		DBUtil.populate("com/tougher/intranet/unit/unit.xml");
		UnitDao dao = SpringUtil.getBeanByClass(UnitDao.class);
		List<Unit> list = dao.findAll();
		assertEquals(11, list.size());
	}
}
