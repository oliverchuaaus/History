package test.individual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import individual.simple.GenderEnum;
import individual.simple.SimpleEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.junit.Test;

import util.JPAUtil;

public class SimpleEntityTest {
	private static final String TEXT = "Four scores and 7 years ago";

	@Test
	public void testEmpty() {
		SimpleEntity e1 = new SimpleEntity();

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(e1);
		em.getTransaction().commit();
		e1 = (SimpleEntity) em.find(SimpleEntity.class, e1.getId());
		assertNotNull(e1);
	}

	@Test
	public void testStringLength() {
		SimpleEntity e1 = new SimpleEntity();
		e1.setStringFieldLength("1234567");

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		try {
			em.persist(e1);
			em.getTransaction().commit();
			fail();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testDate() {
		SimpleEntity e1 = new SimpleEntity();

		Calendar cal = Calendar.getInstance();
		// 20 June 1977 6:28:10 pm
		cal.set(1977, 5, 20, 18, 28, 10);
		Date dateField = cal.getTime();
		e1.setDateField(dateField);
		Date timeField = new java.sql.Time(cal.getTimeInMillis());
		e1.setTimeField(timeField);
		e1.setTimeStampField(cal);
		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(e1);
		em.getTransaction().commit();

		e1 = (SimpleEntity) em.find(SimpleEntity.class, e1.getId());
		assertNotNull(e1);

		Calendar cal2 = e1.getTimeStampField();
		assertEquals(cal, cal2);
		Date dateField2 = e1.getDateField();
		assertEquals(dateField, dateField2);
		Date timeField2 = e1.getTimeField();
		assertEquals(timeField, timeField2);
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String timeFieldString2 = sdf.format(timeField2);
		assertEquals("182810", timeFieldString2);
	}

	@Test
	public void testEnum() {
		SimpleEntity e1 = new SimpleEntity();
		e1.setEnumOrdinalField(GenderEnum.FEMALE);
		e1.setEnumStringField(GenderEnum.FEMALE);

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(e1);
		em.getTransaction().commit();
		e1 = (SimpleEntity) em.find(SimpleEntity.class, e1.getId());
		assertNotNull(e1);

		assertEquals(GenderEnum.FEMALE, e1.getEnumOrdinalField());
		assertEquals(GenderEnum.FEMALE, e1.getEnumStringField());
	}

	@Test
	public void testLobs() {
		SimpleEntity e1 = new SimpleEntity();
		e1.setBlobField(TEXT.getBytes());
		e1.setClobField(TEXT);

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(e1);
		em.getTransaction().commit();
		e1 = (SimpleEntity) em.find(SimpleEntity.class, e1.getId());
		assertNotNull(e1);

		assertEquals(TEXT, e1.getClobField());
		assertEquals(TEXT, new String(e1.getBlobField()));
	}

	@Test
	public void testBigDecimalInteger() {
		SimpleEntity e1 = new SimpleEntity();
		e1.setBigIntegerField(new BigInteger("9"));
		e1.setBigDecimalField(new BigDecimal(1.23));

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(e1);

		e1 = (SimpleEntity) em.find(SimpleEntity.class, e1.getId());
		assertNotNull(e1);

		assertEquals(new BigInteger("9"), e1.getBigIntegerField());
		assertEquals(new BigDecimal(1.23), e1.getBigDecimalField());
	}

	@Test
	public void testBadBigIntegerScalePrecision() {
		SimpleEntity e1 = new SimpleEntity();
		// exceeds (precision = 1, scale = 0) 9 is fine
		e1.setBigIntegerField(new BigInteger("99"));

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(e1);

		try {
			em.getTransaction().commit();
			fail();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testBadBigDecimalScalePrecision() {
		SimpleEntity e1 = new SimpleEntity();
		// exceeds (precision = 3, scale = 2) 12.2 is fine.
		e1.setBigDecimalField(new BigDecimal(12.23));

		EntityManager em = JPAUtil.getEm();
		em.getTransaction().begin();
		em.persist(e1);

		try {
			em.getTransaction().commit();
			fail();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			fail();
		}
	}
}
