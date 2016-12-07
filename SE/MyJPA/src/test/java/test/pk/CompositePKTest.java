package test.pk;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.Test;

import pk.composite.EmbeddedIdPK;
import pk.composite.EmbeddedIdPKEntity;
import pk.composite.IdClassPK;
import pk.composite.IdClassPKEntity;
import util.JPAUtil;

public class CompositePKTest  {
	@Test
	public void testEmbeddedIdPK() {
		EmbeddedIdPKEntity e1 = new EmbeddedIdPKEntity();
		EmbeddedIdPK pk = new EmbeddedIdPK();
		pk.setMobile(415L);
		pk.setPin(129L);
		e1.setId(pk);

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(e1);
		em.getTransaction().commit();
		e1 = (EmbeddedIdPKEntity) em.find(EmbeddedIdPKEntity.class, e1.getId());
		assertNotNull(e1);
	}
	@Test
	public void testIdClassPK() {
		IdClassPKEntity e1 = new IdClassPKEntity();
		e1.setMobile(415L);
		e1.setPin(129L);

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(e1);
		em.getTransaction().commit();

		IdClassPK pk = new IdClassPK();
		pk.setMobile(e1.getMobile());
		pk.setPin(e1.getPin());
		e1 = (IdClassPKEntity) em.find(IdClassPKEntity.class, pk);
		assertNotNull(e1);
	}

}
