package patterns.singleton;

public final class Singleton {

	private static Singleton instance = new Singleton();

	private Singleton() {
	}

	public static synchronized Singleton getInstance() {
		return instance;
	}

	public String method() {
		return "message";
	}
}
