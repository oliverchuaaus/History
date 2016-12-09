package com.tougher.prattle.domain;

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

import static org.junit.Assert.assertEquals;

public class PrattlerIT {
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
	public void testPrattler() {
		Prattler prattler1 = new Prattler();
		prattler1.setUsername("username");
		prattler1.setPassword("password");
		prattler1.setFullName("fullname");
		prattler1.setEmail("email@email.com");
		em.getTransaction().begin();
		em.persist(prattler1);
		em.getTransaction().commit();

		Prattler prattler2 = em.find(Prattler.class, prattler1.getId());
		assertEquals(prattler1, prattler2);

	}

	@Test
	public void testPrattlerValidation() {
		Prattler prattler = new Prattler();
		Set<ConstraintViolation<Prattler>> violations;

		violations = validator.validateProperty(prattler, "username");
		assertEquals(1, violations.size());
		assertEquals("may not be null", violations.iterator().next()
				.getMessage());

		prattler.setUsername("1 1");
		violations = validator.validateProperty(prattler, "username");
		assertEquals(1, violations.size());
		assertEquals("Username must be alphanumeric with no spaces", violations
				.iterator().next().getMessage());

		prattler.setUsername("aa");
		violations = validator.validateProperty(prattler, "username");
		assertEquals(1, violations.size());
		assertEquals("Username must be between 3 and 20 characters long.",
				violations.iterator().next().getMessage());

		// TODO Add more unit tests
		// TODO use advanced validation like groups, custom validations and i18n
		// message resources
	}

}
