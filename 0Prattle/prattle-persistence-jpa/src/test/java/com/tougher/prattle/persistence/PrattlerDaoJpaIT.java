package com.tougher.prattle.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.tougher.prattle.domain.Prattler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath:test-persistence-jpa-context.xml",
		"classpath:test-domain-jpa-context.xml" })
@TransactionConfiguration(transactionManager = "testTxnMgr", defaultRollback = true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
public class PrattlerDaoJpaIT {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PrattlerDao dao;

	@After
	public void cleanup() {
		deleteFromTables(jdbcTemplate, "Prattle");
		deleteFromTables(jdbcTemplate, "Prattler");
	}

	@Test
	public void shouldCreateRowsAndSetIds() {
		assertEquals(0, countRowsInTable(jdbcTemplate, "Prattler"));
		insertPrattler("username", "password", "fullname", "email@mail.com");

		assertEquals(1, countRowsInTable(jdbcTemplate, "Prattler"));

		insertPrattler("username2", "password2", "fullname2", "email@mail.com");
		assertEquals(2, countRowsInTable(jdbcTemplate, "Prattler"));
	}

	@Test
	public void shouldBeAbleToFindInsertedSpitter() {
		Prattler prattlerIn = insertPrattler("username", "password",
				"fullname", "email@mail.com");

		Prattler prattlerOut = dao.getPrattlerById(prattlerIn.getId());

		assertEquals(prattlerIn, prattlerOut);
	}

	private Prattler insertPrattler(String username, String password,
			String fullname, String email) {
		Prattler spitter = new Prattler();
		spitter.setUsername(username);
		spitter.setPassword(password);
		spitter.setFullName(fullname);
		spitter.setEmail(email);
		assertNull(spitter.getId());
		dao.addPrattler(spitter);
		assertNotNull(spitter.getId());
		return spitter;
	}
	
}
