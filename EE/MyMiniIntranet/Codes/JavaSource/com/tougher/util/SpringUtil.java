package com.tougher.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {

	public static ApplicationContext getContext() {
		String[] paths = { "spring.xml" };
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
		return ctx;
	}

}
