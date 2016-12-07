/*
 * Created on Apr 15, 2003
 */
package com.tougher.util;

import java.sql.*;
import java.io.*;
import java.util.*;
import org.apache.log4j.*;
/**
* Runs single lines of SQL as well as SQL script files. This is necesarry for
* unit and functional testing as a way to setup a consistent database state
* before every test.
* <P>
* Currently, this is very crude, relying only on ; as delimeter to separate 
* ql statements. Comments -- without semicolons at the end is unsupported.
* Since only semicolons are treated as separator tokens, the line after the
* comment will be ignored, which is not the usual or correct behavior.
* 
* @author  Kristoffer Chua
* @version 0.1 August 26, 2003
*/
public class SQLRunner{
	private static Logger log=Logger.getLogger(SQLRunner.class);
	/**
	 * Executes an SQL script file. Files that have commit are committed.
	 * @param conn
	 * @param file Use absolute path
	 * @throws Exception
	 */
	public static void executeSQLScript(Connection conn, String file) throws Exception{
		FileInputStream fis=new FileInputStream(file);
		BufferedInputStream bis=new BufferedInputStream(fis);
		StringBuffer sb=new StringBuffer(); 
		byte[] bytes=new byte[1024];
		while (bis.available()!=0){
			int length=fis.read(bytes);
			if (length!=1024){
				byte[] smallBytes=new byte[length];
				System.arraycopy(bytes,0,smallBytes,0,length);
				bytes=smallBytes;
			}	
			sb.append(new String(bytes));
		}
		StringTokenizer st = new StringTokenizer(sb.toString(),";",false);
		while (st.hasMoreTokens()) {
			String token=st.nextToken().trim();	
			if (!token.equals("")){
				if (token.equalsIgnoreCase("commit")){
					try{
						conn.commit();
					}
					catch (Exception e){
						DataAccessObject.rollback(conn);
					}
				}
				else if (token.equalsIgnoreCase("quit")){
					//do nothing
				}				
				else if (token.substring(0,2).equals("--")){
					
				}
				else{
					executeSQLStatement(conn,token);					
				}
			}
		}
	}



	/**
	 * Executes an SQL statement
	 * @param conn
	 * @param sqlStatement
	 * @return true if there is at least one row affected
	 * @throws SQLException
	 */
	public static boolean executeSQLStatement(Connection conn, String sqlStatement)
		throws SQLException {
		PreparedStatement pstmt= null;
		try {
			pstmt= conn.prepareStatement(sqlStatement);
			int affectedRows= pstmt.executeUpdate();
			return (affectedRows>0);
		}catch (SQLException se) {
			log.error(sqlStatement);
			DataAccessObject.rollback(conn);
			se.fillInStackTrace();
			throw se;
		}
		finally{
			DataAccessObject.closeCursors(pstmt);
		}
	}
	


	
}
