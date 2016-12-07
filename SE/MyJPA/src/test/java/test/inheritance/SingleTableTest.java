package test.inheritance;

import static org.junit.Assert.assertNotNull;
import inheritance.SingleTableChild1;
import inheritance.SingleTableChild2;
import inheritance.SingleTableParent;

import javax.persistence.EntityManager;

import org.junit.Test;

import util.JPAUtil;
//SingleTablePerHierarchy
public class SingleTableTest  {
	
	@Test
	public void testParent() {
		SingleTableParent p = new SingleTableParent();
		p.setParentField("fieldPlane");
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		p = em.find(SingleTableParent.class, p.getId());
		assertNotNull(p);
	}
	@Test
	public void testChild1() {
		SingleTableChild1 c = new SingleTableChild1();
		c.setParentField("fieldPlane");
		c.setChild1Field("field747");
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		c = (SingleTableChild1) em.find(SingleTableChild1.class, c.getId());
		assertNotNull(c);

		SingleTableParent p = em.find(SingleTableParent.class, c.getId());
		assertNotNull(p);
	}
	@Test
	public void testChild2() {
		SingleTableChild2 c = new SingleTableChild2();
		c.setParentField("fieldPlane");
		c.setChild2Field("field230");
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		c = (SingleTableChild2) em.find(SingleTableChild2.class, c.getId());
		assertNotNull(c);

		SingleTableParent p = (SingleTableParent) em.find(
				SingleTableParent.class, c.getId());
		assertNotNull(p);
	}
}
