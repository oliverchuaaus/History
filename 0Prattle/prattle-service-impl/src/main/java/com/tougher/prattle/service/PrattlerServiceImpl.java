package com.tougher.prattle.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tougher.prattle.domain.Prattler;
import com.tougher.prattle.persistence.PrattlerDao;

@Service("prattlerService")
@Transactional
public class PrattlerServiceImpl implements PrattlerService {
	@Autowired
	private PrattlerDao dao;

	@Override
	public void savePrattler(Prattler prattler) {
		if (prattler.getId() == null) {
			dao.addPrattler(prattler);
		} else {
			dao.savePrattler(prattler);
		}
	}
	
	@Override
	public Prattler getPrattler(long id) {
		return dao.getPrattlerById(id);
	}

	@Override
	public Prattler getPrattler(String username) {
		return dao.getPrattlerByUsername(username);
	}

	@Override
	public List<Prattler> getAllPrattlers() {
		return dao.findAllPrattlers();
	}

}
