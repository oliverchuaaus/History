package test.association.onetoone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.junit.Ignore;
import org.junit.Test;

import util.JPAUtil;
import association.onetoone.jointable.EntityX;
import association.onetoone.jointable.EntityY;

public class OneToOneJoinedTableTest {
	@Test
	public void testJoinedTable() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityX entityX = new EntityX();
		EntityY entityY = new EntityY();
		entityX.setEntityY(entityY);
		entityY.setEntityX(entityX);
		em.persist(entityX);
		em.persist(entityY);
		em.getTransaction().commit();

		entityX = (EntityX) em.find(EntityX.class, entityX.getId());
		assertNotNull(entityX);
		assertEquals(entityX.getEntityY().getId(), entityY.getId());
	}

	@Test
	public void testJoinedTableNullY() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityX entityX = new EntityX();
		em.persist(entityX);
		em.getTransaction().commit();

		entityX = (EntityX) em.find(EntityX.class, entityX.getId());
		assertNotNull(entityX);
	}

	@Test
	@Ignore
	//TODO Fix test
	public void testBadJoinedTableNullX() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityY entityY = new EntityY();
		try {
			em.persist(entityY);
			em.getTransaction().commit();
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testUpdateAssociationX() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityX entityX = new EntityX();
		EntityY entityY = new EntityY();
		entityX.setEntityY(entityY);
		entityY.setEntityX(entityX);
		em.persist(entityX);
		em.persist(entityY);
		em.getTransaction().commit();

		em.getTransaction().begin();
		entityX = (EntityX) em.find(EntityX.class, entityX.getId());
		EntityY entityY2 = new EntityY();
		entityX.setEntityY(entityY2);
		entityY2.setEntityX(entityX);
		em.persist(entityX);
		em.persist(entityY2);
		em.getTransaction().commit();

		entityX = (EntityX) em.find(EntityX.class, entityX.getId());
		assertNotNull(entityX);
		assertEquals(entityX.getEntityY().getId(), entityY2.getId());
	}

	@Test
	public void testUpdateAssociationY() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityX entityX = new EntityX();
		EntityY entityY = new EntityY();
		entityX.setEntityY(entityY);
		entityY.setEntityX(entityX);
		em.persist(entityX);
		em.persist(entityY);
		em.getTransaction().commit();

		em.getTransaction().begin();
		entityY = (EntityY) em.find(EntityY.class, entityY.getId());
		EntityX entityX2 = new EntityX();
		entityY.setEntityX(entityX2);
		em.persist(entityX2);
		em.persist(entityY);
		em.getTransaction().commit();

		entityY = (EntityY) em.find(EntityY.class, entityY.getId());
		assertNotNull(entityY);
		assertEquals(entityY.getEntityX().getId(), entityX2.getId());
	}

	@Test
	public void testDeleteAssociationX() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityX entityX = new EntityX();
		EntityY entityY = new EntityY();
		entityX.setEntityY(entityY);
		entityY.setEntityX(entityX);
		em.persist(entityX);
		em.persist(entityY);
		em.getTransaction().commit();

		em.getTransaction().begin();
		entityX = (EntityX) em.find(EntityX.class, entityX.getId());
		entityX.setEntityY(null);
		em.persist(entityX);
		em.getTransaction().commit();

		entityX = (EntityX) em.find(EntityX.class, entityX.getId());
		assertNotNull(entityX);
		assertNull(entityX.getEntityY());
	}

	@Test
	public void testDeleteAssociationY() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityX entityX = new EntityX();
		EntityY entityY = new EntityY();
		entityX.setEntityY(entityY);
		entityY.setEntityX(entityX);
		em.persist(entityX);
		em.persist(entityY);
		em.getTransaction().commit();

		em.getTransaction().begin();
		entityX = (EntityX) em.find(EntityX.class, entityX.getId());
		entityY = entityX.getEntityY();

		try {
			em.remove(entityY);
			em.getTransaction().commit();
			fail();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testDelete() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityX entityX = new EntityX();
		EntityY entityY = new EntityY();
		entityX.setEntityY(entityY);
		entityY.setEntityX(entityX);
		em.persist(entityX);
		em.persist(entityY);
		em.getTransaction().commit();

		em.getTransaction().begin();
		entityX = (EntityX) em.find(EntityX.class, entityX.getId());
		// order is important
		em.remove(entityY);
		em.remove(entityX);
		em.getTransaction().commit();

		entityX = (EntityX) em.find(EntityX.class, entityX.getId());
		assertNull(entityX);

		entityY = (EntityY) em.find(EntityY.class, entityY.getId());
		assertNull(entityY);
	}

	@Test
	public void testDeleteX() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityX entityX = new EntityX();
		EntityY entityY = new EntityY();
		entityX.setEntityY(entityY);
		entityY.setEntityX(entityX);
		em.persist(entityX);
		em.persist(entityY);
		em.getTransaction().commit();

		em.getTransaction().begin();
		entityX = (EntityX) em.find(EntityX.class, entityX.getId());
		em.remove(entityX);
		em.getTransaction().commit();

		entityX = (EntityX) em.find(EntityX.class, entityX.getId());
		assertNull(entityX);

		entityY = (EntityY) em.find(EntityY.class, entityY.getId());
		assertNotNull(entityY);
	}

	@Test
	public void testDeleteY() {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		EntityX entityX = new EntityX();
		EntityY entityY = new EntityY();
		entityX.setEntityY(entityY);
		entityY.setEntityX(entityX);
		em.persist(entityX);
		em.persist(entityY);
		em.getTransaction().commit();

		em.getTransaction().begin();
		entityY = (EntityY) em.find(EntityY.class, entityY.getId());
		entityX = (EntityX) em.find(EntityX.class, entityX.getId());
		em.remove(entityY);
		entityX.setEntityY(null);
		em.persist(entityX);
		em.getTransaction().commit();

		entityX = (EntityX) em.find(EntityX.class, entityX.getId());
		assertNotNull(entityX);

		entityY = (EntityY) em.find(EntityY.class, entityY.getId());
		assertNull(entityY);
	}

}
