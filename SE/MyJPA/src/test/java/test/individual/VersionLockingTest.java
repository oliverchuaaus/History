package test.individual;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import individual.simple.SimpleEntity;
import individual.simple.locking.LockingEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.junit.Test;

import util.JPAUtil;

public class VersionLockingTest  {
	@Test
	public void testNoOptimisticLocking() {
		SimpleEntity e1 = new SimpleEntity();
		e1.setStringField("string 1");

		EntityManager em = JPAUtil.getEm();
		// txn to create entity
		em.getTransaction().begin();
		em.persist(e1);
		em.getTransaction().commit();

		SimpleEntity e2 = (SimpleEntity) em
				.find(SimpleEntity.class, e1.getId());
		assertNotNull(e2);
		e2.setStringField("string 2");

		EntityManager em2 = JPAUtil.getEm();
		SimpleEntity e3 = (SimpleEntity) em2.find(SimpleEntity.class,
				e1.getId());
		assertNotNull(e3);

		assertNotSame(em2, em);

		// 1st txn to update
		em.getTransaction().begin();
		em.persist(e2);
		em.getTransaction().commit();

		// 2nd txn to update
		e3.setStringField("string 3");
		em2.getTransaction().begin();
		em2.persist(e3);
		try {
			em2.getTransaction().commit();
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	public void testOptimisticLocking() {
		LockingEntity e1 = new LockingEntity();
		e1.setStringField("string 1");

		EntityManager em = JPAUtil.getEm();
		// txn to create entity
		em.getTransaction().begin();
		em.persist(e1);
		// version = 0
		em.getTransaction().commit();

		LockingEntity e2 = (LockingEntity) em.find(LockingEntity.class,
				e1.getId());
		assertNotNull(e2);
		e2.setStringField("string 2");

		EntityManager em2 = JPAUtil.getEm();
		LockingEntity e3 = (LockingEntity) em2.find(LockingEntity.class,
				e1.getId());
		assertNotNull(e3);

		assertNotSame(em2, em);

		// 1st txn to update
		em.getTransaction().begin();
		em.persist(e2);
		// version = 1
		em.getTransaction().commit();

		// 2nd txn to update
		e3.setStringField("string 3");
		em2.getTransaction().begin();
		em2.persist(e3);
		try {
			// version = 1
			// update TABLE set stringField=?, version=1 where id=? and
			// version=0
			em2.getTransaction().commit();
			fail();
		} catch (PersistenceException e) {
			e.printStackTrace();
			assertTrue(e
					.getCause()
					.getMessage()
					.contains(
							"Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect)"));
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	public void testOptimisticLockingDeletedFirst() {
		LockingEntity e1 = new LockingEntity();
		e1.setStringField("string 1");

		EntityManager em = JPAUtil.getEm();
		// txn to create entity
		em.getTransaction().begin();
		em.persist(e1);
		// version = 0
		em.getTransaction().commit();

		LockingEntity e2 = (LockingEntity) em.find(LockingEntity.class,
				e1.getId());
		assertNotNull(e2);
		e2.setStringField("string 2");

		EntityManager em2 = JPAUtil.getEm();
		LockingEntity e3 = (LockingEntity) em2.find(LockingEntity.class,
				e1.getId());
		assertNotNull(e3);

		assertNotSame(em2, em);

		// 1st txn to delete
		em.getTransaction().begin();
		em.remove(e2);
		// version = 1
		em.getTransaction().commit();

		// 2nd txn to update
		e3.setStringField("string 3");
		em2.getTransaction().begin();
		em2.persist(e3);
		try {
			// version = 1
			em2.getTransaction().commit();
			fail();
		} catch (PersistenceException e) {
			e.printStackTrace();
			assertTrue(e
					.getCause()
					.getMessage()
					.contains(
							"Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect)"));
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	public void testOptimisticLockingDeletedLast() {
		LockingEntity e1 = new LockingEntity();
		e1.setStringField("string 1");

		EntityManager em = JPAUtil.getEm();
		// txn to create entity
		em.getTransaction().begin();
		em.persist(e1);
		// version = 0
		em.getTransaction().commit();

		LockingEntity e2 = (LockingEntity) em.find(LockingEntity.class,
				e1.getId());
		assertNotNull(e2);
		e2.setStringField("string 2");

		EntityManager em2 = JPAUtil.getEm();
		LockingEntity e3 = (LockingEntity) em2.find(LockingEntity.class,
				e1.getId());
		assertNotNull(e3);

		assertNotSame(em2, em);

		// 1st txn to update
		em.getTransaction().begin();
		em.merge(e2);
		// version = 1
		em.getTransaction().commit();

		// 2nd txn to update
		e3.setStringField("string 3");
		em2.getTransaction().begin();
		em2.remove(e3);
		try {
			// version = 1
			em2.getTransaction().commit();
			fail();
		} catch (PersistenceException e) {
			e.printStackTrace();
			assertTrue(e
					.getCause()
					.getMessage()
					.contains(
							"Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect)"));
		} catch (Exception e) {
			fail();
		}
	}
}
