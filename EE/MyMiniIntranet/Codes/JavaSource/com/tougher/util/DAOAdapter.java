package com.tougher.util;

import java.sql.*;
import java.util.*;

/**
 * This class allows developers to extend DataAccessObject without having to
 * override all methods, just the methods they need to.
 * 
 * @author Kristoffer Chua
 * @version 0.1 August 26, 2003
 */
public class DAOAdapter extends DataAccessObject {

	protected void setQueryPreparedStatement(StringBuffer queryPreparedStatement,
			HashMap sc) {
	}

	protected void assignQueryPreparedStatement(PreparedStatement pstmt,
			HashMap sc) throws SQLException {
	}

	protected Object processQueryResultSet(ResultSet rset, HashMap sc)
			throws SQLException {
		return null;
	}

	protected void setCollectionQueryPreparedStatement(HashMap sc,
			StringBuffer collectionQueryPreparedStatement) {
	}

	protected void assignCollectionQueryPreparedStatement(
			PreparedStatement pstmt, HashMap sc) throws SQLException {
	}

	protected ArrayList processCollectionQueryResultSet(ResultSet rset, HashMap sc)
			throws SQLException {
		return null;
	}

	protected void setAppendPreparedStatement(
			StringBuffer appendPreparedStatement, Object realSubject) {
	}

	protected void assignAppendPreparedStatement(PreparedStatement pstmt,
			Object realSubject) throws SQLException {
	}

	protected void processGeneratedKeys(ResultSet rset, Object businessObject) throws SQLException {
	}

	protected void setUpdatePreparedStatement(
			StringBuffer updatePreparedStatement, Object realSubject) {
	}

	protected void assignUpdatePreparedStatement(PreparedStatement pstmt,
			Object realSubject) throws SQLException {
	}

	protected void setDeletePreparedStatement(
			StringBuffer deletePreparedStatement, Object primaryKey) {
	}

	protected void assignDeletePreparedStatement(PreparedStatement pstmt,
			Object primaryKey) throws SQLException {
	}
}