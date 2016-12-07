package test.association.onetomany;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.junit.Test;

import util.JPAUtil;
import association.onetomany.set.ManySet;
import association.onetomany.set.OneSet;

public class OneToManySetTest {

	private void create(OneSet[] oneArray, ManySet[] manyMapArray) {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		OneSet one = new OneSet();

		ManySet many = new ManySet();
		many.setManyField("Number 1");
		many.setOne(one);
		one.getMany().add(many);
		em.persist(many);

		many = new ManySet();
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
	public void testSet() {
		OneSet[] oneArray = new OneSet[1];
		ManySet[] manyMapArray = new ManySet[1];
		create(oneArray, manyMapArray);
		OneSet one = oneArray[0];
		ManySet many = manyMapArray[0];

		EntityManager em = JPAUtil.getEm();
		one = (OneSet) em.find(OneSet.class, one.getId());
		assertNotNull(one);
		Set<ManySet> setOfMany = one.getMany();
		assertEquals(2, setOfMany.size());
		List<String> valuesList = Arrays.asList(new String[] { "Number 1",
				"Number 2" });
		for (ManySet manySet : setOfMany) {
			valuesList.contains(manySet.getManyField());
		}

		many = (ManySet) em.find(ManySet.class, many.getId());
		assertNotNull(many);
		assertEquals(one.getId(), many.getOne().getId());
	}

	@Test
	public void testDelete() {
		OneSet[] oneArray = new OneSet[1];
		ManySet[] manyMapArray = new ManySet[1];
		create(oneArray, manyMapArray);
		OneSet one = oneArray[0];

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		one = (OneSet) em.find(OneSet.class, one.getId());
		em.remove(one);
		for (ManySet manyMap : one.getMany()) {
			em.remove(manyMap);
		}
		em.getTransaction().commit();
	}

	@Test
	public void testDeleteA() {
		OneSet[] oneArray = new OneSet[1];
		ManySet[] manyMapArray = new ManySet[1];
		create(oneArray, manyMapArray);
		OneSet one = oneArray[0];

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		one = (OneSet) em.find(OneSet.class, one.getId());
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
		OneSet[] oneArray = new OneSet[1];
		ManySet[] manyMapArray = new ManySet[1];
		create(oneArray, manyMapArray);
		OneSet one = oneArray[0];

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		one = (OneSet) em.find(OneSet.class, one.getId());
		for (ManySet manyMap : one.getMany()) {
			em.remove(manyMap);
		}
		em.getTransaction().commit();
	}
}
