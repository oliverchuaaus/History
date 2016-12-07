package com.tougher.prattle.domain;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PrattleIT {
	private static EntityManager em;
	private static ClassPathXmlApplicationContext context;
	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		context = new ClassPathXmlApplicationContext(
				"test-domain-jpa-context.xml");
		context.refresh();
		EntityManagerFactory emf = (EntityManagerFactory) context
				.getBean("emf");
		em = emf.createEntityManager();

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

	}

	@AfterClass
	public static void tearDown() {
		context.close();
	}

	@Test
	public void testPrattle() {
		Prattler prattler1 = new Prattler();
		prattler1.setUsername("username");
		prattler1.setPassword("password");
		prattler1.setFullName("fullname");
		prattler1.setEmail("email@email.com");
		em.getTransaction().begin();
		em.persist(prattler1);
		em.getTransaction().commit();

		Prattle prattle1 = new Prattle();
		prattle1.setPrattler(prattler1);
		em.getTransaction().begin();
		em.persist(prattle1);
		em.getTransaction().commit();

		Prattle prattle2 = em.find(Prattle.class, prattle1.getId());
		assertEquals(prattle1, prattle2);
	}

	@Test
	public void testPrattleValidation() {
		Prattle prattle = new Prattle();
		Set<ConstraintViolation<Prattle>> violations;

		violations = validator.validateProperty(prattle, "prattler");
		assertEquals(1, violations.size());
		assertEquals("may not be null", violations.iterator().next()
				.getMessage());

	}

}
