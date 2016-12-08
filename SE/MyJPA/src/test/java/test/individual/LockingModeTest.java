package test.individual;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceException;

import org.junit.Test;

import individual.simple.locking.LockingEntity;
import individual.simple.locking.NonLockingEntity;
import util.JPAUtil;
/*
 * OPTIMISTIC - The Entity will have its optimistic lock version checked on commit, 
 * to ensure no other transaction updated the object.
 * OPTIMISTIC_FORCE_INCREMENT - The Entity will have its optimistic lock version incremented on commit, 
 * to ensure no other transaction updated (or READ locked) the object.
 * PESSIMISTIC_READ - The Entity is locked on the database, 
 * prevents any other transaction from acquiring a PESSIMISTIC_WRITE lock
 * PESSIMISTIC_WRITE - The Entity is locked on the database, 
 * prevents any other transaction from acquiring a PESSIMISTIC_READ or PESSIMISTIC_WRITE lock.
 * PESSIMISTIC_FORCE_INCREMENT - The Entity is locked on the database, 
 * prevents any other transaction from acquiring a PESSIMISTIC_READ or PESSIMISTIC_WRITE lock, 
 * and the Entity will have its optimistic lock version incremented on commit. 
 * This is unusual as it does both an optimistic and pessimistic lock, 
 * normally an application would only use one locking model.
 * 
 */
public class LockingModeTest  {

	// Entity with no version, hence no locking
	@Test
	public void testBadLocking() {
		NonLockingEntity e1 = new NonLockingEntity();
		e1.setStringField("string 1");

		EntityManager em = JPAUtil.getEm();
		// txn to create entity
		em.getTransaction().begin();
		em.persist(e1);
		// version = 0
		em.getTransaction().commit();

		NonLockingEntity e2 = (NonLockingEntity) em.find(
				NonLockingEntity.class, e1.getId());
		assertNotNull(e2);
		e2.setStringField("string 2");

		EntityManager em2 = JPAUtil.getEm();
		NonLockingEntity e3 = (NonLockingEntity) em2.find(
				NonLockingEntity.class, e1.getId());
		assertNotNull(e3);

		assertNotSame(em2, em);

		// 1st txn to update
		em.getTransaction().begin();
		em.refresh(e2);
		em.lock(e2, LockModeType.PESSIMISTIC_WRITE);
		em.persist(e2);
		// version = 1
		em.getTransaction().commit();

		// 2nd txn to update
		e3.setStringField("string 3");
		em2.getTransaction().begin();
		em2.refresh(e3);
		em2.lock(e3, LockModeType.PESSIMISTIC_WRITE);
		em2.persist(e3);
		try {
			// version = 1
			// update TABLE set stringField=?, version=1 where id=? and
			// version=0
			em2.getTransaction().commit();
			// non-locking entity cannot be locked
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

	// Optimistic locking, essentially does nothing. same as using version
	@Test
	public void testLockingOptimistic() {
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
		em2.getTransaction().begin();
		em2.lock(e3, LockModeType.OPTIMISTIC);
		e3.setStringField("string 3");
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

	// Optimistic locking, essentially does nothing. same as using version
	@Test
	public void testLockingOptimisticForceIncrement() {
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

		EntityManager em2 = JPAUtil.getEm();
		LockingEntity e3 = (LockingEntity) em2.find(LockingEntity.class,
				e1.getId());
		assertNotNull(e3);

		assertNotSame(em2, em);

		// 1st txn to update
		em.getTransaction().begin();
		em.lock(e2, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		// version = 1
		em.getTransaction().commit();

		// 2nd txn to update
		em2.getTransaction().begin();
		e3.setStringField("string 3");
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

	// Pessimistic Locking throws exception during lock method call when object
	// is stale
	@Test
	public void testLockingPessimisticRead() {
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
		em2.getTransaction().begin();
		try {
			// exception thrown during lock, even before commit
			em2.lock(e3, LockModeType.PESSIMISTIC_READ);
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

	// Pessimistic Locking throws exception during lock method call when object
	// is stale
	@Test
	public void testLockingPessimisticForceIncrement() {
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

		EntityManager em2 = JPAUtil.getEm();
		LockingEntity e3 = (LockingEntity) em2.find(LockingEntity.class,
				e1.getId());
		assertNotNull(e3);

		assertNotSame(em2, em);

		// 1st txn to update
		em.getTransaction().begin();
		em.lock(e2, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
		// version = 1
		em.getTransaction().commit();

		// 2nd txn to update
		em2.getTransaction().begin();
		try {
			// exception thrown during lock, even before commit
			em2.lock(e3, LockModeType.PESSIMISTIC_READ);
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

	// Pessimistic Locking throws exception during lock method call when object
	// is stale
	@Test
	public void testLockingPessimisticLockLoad() {
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
		em2.getTransaction().begin();
		LockingEntity e3 = (LockingEntity) em2.find(LockingEntity.class,
				e1.getId(), LockModeType.OPTIMISTIC);
		assertNotNull(e3);

		assertNotSame(em2, em);

		// 1st txn to update
		em.getTransaction().begin();
		em.persist(e2);
		// version = 1
		em.getTransaction().commit();

		// 2nd txn to update
		try {
			em2.getTransaction().commit();
			// exception thrown during lock, even before commit
			fail();
		} catch (PersistenceException e) {
			e.printStackTrace();
			assertTrue(e
					.getCause()
					.getMessage()
					.contains(
							"Newer version [1] of entity [[individual.simple.locking.LockingEntity"));
		} catch (Exception e) {
			fail();
		}
	}
}
