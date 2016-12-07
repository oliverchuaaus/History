package test.association.onetomany;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.junit.Test;

import util.JPAUtil;
import association.onetomany.map.ManyMap;
import association.onetomany.map.OneMap;

public class OneToManyMapTest {

	private void create(OneMap[] oneArray, ManyMap[] manyMapArray) {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		OneMap one = new OneMap();

		ManyMap many = new ManyMap();
		many.setManyField("Number 1");
		many.setOne(one);
		one.getMany().put(many.getManyField(), many);
		em.persist(many);

		many = new ManyMap();
		many.setManyField("Number 2");
		many.setOne(one);
		one.getMany().put(many.getManyField(), many);
		em.persist(many);

		em.persist(one);
		em.getTransaction().commit();

		oneArray[0] = one;
		manyMapArray[0] = many;
	}

	@Test
	public void testMap() {
		OneMap[] oneArray = new OneMap[1];
		ManyMap[] manyMapArray = new ManyMap[1];
		create(oneArray, manyMapArray);
		OneMap one = oneArray[0];
		ManyMap many = manyMapArray[0];

		EntityManager em = JPAUtil.getEm();
		one = (OneMap) em.find(OneMap.class, one.getId());
		assertNotNull(one);
		assertEquals("Number 1", one.getMany().get("Number 1").getManyField());

		many = (ManyMap) em.find(ManyMap.class, many.getId());
		assertNotNull(many);
		assertEquals(one.getId(), many.getOne().getId());
	}

	@Test
	public void testDelete() {
		OneMap[] oneArray = new OneMap[1];
		ManyMap[] manyMapArray = new ManyMap[1];
		create(oneArray, manyMapArray);
		OneMap one = oneArray[0];

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		one = (OneMap) em.find(OneMap.class, one.getId());
		em.remove(one);
		for (ManyMap manyMap : one.getMany().values()) {
			em.remove(manyMap);
		}
		em.getTransaction().commit();
	}

	@Test
	public void testDeleteA() {
		OneMap[] oneArray = new OneMap[1];
		ManyMap[] manyMapArray = new ManyMap[1];
		create(oneArray, manyMapArray);
		OneMap one = oneArray[0];

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		one = (OneMap) em.find(OneMap.class, one.getId());
		try {
			em.remove(one);
			em.getTransaction().commit();
			fail();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testDeleteB() {
		OneMap[] oneArray = new OneMap[1];
		ManyMap[] manyMapArray = new ManyMap[1];
		create(oneArray, manyMapArray);
		OneMap one = oneArray[0];

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		one = (OneMap) em.find(OneMap.class, one.getId());
		for (ManyMap manyMap : one.getMany().values()) {
			em.remove(manyMap);
		}
		em.getTransaction().commit();
	}
}
