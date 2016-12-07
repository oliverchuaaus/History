package com.tougher.intranet.unit.model.domain;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.tougher.util.EMUtil;

public class UnitTest {
	@Test
	public void testCreate() {
		EntityManager em = EMUtil.getEm();
		Unit unit = new Unit();
		unit.setUnitName("unitName");
		em.getTransaction().begin();
		em.persist(unit);
		em.getTransaction().commit();
		
		unit = em.find(Unit.class, unit.getUnitCode());
		assertEquals("unitName", unit.getUnitName());
	}
}
