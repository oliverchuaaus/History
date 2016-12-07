package resource.bmt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import common.MyEntity;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ResourceBMTServiceImpl implements ResourceBMTService {
    @Resource
    private UserTransaction tx;
    @Resource(mappedName = "java:/OracleDS")
    private DataSource ds;

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
    public void add(MyEntity entity) {
	Connection conn = null;
	try {
	    tx.begin();
	    String sql = "INSERT INTO MyEntity (id, stringField) VALUES (?, ?)";
	    conn = ds.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setLong(1, entity.getId());
	    pstmt.setString(2, entity.getStringField());
	    pstmt.executeUpdate();

	    tx.commit();
	} catch (RuntimeException e) {
	    try {
		tx.rollback();
	    } catch (RuntimeException e1) {
	    } catch (SystemException e2) {
	    }
	    e.printStackTrace();
	} catch (Exception e) {
	    try {
		tx.rollback();
	    } catch (RuntimeException e1) {
	    } catch (SystemException e2) {
	    }
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
