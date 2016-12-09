package com.tougher.prattle.persistence;

import java.util.List;

import com.tougher.prattle.domain.Prattle;
import com.tougher.prattle.domain.Prattler;

public interface PrattleDao {

	void savePrattle(Prattle prattle);

	void deletePrattle(long id);

	Prattle getPrattleById(long id);

	List<Prattle> getPrattlesForPrattler(Prattler prattler);

	List<Prattle> getRecentPrattle();

}
