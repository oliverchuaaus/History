package com.tougher.prattle.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tougher.prattle.domain.Prattle;
import com.tougher.prattle.domain.Prattler;

@Repository("prattleDao")
@Transactional
public class PrattleDaoJpa implements PrattleDao {
	private static final String PRATTlES_OF_PRATTLER = "SELECT p FROM Prattle p WHERE p.prattler = :prattler";
	private static final String RECENT_PRATTLE = "SELECT p FROM Prattle p";

	@PersistenceContext
	private EntityManager em;

	@Override
	public void savePrattle(Prattle prattle) {
		em.persist(prattle);
	}

	@Override
	public void deletePrattle(long id) {
		em.remove(getPrattleById(id));
	}

	@Override
	public Prattle getPrattleById(long id) {
		return em.find(Prattle.class, id);
	}

	@Override
	public List<Prattle> getPrattlesForPrattler(Prattler prattler) {
		return em.createQuery(PRATTlES_OF_PRATTLER, Prattle.class)
				.setParameter("prattler", prattler).getResultList();
	}

	@Override
	public List<Prattle> getRecentPrattle() {
		return em.createQuery(RECENT_PRATTLE, Prattle.class).getResultList();
	}

}
