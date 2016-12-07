package util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EjbUtil {
    public static Object getBean(String beanName) throws NamingException {
	Hashtable<String, String> environment = new Hashtable<String, String>();
	environment.put(Context.INITIAL_CONTEXT_FACTORY,
		"org.jnp.interfaces.NamingContextFactory");
	environment.put(Context.URL_PKG_PREFIXES,
		"org.jboss.naming:org.jnp.interfaces");
	environment.put(Context.PROVIDER_URL, "jnp://localhost:8099"); // remote
	// machine
	// IP
	InitialContext context = new InitialContext(environment);
	Object obj = context.lookup(beanName); // ejb-name
	return obj;
    }

}
