package test.association.onetoone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.junit.Test;

import util.JPAUtil;
import association.onetoone.joincolumn.EntityE;
import association.onetoone.joincolumn.EntityF;

public class OneToOneJoinColumnTest {
	@Test
	public void testFK() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityE entityE = new EntityE();
		EntityF entityF = new EntityF();

		entityE.setEntityF(entityF);

		em.persist(entityE);
		em.persist(entityF);
		em.getTransaction().commit();

		entityE = (EntityE) em.find(EntityE.class, entityE.getId());
		assertNotNull(entityE);
		assertEquals(entityE.getEntityF().getId(), entityF.getId());
	}

	@Test
	public void testUpdateAssociationE() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityE entityE = new EntityE();
		EntityF entityF = new EntityF();

		entityE.setEntityF(entityF);

		em.persist(entityE);
		em.persist(entityF);
		em.getTransaction().commit();

		em.getTransaction().begin();
		EntityF entityF2 = new EntityF();
		entityE.setEntityF(entityF2);
		em.persist(entityE);
		em.persist(entityF2);
		em.getTransaction().commit();

		entityE = (EntityE) em.find(EntityE.class, entityE.getId());
		assertNotNull(entityE.getEntityF());
		assertEquals(entityE.getEntityF().getId(), entityF2.getId());
	}

	@Test
	public void testBadUpdateAssociationF() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityE entityE = new EntityE();
		EntityF entityF = new EntityF();

		entityE.setEntityF(entityF);

		em.persist(entityE);
		em.persist(entityF);
		em.getTransaction().commit();

		em.getTransaction().begin();
		EntityE entityE2 = new EntityE();
		// This update does not do anything, since field has mappedBy.
		entityF.setEntityE(entityE2);
		em.persist(entityE2);
		em.persist(entityF);
		em.getTransaction().commit();

		entityE2 = (EntityE) em.find(EntityE.class, entityE2.getId());
		assertNull(entityE2.getEntityF());
		entityE = (EntityE) em.find(EntityE.class, entityE.getId());
		assertNotNull(entityE.getEntityF());
	}

	@Test
	public void testDeleteAssociationE() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityE entityE = new EntityE();
		EntityF entityF = new EntityF();

		entityE.setEntityF(entityF);

		em.persist(entityE);
		em.persist(entityF);
		em.getTransaction().commit();

		em.getTransaction().begin();
		entityE.setEntityF(null);
		em.persist(entityE);
		em.getTransaction().commit();

		entityE = (EntityE) em.find(EntityE.class, entityE.getId());
		assertNull(entityE.getEntityF());

		entityF = (EntityF) em.find(EntityF.class, entityF.getId());
		assertNull(entityF.getEntityE());
	}

	@Test
	public void testBadDeleteAssociationF() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityE entityE = new EntityE();
		EntityF entityF = new EntityF();

		entityE.setEntityF(entityF);

		em.persist(entityE);
		em.persist(entityF);
		em.getTransaction().commit();

		em.getTransaction().begin();
		// This update does not do anything, since field has mappedBy.
		entityF.setEntityE(null);
		em.persist(entityF);
		em.getTransaction().commit();

		entityF = (EntityF) em.find(EntityF.class, entityF.getId());
		assertNull(entityF.getEntityE());

		entityE = (EntityE) em.find(EntityE.class, entityE.getId());
		assertNotNull(entityE.getEntityF());
	}

}
