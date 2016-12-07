package resource.cmt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import common.MyEntity;

@Stateless
public class ResourceCMTServiceImpl implements ResourceCMTService {
    @Resource(mappedName = "java:/OracleDS")
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
		return entity;
	    }
	    
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
	    throw new RuntimeException(e);
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
    
    public void clear() {
	Connection conn = null;
	try {
	    String sql = "DELETE FROM MyEntity";
	    conn = ds.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new RuntimeException(e);
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
	    throw new RuntimeException(e);
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

}
