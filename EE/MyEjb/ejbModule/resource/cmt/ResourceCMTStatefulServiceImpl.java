package resource.cmt;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import common.MyEntity;

/*
 * We use SessionSynchronization for caching since we don't have em
 */
@Stateful
public class ResourceCMTStatefulServiceImpl implements ResourceCMTStatefulService,
	SessionSynchronization {
    @Resource(mappedName = "java:/Oracle2DS")
    private DataSource ds;
    @Resource
    private SessionContext sessionContext;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public MyEntity find() {
	Connection conn = null;
	try {
	    MyEntity entity = new MyEntity();
	    String sql = "SELECT id, stringField FROM MyEntity WHERE ID=1000";
	    conn = ds.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    ResultSet rset = pstmt.executeQuery();
	    while (rset.next()) {
		entity.setId(rset.getLong(1));
		entity.setStringField(rset.getString(2));
	    }
	    return entity;
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (conn != null) {
		try {
		    conn.close();
		} catch (SQLException e) {
		    // ignore
		}
	    }
	}
	return null;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void add(MyEntity entity) {
	Connection conn = null;
	try {
	    String sql = "INSERT INTO MyEntity (id, stringField) VALUES (?, ?)";
	    conn = ds.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setLong(1, entity.getId());
	    pstmt.setString(2, entity.getStringField());
	    pstmt.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (conn != null) {
		try {
		    conn.close();
		} catch (SQLException e) {
		    // ignore
		}
	    }
	}
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addRollback(MyEntity entity) {
	Connection conn = null;
	try {
	    String sql = "INSERT INTO MyEntity (id, stringField) VALUES (?, ?)";
	    conn = ds.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setLong(1, entity.getId());
	    pstmt.setString(2, entity.getStringField());
	    pstmt.executeUpdate();

	    sessionContext.setRollbackOnly();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (conn != null) {
		try {
		    conn.close();
		} catch (SQLException e) {
		    // ignore
		}
	    }
	}
    }

    @Override
    public void afterBegin() throws EJBException, RemoteException {
	// TODO Auto-generated method stub
    }

    @Override
    public void afterCompletion(boolean arg0) throws EJBException,
	    RemoteException {
	// TODO Auto-generated method stub

    }

    @Override
    public void beforeCompletion() throws EJBException, RemoteException {
	// TODO Auto-generated method stub

    }

}
