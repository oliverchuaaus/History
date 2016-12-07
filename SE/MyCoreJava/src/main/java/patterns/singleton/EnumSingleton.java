package patterns.singleton;

public enum EnumSingleton {
	INSTANCE("message");

	private String message;

	private EnumSingleton(String str) {
		message = str;
	}

	public String method() {
		return message;
	}
}
