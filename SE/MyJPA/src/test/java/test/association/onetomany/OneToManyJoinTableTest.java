package test.association.onetomany;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.Test;

import util.JPAUtil;
import association.onetomany.jointable.ManyJoinTable;
import association.onetomany.jointable.OneJoinTable;

public class OneToManyJoinTableTest  {
	private void create(OneJoinTable[] oneArray, ManyJoinTable[] manyMapArray) {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		OneJoinTable one = new OneJoinTable();

		ManyJoinTable many = new ManyJoinTable();
		many.setManyField("Number 1");
		many.setOne(one);
		one.getMany().add(many);
		em.persist(many);

		many = new ManyJoinTable();
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
		OneJoinTable[] oneArray = new OneJoinTable[1];
		ManyJoinTable[] manyMapArray = new ManyJoinTable[1];
		create(oneArray, manyMapArray);
		OneJoinTable one = oneArray[0];
		ManyJoinTable many = manyMapArray[0];

		EntityManager em = JPAUtil.getEm();
		one = (OneJoinTable) em.find(OneJoinTable.class, one.getId());
		assertNotNull(one);
		Set<ManyJoinTable> setOfMany = one.getMany();
		assertEquals(2, setOfMany.size());
		List<String> valuesList = Arrays.asList(new String[] { "Number 1",
				"Number 2" });
		for (ManyJoinTable ManyJoinTable : setOfMany) {
			valuesList.contains(ManyJoinTable.getManyField());
		}

		many = (ManyJoinTable) em.find(ManyJoinTable.class, many.getId());
		assertNotNull(many);
		assertEquals(one.getId(), many.getOne().getId());
	}
}
