package em.containerManaged;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class SimulatedException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public SimulatedException(String str) {
	super(str);
    }
}
