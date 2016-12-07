package test.association.onetoone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.junit.Test;

import util.JPAUtil;
import association.onetoone.sharedpk.EntityA;
import association.onetoone.sharedpk.EntityB;

public class OneToOneSharedPKTest {
	@Test
	public void testSharedPK() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityA entityA = new EntityA();
		EntityB entityB = new EntityB();
		entityA.setEntityB(entityB);
		em.persist(entityB);
		entityA.setIdA(entityB.getIdB());
		em.persist(entityA);

		em.getTransaction().commit();

		entityA = (EntityA) em.find(EntityA.class, entityA.getIdA());
		assertNotNull(entityA);
		assertEquals(entityA.getIdA(), entityB.getIdB());
	}

	@Test
	public void testUpdateA() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityA entityA = new EntityA();
		EntityB entityB = new EntityB();
		entityA.setEntityB(entityB);
		em.persist(entityB);
		entityA.setIdA(entityB.getIdB());
		em.persist(entityA);

		em.getTransaction().commit();

		em.getTransaction().begin();
		entityA = (EntityA) em.find(EntityA.class, entityA.getIdA());
		EntityB entityB2 = new EntityB();
		entityA.setEntityB(entityB2);
		entityB2.setIdB(entityA.getIdA());
		em.persist(entityA);
		em.getTransaction().commit();

		entityA = (EntityA) em.find(EntityA.class, entityA.getIdA());
		assertEquals(entityA.getEntityB().getIdB(), entityB2.getIdB());
	}

	@Test
	public void testUpdateB() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityA entityA = new EntityA();
		EntityB entityB = new EntityB();
		entityA.setEntityB(entityB);
		em.persist(entityB);
		entityA.setIdA(entityB.getIdB());
		em.persist(entityA);

		em.getTransaction().commit();

		em.getTransaction().begin();
		EntityA entityA2 = new EntityA();
		entityB.setEntityA(entityA2);
		em.persist(entityB);
		entityA2.setIdA(entityB.getIdB());

		try {
			em.persist(entityA2);
			fail();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testDeleteAssociationA() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityA entityA = new EntityA();
		EntityB entityB = new EntityB();
		entityA.setEntityB(entityB);
		em.persist(entityB);
		entityA.setIdA(entityB.getIdB());
		em.persist(entityA);

		em.getTransaction().commit();

		em.getTransaction().begin();
		entityA = (EntityA) em.find(EntityA.class, entityA.getIdA());
		entityA.setEntityB(null);
		em.getTransaction().commit();

		entityA = (EntityA) em.find(EntityA.class, entityA.getIdA());
		assertNull(entityA.getEntityB());
	}

	@Test
	public void testDeleteAssociationB() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityA entityA = new EntityA();
		EntityB entityB = new EntityB();
		entityA.setEntityB(entityB);
		em.persist(entityB);
		entityA.setIdA(entityB.getIdB());
		em.persist(entityA);

		em.getTransaction().commit();

		entityB = (EntityB) em.find(EntityB.class, entityB.getIdB());
		assertNull(entityB.getEntityA());
	}

	@Test
	public void testBadDeleteA() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityA entityA = new EntityA();
		EntityB entityB = new EntityB();
		entityA.setEntityB(entityB);
		em.persist(entityB);
		entityA.setIdA(entityB.getIdB());
		em.persist(entityA);

		em.getTransaction().commit();

		em.getTransaction().begin();
		entityA = (EntityA) em.find(EntityA.class, entityA.getIdA());
		em.remove(entityA);
		em.getTransaction().commit();

	}

	@Test
	public void testDeleteB() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityA entityA = new EntityA();
		EntityB entityB = new EntityB();
		entityA.setEntityB(entityB);
		em.persist(entityB);
		entityA.setIdA(entityB.getIdB());
		em.persist(entityA);

		em.getTransaction().commit();

		try {
			em.getTransaction().begin();
			entityA = (EntityA) em.find(EntityA.class, entityA.getIdA());
			em.remove(entityB);
			em.getTransaction().commit();
		} catch (Exception e) {
			fail();
		}
	}
}
