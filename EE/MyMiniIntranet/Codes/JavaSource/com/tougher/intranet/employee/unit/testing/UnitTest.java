package com.tougher.intranet.employee.unit.testing;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tougher.intranet.employee.unit.Unit;
import com.tougher.util.HibernateUtil;

import junit.framework.TestCase;

public class UnitTest extends TestCase {

	public void testUnit() {
		Session s = HibernateUtil.currentSession();
		Transaction t = s.beginTransaction();

		Unit u = new Unit();
		u.setUnitName("MyUnit");
		s.save(u);
		t.commit();

		s.get(Unit.class, new Long("1"));
	}

}
