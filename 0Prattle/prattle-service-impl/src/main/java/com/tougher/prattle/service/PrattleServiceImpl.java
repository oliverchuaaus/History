package com.tougher.prattle.service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tougher.prattle.domain.Prattle;
import com.tougher.prattle.domain.Prattler;
import com.tougher.prattle.persistence.PrattleDao;
import com.tougher.prattle.persistence.PrattlerDao;

@Service("prattleService")
@Transactional
public class PrattleServiceImpl implements PrattleService {
	@Autowired
	private PrattleDao dao;
	@Autowired
	private PrattlerDao prattlerDao;

	@Override
	public void savePrattle(Prattle prattle) {
		prattle.setPostedDate(new Timestamp(System.currentTimeMillis()));
		dao.savePrattle(prattle);
	}

	@Override
	public void deletePrattle(long id) {
		dao.deletePrattle(id);
	}

	@Override
	public Prattle getPrattleById(long id) {
		return dao.getPrattleById(id);
	}

	@Override
	public List<Prattle> getPrattlesForPrattler(Prattler prattler) {
		return dao.getPrattlesForPrattler(prattler);
	}

	@Override
	public List<Prattle> getPrattlesForPrattler(String username) {
		Prattler platter = prattlerDao.getPrattlerByUsername(username);
		return dao.getPrattlesForPrattler(platter);
	}

	@Override
	public List<Prattle> getRecentPrattles(int count) {
		List<Prattle> recentPrattles = dao.getRecentPrattle();
		Collections.reverse(recentPrattles);
		return recentPrattles;
	}

}
