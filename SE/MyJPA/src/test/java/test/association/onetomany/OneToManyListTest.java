package test.association.onetomany;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.junit.Test;

import util.JPAUtil;
import association.onetomany.list.ManyList;
import association.onetomany.list.OneList;

public class OneToManyListTest {

	private void create(OneList[] oneArray, ManyList[] manyMapArray) {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		OneList one = new OneList();

		ManyList many = new ManyList();
		many.setManyField("Number 1");
		many.setOne(one);
		one.getMany().add(many);
		em.persist(many);

		many = new ManyList();
		many.setManyField("Number 2");
		many.setOne(one);
		one.getMany().add(many);
		em.persist(many);

		em.persist(one);
		em.getTransaction().commit();

		oneArray[0] = one;
		manyMapArray[0] = many;
	}

	@Test
	public void testList() {
		OneList[] oneArray = new OneList[1];
		ManyList[] manyMapArray = new ManyList[1];
		create(oneArray, manyMapArray);
		OneList one = oneArray[0];
		ManyList many = manyMapArray[0];

		EntityManager em = JPAUtil.getEm();
		one = (OneList) em.find(OneList.class, one.getId());
		assertNotNull(one);
		List<String> valuesList = Arrays.asList(new String[] { "Number 1",
				"Number 2" });
		for (ManyList manySet : one.getMany()) {
			valuesList.contains(manySet.getManyField());
		}

		many = (ManyList) em.find(ManyList.class, many.getId());
		assertNotNull(many);
		assertEquals(one.getId(), many.getOne().getId());
	}

	@Test
	public void testDelete() {
		OneList[] oneArray = new OneList[1];
		ManyList[] manyMapArray = new ManyList[1];
		create(oneArray, manyMapArray);
		OneList one = oneArray[0];

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		one = (OneList) em.find(OneList.class, one.getId());
		em.remove(one);
		for (ManyList manyMap : one.getMany()) {
			em.remove(manyMap);
		}
		em.getTransaction().commit();
	}

	@Test
	public void testDeleteA() {
		OneList[] oneArray = new OneList[1];
		ManyList[] manyMapArray = new ManyList[1];
		create(oneArray, manyMapArray);
		OneList one = oneArray[0];

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		one = (OneList) em.find(OneList.class, one.getId());
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
		OneList[] oneArray = new OneList[1];
		ManyList[] manyMapArray = new ManyList[1];
		create(oneArray, manyMapArray);
		OneList one = oneArray[0];

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		one = (OneList) em.find(OneList.class, one.getId());
		for (ManyList manyMap : one.getMany()) {
			em.remove(manyMap);
		}
		em.getTransaction().commit();
	}
}
