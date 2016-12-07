package exceptions;

public class MyCheckedException extends Exception {

	private static final long serialVersionUID = 1L;

	public MyCheckedException() {
		super();
	}

	public MyCheckedException(String message) {
		super(message);
	}

	public MyCheckedException(String message, Throwable t) {
		super(message, t);
	}

	public MyCheckedException(Throwable t) {
		super(t);
	}
}
