package util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	private static ApplicationContext context = null;

	public static synchronized void init(String configFile) {
		try {
			context = new ClassPathXmlApplicationContext(configFile);
		} catch (Exception e) {
			throw new RuntimeException("Cannot create bean factory", e);
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
