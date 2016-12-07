package test.individual;

import static org.junit.Assert.assertNotNull;
import individual.embedabble.EntityEmbedabble;

import javax.persistence.EntityManager;

import org.junit.Test;

import util.JPAUtil;

public class EmbedabbleTest {
	@Test
	public void testEmbedded() {
		EntityEmbedabble e1 = new EntityEmbedabble();

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(e1);
		em.getTransaction().commit();
		e1 = (EntityEmbedabble) em.find(EntityEmbedabble.class, e1.getId());
		assertNotNull(e1);
	}

}
