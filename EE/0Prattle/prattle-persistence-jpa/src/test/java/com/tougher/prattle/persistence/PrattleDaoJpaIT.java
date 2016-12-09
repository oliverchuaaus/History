package com.tougher.prattle.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;

import java.sql.Timestamp;

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

import com.tougher.prattle.domain.Prattle;
import com.tougher.prattle.domain.Prattler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:test-persistence-jpa-context.xml",
		"classpath*:test-domain-jpa-context.xml" })
@TransactionConfiguration(transactionManager = "testTxnMgr", defaultRollback = true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
public class PrattleDaoJpaIT {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PrattlerDao prattlerDao;

	@Autowired
	private PrattleDao dao;

	@After
	public void cleanup() {
		deleteFromTables(jdbcTemplate, "Prattle");
	}

	@Test
	public void shouldCreateRowsAndSetIds() {
		Prattler prattler = insertPrattler("username", "password", "fullname",
				"email@mail.com");

		assertEquals(0, countRowsInTable(jdbcTemplate, "Prattle"));

		insertPrattle(prattler, "1st Message");
		assertEquals(1, countRowsInTable(jdbcTemplate, "Prattle"));

		insertPrattle(prattler, "2nd Message");
		assertEquals(2, countRowsInTable(jdbcTemplate, "Prattle"));
	}

	@Test
	public void shouldBeAbleToFindInsertedSpitter() {
		Prattler prattler = insertPrattler("username", "password", "fullname",
				"email@mail.com");

		Prattle prattleIn = insertPrattle(prattler, "my message");

		Prattle prattleOut = dao.getPrattleById(prattleIn.getId());

		// TODO EqualsBuilder fails on Timestamp comparison, any solution?
		assertEquals(prattleIn.getId(), prattleOut.getId());
	}

	private Prattler insertPrattler(String username, String password,
			String fullname, String email) {
		Prattler spitter = new Prattler();
		spitter.setUsername(username);
		spitter.setPassword(password);
		spitter.setFullName(fullname);
		spitter.setEmail(email);
		assertNull(spitter.getId());
		prattlerDao.addPrattler(spitter);
		assertNotNull(spitter.getId());
		return spitter;
	}

	private Prattle insertPrattle(Prattler prattler, String text) {
		Prattle spitter = new Prattle();
		spitter.setPrattler(prattler);
		spitter.setText(text);
		spitter.setPostedDate(new Timestamp(System.currentTimeMillis()));
		assertNull(spitter.getId());
		dao.savePrattle(spitter);
		assertNotNull(spitter.getId());
		return spitter;
	}
}
