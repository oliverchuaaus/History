package test.inheritance;

import static org.junit.Assert.assertNotNull;
import inheritance.MappedSuperClassEntity;

import javax.persistence.EntityManager;

import org.junit.Test;

import util.JPAUtil;

public class MappedSuperClassTest {
	@Test
	public void testOrder() {
		MappedSuperClassEntity o = new MappedSuperClassEntity();
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
		o = (MappedSuperClassEntity) em.find(MappedSuperClassEntity.class,
				o.getId());
		assertNotNull(o);
	}
}
