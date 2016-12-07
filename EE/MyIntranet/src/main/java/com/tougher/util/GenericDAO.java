package com.tougher.util;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {
	public void persist(T entity);

	public void merge(T entity);

	public void remove(T entity);

	public T findById(Object id);

	public List<T> findAll();
}