package net.learntechnology.jms;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.learntechnology.dto.ProcessDTO;
import net.learntechnology.util.JmsProducer;

public class AppServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String QUEUE_CONNECTION_FACTORY = "java:comp/env/jms/AppQueueConnectionFactory";
    private static final String LONG_PROCESS_QUEUE = "java:comp/env/jms/LongProcessQueue";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("in doPost of AppServlet..");
    	String name = request.getParameter("name");
    	System.out.println("name: "+name );
    	ProcessDTO processDTO = new ProcessDTO();
    	processDTO.setName(name);
		JmsProducer.sendMessage(processDTO, QUEUE_CONNECTION_FACTORY, LONG_PROCESS_QUEUE);
		request.setAttribute("started",true);
		RequestDispatcher dispatcher =  getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}
