package com.tougher.prattle.service;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;

import org.junit.Before;
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
@ContextConfiguration(locations = { "classpath:test-domain-jpa-context.xml",
		"classpath:test-service-impl-context.xml" })
@TransactionConfiguration(transactionManager = "testTxnMgr", defaultRollback = true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
public class PrattlerServiceIT {
	@Autowired
	private PrattlerService prattlerService;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private Prattler newPrattler;
	private Prattler oldPrattler;

	@Before
	public void cleanup() {
		deleteFromTables(jdbcTemplate, "Prattle");
		deleteFromTables(jdbcTemplate, "Prattler");
	}

	@Test
	public void shouldAddPrattler() {
		newPrattler = new Prattler();
		newPrattler.setUsername("testuser");
		newPrattler.setPassword("password");
		newPrattler.setFullName("Michael McTest");
		newPrattler.setEmail("mike@mail.com");

		assertEquals(0, countRowsInTable(jdbcTemplate, "Prattler"));
		prattlerService.savePrattler(newPrattler);
		assertEquals(1, countRowsInTable(jdbcTemplate, "Prattler"));

		oldPrattler = new Prattler();
		oldPrattler.setId(newPrattler.getId());
		oldPrattler.setUsername("olduser");
		oldPrattler.setPassword("letmein");
		oldPrattler.setFullName("Bob O'Test");
		oldPrattler.setEmail("bob@mail.com");

		prattlerService.savePrattler(oldPrattler);
		assertEquals(1, countRowsInTable(jdbcTemplate, "Prattler"));

	}

}
