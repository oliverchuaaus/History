package com.tougher.prattle.persistence;

import java.util.List;

import com.tougher.prattle.domain.Prattler;

public interface PrattlerDao {
	void addPrattler(Prattler prattler);

	void savePrattler(Prattler prattler);

	Prattler getPrattlerById(long id);

	Prattler getPrattlerByUsername(String username);

	List<Prattler> findAllPrattlers();

}
