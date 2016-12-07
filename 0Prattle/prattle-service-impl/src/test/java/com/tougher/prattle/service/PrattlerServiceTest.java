package com.tougher.prattle.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tougher.prattle.domain.Prattler;
import com.tougher.prattle.persistence.PrattlerDao;

public class PrattlerServiceTest {
	@InjectMocks
	private PrattlerServiceImpl prattlerService;
	@Mock
	private PrattlerDao dao;
	private static Prattler prattler1;
	private static Prattler prattler2;
	private static List<Prattler> prattlerList;

	@BeforeClass
	public static void beforeClass() {
		prattler1 = new Prattler();
		prattler1.setId(1L);
		prattler1.setUsername("toffer");

		prattler2 = new Prattler();
		prattler2.setId(2L);
		prattler2.setUsername("kelvin");

		prattlerList = new ArrayList<Prattler>();
		prattlerList.add(prattler1);
		prattlerList.add(prattler2);
	}

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllPrattlers() {
		when(dao.findAllPrattlers()).thenReturn(prattlerList);
		List<Prattler> list = prattlerService.getAllPrattlers();
		assertEquals(2, list.size());
	}

	@Test
	public void testGetPrattlerById() {
		when(dao.getPrattlerById(1)).thenReturn(prattler1);
		when(dao.getPrattlerById(2)).thenReturn(prattler2);
		Prattler prattler = prattlerService.getPrattler(1);
		assertEquals(1L, prattler.getId().longValue());
		prattler = prattlerService.getPrattler(2);
		assertEquals(2L, prattler.getId().longValue());
	}

	@Test
	public void testGetPrattlerByUsername() {
		when(dao.getPrattlerByUsername("toffer")).thenReturn(prattler1);
		when(dao.getPrattlerByUsername("kelvin")).thenReturn(prattler2);
		Prattler prattler = prattlerService.getPrattler("toffer");
		assertEquals(1L, prattler.getId().longValue());
		prattler = prattlerService.getPrattler("kelvin");
		assertEquals(2L, prattler.getId().longValue());
	}

	@Test
	public void testSavePrattlerByUsername() {
		Prattler prattler = new Prattler();
		prattlerService.savePrattler(prattler);
		verify(dao).addPrattler(prattler);

		prattlerService.savePrattler(prattler1);
		verify(dao).savePrattler(prattler1);
	}
}
