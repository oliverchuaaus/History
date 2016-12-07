package servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.SimpleEntity;

public class MyServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    
    @PersistenceUnit(unitName="warSandbox")
    private EntityManagerFactory emf;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	EntityManager em = emf.createEntityManager();
	em.getTransaction().begin();
	SimpleEntity entity = new SimpleEntity();
	em.persist(entity);
	em.getTransaction().commit();
	resp.getWriter().append("EntityId is "+entity.getId());
    }
    
    
}
