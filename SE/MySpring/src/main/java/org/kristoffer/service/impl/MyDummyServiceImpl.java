package org.kristoffer.service.impl;

import org.kristoffer.service.MyOtherService;
import org.springframework.stereotype.Service;

/**
 * The default bean name is myServiceImpl. This package is set to be
 * auto-scanned for annotated components
 */
@Service
public class MyDummyServiceImpl implements MyOtherService {

	public String doSomething() {
		return "something";
	}
}
