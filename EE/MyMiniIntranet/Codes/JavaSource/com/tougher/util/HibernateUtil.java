package com.tougher.util;

import java.sql.Connection;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static final SessionFactory sessionFactory;
    public static final ThreadLocal session = new ThreadLocal();
    
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session currentSession() throws HibernateException {
        Session s = (Session) session.get();
        if (s == null) {
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s;
    }
    
    public static void runStatement(String statement) throws Exception{
    	Session s=currentSession();
    	Transaction t=s.beginTransaction();
    	Connection conn=s.connection();
    	Statement stmt=conn.createStatement();
    	stmt.executeUpdate(statement);
    	stmt.close();
    	t.commit();
    }
}
