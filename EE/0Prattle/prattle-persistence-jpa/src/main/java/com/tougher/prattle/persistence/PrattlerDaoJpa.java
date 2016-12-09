package com.tougher.prattle.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tougher.prattle.domain.Prattler;

@Repository("prattlerDao")
@Transactional
public class PrattlerDaoJpa implements PrattlerDao {
	private static final String PRATTLER_BY_USERNAME = "SELECT p FROM Prattler p WHERE p.username = :username";
	private static final String ALL_SPITTERS = "SELECT prattler FROM Prattler prattler";
	@PersistenceContext
	private EntityManager em;

	@Override
	public void addPrattler(Prattler prattler) {
		em.persist(prattler);
	}

	@Override
	public void savePrattler(Prattler prattler) {
		em.merge(prattler);
	}

	@Override
	public Prattler getPrattlerById(long id) {
		return em.find(Prattler.class, id);
	}
	
	@Override
	public Prattler getPrattlerByUsername(String username) {
		return (Prattler) em.createQuery(PRATTLER_BY_USERNAME)
				.setParameter("username", username).getSingleResult();
	}

	@Override
	public List<Prattler> findAllPrattlers() {
		return em.createQuery(ALL_SPITTERS, Prattler.class).getResultList();
	}

}
