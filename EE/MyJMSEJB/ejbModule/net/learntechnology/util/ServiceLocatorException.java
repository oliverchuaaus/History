package net.learntechnology.util;

public class ServiceLocatorException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceLocatorException() {
        super();
    }

    public ServiceLocatorException(String message) {
        super(message);
    }

    public ServiceLocatorException(Throwable cause) {
        super(cause);
    }

    public ServiceLocatorException(String message, Throwable cause) {
        super(message, cause);
    }

}
