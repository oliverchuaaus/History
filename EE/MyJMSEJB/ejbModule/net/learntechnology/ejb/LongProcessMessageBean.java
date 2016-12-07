package net.learntechnology.ejb;

import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import net.learntechnology.dto.ProcessDTO;
import net.learntechnology.util.LongProcessService;
import net.learntechnology.util.ProcessResult;


public class LongProcessMessageBean implements MessageDrivenBean, MessageListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private MessageDrivenContext ctx = null;

    public LongProcessMessageBean() {}


    public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException {
        this.ctx = ctx;
    }

    public void ejbCreate() {
        // no specific action required for message-driven beans
    }

    public void ejbRemove() {
        ctx = null;
    }

    public void onMessage(Message message) {
    	System.out.println("****************************************************");
        System.out.println("LongProcessMessageBean.onMessage(): Received message.");
    	System.out.println("****************************************************");
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage objMessage = (ObjectMessage) message;
                Object obj = objMessage.getObject();
                if (obj instanceof ProcessDTO) {
                    System.out.println("obj is ProcessDTO, calling the LongProcess.......");
                    @SuppressWarnings("unused")
					ProcessResult result = LongProcessService.doLongProcess((ProcessDTO)obj);
                 } else {
                    System.err.println("Expecting ProcessDTO in Message");
                }
            } else {
                System.err.println("Expecting Object Message");
            }
            System.out.println("*******************************************");
            System.out.println("Leaving LongProcessMessageBean.onMessage()");
            System.out.println("*******************************************");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
