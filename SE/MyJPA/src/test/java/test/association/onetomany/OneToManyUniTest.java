package test.association.onetomany;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.Test;

import util.JPAUtil;
import association.onetomany.uni.ManyUni;
import association.onetomany.uni.OneUni;

public class OneToManyUniTest {

	private void create(OneUni[] oneArray, ManyUni[] manyMapArray) {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		OneUni one = new OneUni();

		ManyUni many = new ManyUni();
		many.setManyField("Number 1");
		one.getMany().add(many);
		em.persist(many);

		many = new ManyUni();
		many.setManyField("Number 2");
		one.getMany().add(many);
		em.persist(many);

		em.persist(one);
		em.getTransaction().commit();

		oneArray[0] = one;
		manyMapArray[0] = many;
	}
	@Test
	public void testSet() {
		OneUni[] oneArray = new OneUni[1];
		ManyUni[] manyMapArray = new ManyUni[1];
		create(oneArray, manyMapArray);
		OneUni one = oneArray[0];
		ManyUni many = manyMapArray[0];

		EntityManager em = JPAUtil.getEm();
		one = (OneUni) em.find(OneUni.class, one.getId());
		assertNotNull(one);
		Set<ManyUni> setOfMany = one.getMany();
		assertEquals(2, setOfMany.size());
		List<String> valuesList = Arrays.asList(new String[] { "Number 1",
				"Number 2" });
		for (ManyUni ManyUni : setOfMany) {
			valuesList.contains(ManyUni.getManyField());
		}

		many = (ManyUni) em.find(ManyUni.class, many.getId());
		assertNotNull(many);
	}
}
