package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;

public class JpaUtil {
    /**
     * Taken from
     * http://stackoverflow.com/questions/367626/how-do-i-fix-the-expression
     * -of-type-list-needs-unchecked-conversion
     */
    public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	List<T> r = new ArrayList<T>(c.size());
	for (Object o : c)
	    r.add(clazz.cast(o));
	return r;
    }

    public static EntityManager getEntityManager() {
	InitialContext ctx;
	try {
	    ctx = new InitialContext();
	    EntityManager em = (EntityManager) ctx
		    .lookup("java:comp/env/persistence/ejbSandbox");
	    return em;
	} catch (NamingException e) {
	    e.printStackTrace();
	}
	return null;
    }
}
