package net.learntechnology.util;


public class JmsProducerException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JmsProducerException() {}

    public JmsProducerException(String message) {
        super(message);
    }

    public JmsProducerException(Throwable cause) {
        super(cause);
    }

    public JmsProducerException(String message, Throwable cause) {
        super(message, cause);
    }

}