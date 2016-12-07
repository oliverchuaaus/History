package com.tougher.util;

import java.sql.*;

/**
 * A utility class for retrieving a single Connection instance.
 * <P>
 * It gets the Database driver class and Database connect string from
 * ProgramAttributes using the keys dbDriverClass and dbConnectString.
 * <P>
 * It was designed so that there is a uniform way to get a Connection and a
 * uniform way to set the parameters to get a Connection. In this case, from the
 * ProgramAttributes. This will facilitate easy switching of database
 * connections from development to deployment.
 * <P>
 * It was also designed so that the implementation of getting the Connection is
 * transparent. When the need arise to get the Connection from a Connection
 * Pool, only this class will change, while classes calling this will not be
 * affected.
 * 
 * @author Kristoffer Chua
 * @version 0.1 August 26, 2003
 */
public class ConnectionFactory {

	private ConnectionFactory() {
	}

	/**
	 * There should be entries on the property file read by ProgramAttributes for
	 * the following keys:
	 * <P>
	 * dbDriverClass - Class name of database driver. <BR>
	 * For example - for Oracle: oracle.jdbc.driver.OracleDriver
	 * <P>
	 * dbConnectString - Connect string for the database instance. For an oracle
	 * db with the following key-value pairs: <BR>
	 * username:uname <BR>
	 * password:pword <BR>
	 * pc name:mypc <BR>
	 * port:1521 <BR>
	 * olite:service name <BR>
	 * For example - jdbc:oracle:thin:uname/passord@mypc:1521:olite
	 * 
	 * @return a Connection instance with autocommit = false.
	 * @throws SQLException
	 *           If the connectString is not specified in the property file, is
	 *           invalid, or jar file of db driver is not in classpath. Also, if
	 *           dbName is not specified in the property file, is invalid or
	 *           database is down.
	 */
	public static Connection getConnection() throws SQLException {
		String dbDriverClass = ProgramAttributes.getProperty("dbDriverClass");
		String dbConnectString = ProgramAttributes.getProperty("dbConnectString");
		try {
			Class.forName(dbDriverClass);
		} catch (ClassNotFoundException e) {
			throw new SQLException("ClassNotFoundException: " + e.getMessage());
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}
		Connection conn = DriverManager.getConnection(dbConnectString);
		conn.setAutoCommit(false);
		return conn;
	}
}