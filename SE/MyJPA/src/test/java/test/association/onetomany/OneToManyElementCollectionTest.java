package test.association.onetomany;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import util.JPAUtil;
import association.onetomany.elementcollection.ManyEmbeddableElementCollection;
import association.onetomany.elementcollection.OneElementCollection;
import association.onetomany.elementcollection.OneEmbeddableElementCollection;

public class OneToManyElementCollectionTest  {

	private void create(OneElementCollection[] oneArray) {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		OneElementCollection one = new OneElementCollection();
		one.getManyList().add("Number 1");
		one.getManyList().add("Number 2");
		em.persist(one);
		em.getTransaction().commit();

		oneArray[0] = one;
	}

	private void createEmbedded(OneEmbeddableElementCollection[] oneArray) {
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();

		OneEmbeddableElementCollection one = new OneEmbeddableElementCollection();
		ManyEmbeddableElementCollection many = new ManyEmbeddableElementCollection();
		many.setName("Number 1");
		one.getManyList().add(many);
		many = new ManyEmbeddableElementCollection();
		many.setName("Number 2");
		one.getManyList().add(many);

		em.persist(one);
		em.getTransaction().commit();

		oneArray[0] = one;
	}

	@Test
	public void testList() {
		OneElementCollection[] oneArray = new OneElementCollection[1];
		create(oneArray);
		OneElementCollection one = oneArray[0];

		EntityManager em = JPAUtil.getEm();
		one = (OneElementCollection) em.find(OneElementCollection.class,
				one.getId());
		assertNotNull(one);
		List<String> setOfMany = one.getManyList();
		assertEquals(2, setOfMany.size());
		List<String> valuesList = Arrays.asList(new String[] { "Number 1",
				"Number 2" });
		for (String string : setOfMany) {
			valuesList.contains(string);
		}
	}

	@Test
	public void testEmbeddedList() {
		OneEmbeddableElementCollection[] oneArray = new OneEmbeddableElementCollection[1];
		createEmbedded(oneArray);
		OneEmbeddableElementCollection one = oneArray[0];

		EntityManager em = JPAUtil.getEm();
		one = (OneEmbeddableElementCollection) em.find(
				OneEmbeddableElementCollection.class, one.getId());
		assertNotNull(one);
		List<ManyEmbeddableElementCollection> setOfMany = one.getManyList();
		assertEquals(2, setOfMany.size());
		List<String> valuesList = Arrays.asList(new String[] { "Number 1",
				"Number 2" });
		for (ManyEmbeddableElementCollection many : setOfMany) {
			valuesList.contains(many.getName());
		}
	}
}
