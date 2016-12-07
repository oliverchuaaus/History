package com.tougher.util;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractDAO<T, ID extends Serializable> implements
		GenericDAO<T, ID> {

	protected Class<T> persistentClass;

	@PersistenceContext
	protected EntityManager em;

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(T entity) {
		em.persist(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void merge(T entity) {
		em.merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(T entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	public T findById(Object id) {
		return em.find(persistentClass, id);
	}

	public List<T> findAll() {
		em.isOpen();
		TypedQuery<T> query = em.createQuery(
				" SELECT o FROM " + persistentClass.getSimpleName() + " o", persistentClass);
		return query.getResultList();
	}
}