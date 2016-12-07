package com.tougher.util;

import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringUtil extends ContextLoaderListener {
	private static ApplicationContext context = null;

	public static synchronized void init(String configFile) {
		try {
			context = new ClassPathXmlApplicationContext(configFile);
		} catch (Exception e) {
			throw new RuntimeException("Cannot create bean factory", e);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			super.contextInitialized(event);
			context = WebApplicationContextUtils
					.getRequiredWebApplicationContext(event.getServletContext());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	public static <T> T getBeanByClass(Class<T> clazz) {
		return clazz.cast(context.getBean(clazz));
	}

	public static <T> T getBeanByClass(String beanName, Class<T> clazz) {
		return clazz.cast(context.getBean(beanName, clazz));
	}

}
