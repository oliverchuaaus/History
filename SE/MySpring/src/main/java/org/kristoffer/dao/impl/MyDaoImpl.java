package org.kristoffer.dao.impl;

import org.kristoffer.dao.MyDao;
import org.springframework.stereotype.Repository;

@Repository
public class MyDaoImpl implements MyDao {
	public String findSomething(String id) {
		return "Something";
	}
}
