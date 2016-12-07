package test.association.onetomany;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.Test;

import util.JPAUtil;
import association.onetomany.aggregate.ManyAggregate;
import association.onetomany.aggregate.OneAggregate;

public class OneToManyAggregateTest {
	private void create(OneAggregate[] oneArray, ManyAggregate[] manyMapArray) {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		OneAggregate one = new OneAggregate();

		ManyAggregate many = new ManyAggregate();
		many.setManyField("Number 1");
		many.setOne(one);
		one.getMany().add(many);
		em.persist(many);

		many = new ManyAggregate();
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
		OneAggregate[] oneArray = new OneAggregate[1];
		ManyAggregate[] manyMapArray = new ManyAggregate[1];
		create(oneArray, manyMapArray);
		OneAggregate one = oneArray[0];
		ManyAggregate many = manyMapArray[0];

		EntityManager em = JPAUtil.getEm();
		one = (OneAggregate) em.find(OneAggregate.class, one.getId());
		assertNotNull(one);
		Set<ManyAggregate> setOfMany = one.getMany();
		assertEquals(2, setOfMany.size());
		List<String> valuesList = Arrays.asList(new String[] { "Number 1",
				"Number 2" });
		for (ManyAggregate ManyAggregate : setOfMany) {
			valuesList.contains(ManyAggregate.getManyField());
		}

		many = (ManyAggregate) em.find(ManyAggregate.class, many.getId());
		assertNotNull(many);
		assertEquals(one.getId(), many.getOne().getId());
	}
}
