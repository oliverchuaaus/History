package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static final EntityManagerFactory emf;
	static {
		try {
			emf = Persistence.createEntityManagerFactory("jpasandbox");

		} catch (Throwable ex) {
			// Log exception!
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManager getEm() {
		return emf.createEntityManager();
	}
}