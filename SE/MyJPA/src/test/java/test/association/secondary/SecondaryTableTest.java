package test.association.secondary;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.junit.Test;

import util.JPAUtil;
import association.secondary.SecondaryTableEntity;

public class SecondaryTableTest {
	@Test
	public void testSimple() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		SecondaryTableEntity e1 = new SecondaryTableEntity();
		e1.setField("Field");
		e1.setStoryPart1("storyPart1");
		e1.setStoryPart2("storyPart2");
		em.persist(e1);
		em.getTransaction().commit();

		e1 = (SecondaryTableEntity) em.find(SecondaryTableEntity.class,
				e1.getId());
		assertNotNull(e1);
	}

	@Test
	public void testBadUniqueConstraint() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		SecondaryTableEntity e1 = new SecondaryTableEntity();
		e1.setField("Field");
		e1.setStoryPart1("storyPart11");
		e1.setStoryPart2("storyPart22");

		em.persist(e1);
		em.getTransaction().commit();

		e1 = new SecondaryTableEntity();
		e1.setField("Field");
		e1.setStoryPart1("storyPart11");
		e1.setStoryPart2("storyPart22");

		em.getTransaction().begin();
		try {
			em.persist(e1);
			em.getTransaction().commit();
			fail();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			fail();
		}
	}
}
