package com.tougher.util;

import java.sql.*;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * Provides a template for frquently used database operations like retrieving
 * the next value of a sequence, select, update, insert and delete.
 * <P>
 * Developers must subclass this or subclass DAOAdapter so that they only have
 * to override methods that they need to.
 * <P>
 * Abstract methods are provided for the user to specify variables or process
 * the results. For the query methods, there are three abstract methods: to set
 * the statement, to assign parameters and to process the result. For the update
 * methods, there are two abstract methods: to set the statement and to assign
 * parameters.
 * <P>
 * StringBuffers were used to let developers specify SQL statements because they
 * are mutable as opposed to String. It also has the added the benefit of less
 * object instantiation when concatenating String literals.
 * <P>
 * Primary Keys are abstracted as Objects. For single keys, String would be
 * sufficient. For multiple keys, HashMaps can be used. Take note that
 * additional code to cast the Object to a more specific type is needed.
 * <P>
 * Search Criteria are abstracted as HashMap objects, to accommodate more than
 * one search criteria. Values are inserted together with a key. Take note that
 * additional code to cast the Object to a more specific type is needed.
 * <P>
 * For select with multiple rows, this class can accomodate more than one select
 * statements. For example, a DAO can have search by name, search by age or
 * search by salary. <BR>
 * To accomodate this, declare a public static final String variable named
 * SEARCH_BY_NAME for search by name, SEARCH_BY_AGE and so on. <BR>
 * For example: <BR>
 * 
 * <PRE>
 * 
 * if (searchCriteria.get(SEARCH_BY).equals(SEARCH_BY_NAME)){ //set Statement
 * else if (searchCriteria.get(SEARCH_BY).equals(SEARCH_BY_AGE)){ //set
 * Statement }
 * 
 * </PRE>
 * 
 * Do this for all the three abstract methods of objectsWith() to support
 * multiple select statements.
 * 
 * @author Kristoffer Chua
 * @version 0.1 August 26, 2003
 */
public abstract class DataAccessObject implements java.io.Serializable {
	private static Logger log=Logger.getLogger(DataAccessObject.class);
	public static final String SEARCH_BY = "searchBy";

	public static String getSearchBy(HashMap hm){
		return (String)hm.get(SEARCH_BY);
	}
	//***********Close Cursor methods
	/**
	 * Call this to close Statement and ResultSet. Usually for select statements.
	 * Any Exception will be ignored.
	 * 
	 * @param stmt
	 */
	public static void closeCursors(Statement stmt, ResultSet rset) {
		if (rset != null) {
			try {
				rset.close();
			} catch (Exception e) {
			}
		}
		closeCursors(stmt);
	}

	/**
	 * Call this to close Statement. Usually for update, insert and delete
	 * statements. Any Exception will be ignored.
	 * 
	 * @param stmt
	 */
	public static void closeCursors(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Call this to close Connection. Any Exception will be ignored.
	 * 
	 * @param conn
	 */
	public static void closeCursors(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException se) {
			}
		}
	}

	/**
	 * Call this to rollback Connection. The first rollback of the Connection
	 * throws an SQLException, so another rollback should be called to complete
	 * rollback. Any Exception will be ignored.
	 * 
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (Exception ex) {
				}
			} //try
		} //if conn!=null
	}

	//************Get Next Sequence Method
	/**
	 * Returns the next value of the sequence.
	 * 
	 * @param conn
	 * @param sequenceName
	 * @return Next value of the sequence.
	 * @throws SQLException
	 */
	public static final String getNextSequence(Connection conn,
			String sequenceName) throws SQLException {
		ResultSet rset = null;
		Statement stmt = null;
		String sequencePreparedStatement = null;
		try {
			sequencePreparedStatement = "SELECT " + sequenceName
					+ ".nextVal FROM DUAL";
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sequencePreparedStatement);
			String sequence = null;
			if (rset.next()) {
				sequence = "" + rset.getLong(1);
			}
			return sequence;
		} catch (SQLException se) {
			DataAccessObject.rollback(conn);
			log.warn("stmt: " + sequencePreparedStatement, se);
			throw se;
		} finally {
			DataAccessObject.closeCursors(stmt, rset);
		}
	}

	//*****************SELECT method
	/**
	 * Use queryPreparedStatement to specify the query statement.
	 * 
	 * @param queryPreparedStatement
	 */
	protected abstract void setQueryPreparedStatement(
			StringBuffer queryPreparedStatement, HashMap sc);

	/**
	 * Call pstmt methods to set the parameters in the SQL by using the variables
	 * set in primaryKey
	 * 
	 * @param pstmt
	 * @param sc
	 * @throws SQLException
	 */
	protected abstract void assignQueryPreparedStatement(PreparedStatement pstmt,
			HashMap sc) throws SQLException;

	/**
	 * Call rset methods to get the results, populate Business Object, then return
	 * populated object.
	 * 
	 * @param rset
	 * @return Business Object that encapsulates the data from the database.
	 * @throws SQLException
	 */
	protected abstract Object processQueryResultSet(ResultSet rset,
			HashMap sc) throws SQLException;

	/**
	 * Selects a row from the database given its primaryKey. For this method to
	 * work properly, the abstract methods setQueryPreparedStatement(),
	 * assignQueryPreparedStatement(), and processQueryResultSet() must be
	 * implemented.
	 * 
	 * @param conn
	 * @param primaryKey
	 * @return Business Object that encapsulates the data from the database.
	 * @throws SQLException
	 */
	public Object objectWith(Connection conn, HashMap sc)
			throws SQLException {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		StringBuffer queryPreparedStatement = new StringBuffer();
		try {
			setQueryPreparedStatement(queryPreparedStatement, sc);
			pstmt = conn.prepareStatement(queryPreparedStatement.toString());
			assignQueryPreparedStatement(pstmt, sc);
			log.debug("SQL: "+queryPreparedStatement);
			rset = pstmt.executeQuery();
			return processQueryResultSet(rset, sc);
		} catch (SQLException se) {
			DataAccessObject.rollback(conn);
			log.warn("stmt: " + queryPreparedStatement, se);
			throw se;
		} finally {
			DataAccessObject.closeCursors(pstmt, rset);
		}
	}

	//***********SELECT MULTIPLE Methods
	/**
	 * Use collectionQueryPreparedStatement to specify the query statement. If
	 * there are more than one select method, check first for the SEARCH_BY key.
	 * 
	 * @param searchCriteria
	 * @param collectionQueryPreparedStatement
	 */
	protected abstract void setCollectionQueryPreparedStatement(
			HashMap searchCriteria, StringBuffer collectionQueryPreparedStatement);

	/**
	 * Call pstmt methods to set the parameters in the SQL by using the variables
	 * set in primaryKey If there are more than one select method, check first for
	 * the SEARCH_BY key.
	 * 
	 * @param pstmt
	 * @param searchCriteria
	 * @throws SQLException
	 */
	protected abstract void assignCollectionQueryPreparedStatement(
			PreparedStatement pstmt, HashMap searchCriteria) throws SQLException;

	/**
	 * Create arrayList, iterate rset methods to get the results, populate
	 * Business Object, add to arrayList then return arraylist If there are more
	 * than one select method, check first for the SEARCH_BY key.
	 * 
	 * @param rset
	 * @param searchCriteria
	 * @return ArrayList of business objects
	 * @throws SQLException
	 */
	protected abstract ArrayList processCollectionQueryResultSet(ResultSet rset,
			HashMap searchCriteria) throws SQLException;

	/**
	 * Selects multiple rows from the database given searchCriteria. For this
	 * method to work properly, the abstract methods
	 * setCollectionQueryPreparedStatement(),
	 * assignCollectionQueryPreparedStatement(), and
	 * processCollectionQueryResultSet() must be implemented. This method can
	 * accomodate multiple selects with different search criteria.
	 * 
	 * @param conn
	 * @param searchCriteria
	 * @return ArrayList of business objects
	 * @throws SQLException
	 */
	public ArrayList objectsWith(Connection conn, HashMap searchCriteria)
			throws SQLException {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		StringBuffer collectionQueryPreparedStatement = new StringBuffer();
		try {
			this.setCollectionQueryPreparedStatement(searchCriteria,
					collectionQueryPreparedStatement);
			pstmt = conn
					.prepareStatement(collectionQueryPreparedStatement.toString());
			this.assignCollectionQueryPreparedStatement(pstmt, searchCriteria);
			log.debug("SQL: "+collectionQueryPreparedStatement);
			rset = pstmt.executeQuery();
			return this.processCollectionQueryResultSet(rset, searchCriteria);
		} catch (SQLException se) {
			DataAccessObject.rollback(conn);
			log.warn("stmt: " + collectionQueryPreparedStatement, se);
			throw se;
		} finally {
			DataAccessObject.closeCursors(pstmt, rset);
		}
	}

	//***********APPEND Methods
	/**
	 * Use appendPreparedStatement to specify the query statement.
	 * 
	 * @param appendPreparedStatement
	 */
	protected abstract void setAppendPreparedStatement(
			StringBuffer appendPreparedStatement, Object businessObject);

	/**
	 * Call pstmt methods to set the parameters in the SQL by using the variables
	 * set in businessObject.
	 * 
	 * @param pstmt
	 * @param businessObject
	 * @throws SQLException
	 */
	protected abstract void assignAppendPreparedStatement(
			PreparedStatement pstmt, Object businessObject) throws SQLException;

	protected abstract void processGeneratedKeys(
			ResultSet gk, Object businessObject) throws SQLException;
	
	/**
	 * Inserts a row into the database given Business Object. For this method to
	 * work properly, the abstract methods setAppendPreparedStatement(), and
	 * assignAppendPreparedStatement() must be implemented.
	 * 
	 * @param conn
	 * @param businessObject
	 * @return true if there is at least one row affected
	 * @throws SQLException
	 */
	public boolean appendObject(Connection conn, Object businessObject)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		StringBuffer appendPreparedStatement = new StringBuffer();
		
		try {
			this.setAppendPreparedStatement(appendPreparedStatement, businessObject);
			pstmt = conn.prepareStatement(appendPreparedStatement.toString(),Statement.RETURN_GENERATED_KEYS);
			this.assignAppendPreparedStatement(pstmt, businessObject);
			log.debug("SQL: "+appendPreparedStatement);
			int affectedRows = pstmt.executeUpdate();
			rset=pstmt.getGeneratedKeys();
			this.processGeneratedKeys(rset, businessObject);
			if (affectedRows==0){ 
				log.warn("no affected rows");
			}
			return affectedRows > 0;
		} catch (SQLException se) {
			DataAccessObject.rollback(conn);
			log.warn("stmt: " + appendPreparedStatement, se);
			throw se;
		} finally {
			DataAccessObject.closeCursors(pstmt,rset);
		}
	}

	//***********UPDATE Methods
	/**
	 * Use updatePreparedStatement to specify the query statement.
	 * 
	 * @param updatePreparedStatement
	 */
	protected abstract void setUpdatePreparedStatement(
			StringBuffer updatePreparedStatement, Object businessObject);

	/**
	 * Call pstmt methods to set the parameters in the SQL by using the variables
	 * set in businessObject.
	 * 
	 * @param pstmt
	 * @param businessObject
	 * @throws SQLException
	 */
	protected abstract void assignUpdatePreparedStatement(
			PreparedStatement pstmt, Object businessObject) throws SQLException;

	/**
	 * Updates a row into the database given Business Object. For this method to
	 * work properly, the abstract methods setUpdatePreparedStatement(), and
	 * assignUpdatePreparedStatement() must be implemented.
	 * 
	 * @param conn
	 * @param businessObject
	 * @return true if there is at least one row affected
	 * @throws SQLException
	 */
	public boolean updateObject(Connection conn, Object businessObject)
			throws SQLException {
		PreparedStatement pstmt = null;
		StringBuffer updatePreparedStatement = new StringBuffer();
		try {
			this.setUpdatePreparedStatement(updatePreparedStatement, businessObject);
			pstmt = conn.prepareStatement(updatePreparedStatement.toString());
			this.assignUpdatePreparedStatement(pstmt, businessObject);
			log.debug("SQL: "+updatePreparedStatement);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows==0){ 
				log.warn("no affected rows");
			}
			return affectedRows > 0;
		} catch (SQLException se) {
			DataAccessObject.rollback(conn);
			log.warn("stmt: " + updatePreparedStatement, se);
			throw se;
		} finally {
			DataAccessObject.closeCursors(pstmt);
		}
	}

	//***********DELETE Methods
	/**
	 * Use deletePreparedStatement to specify the query statement.
	 * 
	 * @param deletePreparedStatement
	 */
	protected abstract void setDeletePreparedStatement(
			StringBuffer deletePreparedStatement, Object primaryKey);

	/**
	 * Call pstmt methods to set the parameters in the SQL by using the
	 * primaryKey.
	 * 
	 * @param pstmt
	 * @param primaryKey
	 * @throws SQLException
	 */
	protected abstract void assignDeletePreparedStatement(
			PreparedStatement pstmt, Object primaryKey) throws SQLException;

	/**
	 * Deletes a row into the database given primaryKey of Business Object. For
	 * this method to work properly, the abstract methods
	 * setDeletePreparedStatement(), and assignDeletePreparedStatement() must be
	 * implemented.
	 * 
	 * @param conn
	 * @param primaryKey
	 * @return true if there is at least one row affected
	 * @throws SQLException
	 */
	public boolean deleteObject(Connection conn, Object primaryKey)
			throws SQLException {
		PreparedStatement pstmt = null;
		StringBuffer deletePreparedStatement = new StringBuffer();
		try {
			this.setDeletePreparedStatement(deletePreparedStatement, primaryKey);
			pstmt = conn.prepareStatement(deletePreparedStatement.toString());
			this.assignDeletePreparedStatement(pstmt, primaryKey);
			log.debug("SQL: "+deletePreparedStatement);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows==0){ 
				log.warn("no affected rows");
			}
			return affectedRows > 0;
		} catch (SQLException se) {
			DataAccessObject.rollback(conn);
			log.warn("stmt: " + deletePreparedStatement, se);
			throw se;
		} finally {
			DataAccessObject.closeCursors(pstmt);
		}
	}
}