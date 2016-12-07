package test.inheritance;

import static org.junit.Assert.assertNotNull;
import inheritance.JoinedChild1;
import inheritance.JoinedChild2;
import inheritance.JoinedParent;

import javax.persistence.EntityManager;

import org.junit.Test;

import util.JPAUtil;

//SingleTablePerHierarchy
public class JoinedTest {
	@Test
	public void testParent() {
		JoinedParent p = new JoinedParent();
		p.setParentField("fieldBoat");
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		p = (JoinedParent) em.find(JoinedParent.class, p.getId());
		assertNotNull(p);
	}

	@Test
	public void testChild1() {
		JoinedChild1 c = new JoinedChild1();
		c.setParentField("fieldBoat");
		c.setChild1Field("fieldCanoe");
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		c = (JoinedChild1) em.find(JoinedChild1.class, c.getId());
		assertNotNull(c);

		JoinedParent Boat = (JoinedParent) em.find(JoinedParent.class,
				c.getId());
		assertNotNull(Boat);
	}

	@Test
	public void testChild2() {
		JoinedChild2 c = new JoinedChild2();
		c.setParentField("fieldBoat");
		c.setChild2Field("fieldFerry");
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		c = (JoinedChild2) em.find(JoinedChild2.class, c.getId());
		assertNotNull(c);

		JoinedParent Boat = (JoinedParent) em.find(JoinedParent.class,
				c.getId());
		assertNotNull(Boat);
	}

}