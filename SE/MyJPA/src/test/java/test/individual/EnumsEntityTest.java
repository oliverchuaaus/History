package test.individual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import individual.simple.enums.EnumEntity;
import individual.simple.enums.MaritalStatusEnum;

import javax.persistence.EntityManager;

import org.junit.Test;

import util.JPAUtil;
public class EnumsEntityTest  {
	@Test
	public void testEnum() {
		EnumEntity e1 = new EnumEntity();
		e1.setMaritalStatusEnum(MaritalStatusEnum.MARRIED);

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(e1);
		em.getTransaction().commit();
		e1 = (EnumEntity) em.find(EnumEntity.class, e1.getId());
		assertNotNull(e1);

		assertEquals(MaritalStatusEnum.MARRIED, e1.getMaritalStatusEnum());
	}

}
