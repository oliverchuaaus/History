package com.tougher.prattle.service;

import java.util.List;

import com.tougher.prattle.domain.Prattle;
import com.tougher.prattle.domain.Prattler;

public interface PrattleService {

	void savePrattle(Prattle prattle);

	void deletePrattle(long id);

	Prattle getPrattleById(long id);

	List<Prattle> getPrattlesForPrattler(Prattler prattler);

	List<Prattle> getPrattlesForPrattler(String username);

	List<Prattle> getRecentPrattles(int count);

}
