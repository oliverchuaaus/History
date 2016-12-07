package com.tougher.util;

import org.junit.BeforeClass;

public class BaseTest {

	@BeforeClass
	public static void setup() {
		SpringUtil.init("spring.xml");
	}

}
