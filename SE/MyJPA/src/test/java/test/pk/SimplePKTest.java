package test.pk;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.junit.Test;

import pk.simple.EntityAutoPK;
import pk.simple.EntityManualPK;
import pk.simple.EntitySequencePK;
import util.JPAUtil;

public class SimplePKTest {

	@Test
	public void testAutoPK() {
		EntityAutoPK e1 = new EntityAutoPK();

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(e1);
		em.getTransaction().commit();
		e1 = (EntityAutoPK) em.find(EntityAutoPK.class, e1.getId());
		assertNotNull(e1);
	}

	@Test
	public void testManualPK() {
		EntityManualPK e1 = new EntityManualPK();
		e1.setId(100L);

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(e1);
		em.getTransaction().commit();
		e1 = (EntityManualPK) em.find(EntityManualPK.class, e1.getId());
		assertNotNull(e1);
	}

	@Test
	public void testBadManualPKNullPK() {
		EntityManualPK e1 = new EntityManualPK();

		EntityManager em = JPAUtil.getEm();
		try {
			em.getTransaction().begin();
			em.persist(e1);
			em.getTransaction().commit();
			fail();
		} catch (PersistenceException e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testBadManualPKDuplicatePK() {
		EntityManualPK e1 = new EntityManualPK();
		e1.setId(500L);

		EntityManager em = JPAUtil.getEm();

		em.getTransaction().begin();
		em.persist(e1);
		em.getTransaction().commit();

		e1 = new EntityManualPK();
		e1.setId(500L);
		try {
			em.getTransaction().begin();
			em.persist(e1);
			em.getTransaction().commit();
			fail();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testSequencePK() {
		EntitySequencePK e1 = new EntitySequencePK();

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(e1);
		em.getTransaction().commit();
		e1 = (EntitySequencePK) em.find(EntitySequencePK.class, e1.getId());
		assertNotNull(e1);
	}
}
