package test.association.manytomany;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import util.JPAUtil;
import association.manytomany.bi.ManyA;
import association.manytomany.bi.ManyB;
import association.manytomany.uni.ManyAUni;
import association.manytomany.uni.ManyBUni;

public class ManytoManyTest{
	public void testBi() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		ManyA a = new ManyA();
		ManyB b = new ManyB();
		a.getManyBSet().add(b);
		em.persist(a);
		em.persist(b);
		em.getTransaction().commit();

		a = (ManyA) em.find(ManyA.class, a.getId());
		assertNotNull(a);
		assertEquals(b.getId(), a.getManyBSet().iterator().next().getId());
	}

	public void testUni() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		ManyAUni a = new ManyAUni();
		ManyBUni b = new ManyBUni();
		a.getManyBUniSet().add(b);
		em.persist(a);
		em.persist(b);
		em.getTransaction().commit();

		a = (ManyAUni) em.find(ManyAUni.class, a.getId());
		assertNotNull(a);
		assertEquals(b.getId(), a.getManyBUniSet().iterator().next().getId());
	}
}
