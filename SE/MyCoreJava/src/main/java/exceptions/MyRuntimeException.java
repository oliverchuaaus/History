package exceptions;

public class MyRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MyRuntimeException() {
		super();
	}

	public MyRuntimeException(String message) {
		super(message);
	}

	public MyRuntimeException(String message, Throwable t) {
		super(message, t);
	}

	public MyRuntimeException(Throwable t) {
		super(t);
	}
}
