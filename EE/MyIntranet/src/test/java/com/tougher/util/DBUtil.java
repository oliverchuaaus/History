package com.tougher.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;

public class DBUtil {
	public static void populate(String xmlFile) {
		IDatabaseConnection connection = null;
		try {
			Map<String, Object> emProps = EMUtil.getEm()
					.getEntityManagerFactory().getProperties();
			String url = (String) emProps.get("javax.persistence.jdbc.url");
			String username = (String) emProps
					.get("javax.persistence.jdbc.user");
			String password = (String) emProps
					.get("javax.persistence.jdbc.password");
			Connection jdbcConnection = DriverManager.getConnection(url,
					username, password);

			connection = new DatabaseConnection(jdbcConnection, "MYINTRANET");
			ClassLoader classLoader = connection.getClass().getClassLoader();
			File f = new File(classLoader.getResource(xmlFile).getFile());
			IDataSet dataSet = new XmlDataSet(new FileInputStream(f));
			DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
