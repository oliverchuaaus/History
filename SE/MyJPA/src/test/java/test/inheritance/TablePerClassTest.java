package test.inheritance;

import static org.junit.Assert.assertNotNull;
import inheritance.TablePerClassChild1;
import inheritance.TablePerClassChild2;
import inheritance.TablePerClassParent;

import javax.persistence.EntityManager;

import org.junit.Test;

import util.JPAUtil;
//SingleTablePerHierarchy
public class TablePerClassTest  {
	@Test
	public void testChild1() {
		TablePerClassChild1 c = new TablePerClassChild1();
		c.setParentField("fieldPlane");
		c.setChild1Field("field747");
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		c = (TablePerClassChild1) em.find(TablePerClassChild1.class, c.getId());
		assertNotNull(c);

		TablePerClassParent p = (TablePerClassParent)em.find(TablePerClassChild1.class, c.getId());
		assertNotNull(p);
	}
	@Test
	public void testChild2() {
		TablePerClassChild2 c = new TablePerClassChild2();
		c.setParentField("fieldPlane");
		c.setChild2Field("field230");
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		c = (TablePerClassChild2) em.find(TablePerClassChild2.class, c.getId());
		assertNotNull(c);

		TablePerClassParent p = (TablePerClassParent) em.find(
				TablePerClassChild2.class, c.getId());
		assertNotNull(p);
	}
}
