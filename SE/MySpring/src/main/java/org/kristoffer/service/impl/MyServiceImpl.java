package org.kristoffer.service.impl;

import org.kristoffer.dao.MyDao;
import org.kristoffer.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The default bean name is myServiceImpl. This package is set to be
 * auto-scanned for annotated components
 */
@Service
public class MyServiceImpl implements MyService {
	@Autowired
	private MyDao dao;

	public String doSomething() {
		return dao.findSomething("id");
	}
}
