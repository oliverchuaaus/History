package com.tougher.prattle.service;

import java.util.List;

import com.tougher.prattle.domain.Prattler;

public interface PrattlerService {
	void savePrattler(Prattler prattler);

	Prattler getPrattler(long id);

	Prattler getPrattler(String username);

	List<Prattler> getAllPrattlers();

}
