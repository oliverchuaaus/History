package jdk5.generics.custom;

import java.util.HashMap;
import java.util.Map;

public abstract class Authoriser<E extends Number> {
	@SuppressWarnings("rawtypes")
	private static Map<Class<? extends Number>, Class<? extends Authoriser>> authoriserMap = new HashMap<Class<? extends Number>, Class<? extends Authoriser>>();

	static {
		authoriserMap.put(Integer.class, AuthoriserInteger.class);
		authoriserMap.put(Double.class, AuthoriserDouble.class);
	}

	public String authorise(E number) {
		return "authorise: " + number.intValue();
	}

	public static <E extends Number> Authoriser<E> getAuthoriser(E number)
			throws InstantiationException, IllegalAccessException {
		@SuppressWarnings("rawtypes")
		Class<? extends Authoriser> authoriserClazz = authoriserMap.get(number
				.getClass());
		@SuppressWarnings("unchecked")
		Authoriser<E> authoriser = authoriserClazz.newInstance();
		return authoriser;
	}

}
