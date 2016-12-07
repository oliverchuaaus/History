import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;

public class DBUnitUtility {
	public static IDatabaseConnection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection jdbcConnection = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:XE", "jpasandbox", "jpasandbox");
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection,
				"JPASANDBOX");
		connection.getConfig().setProperty(
				DatabaseConfig.FEATURE_SKIP_ORACLE_RECYCLEBIN_TABLES,
				Boolean.TRUE);
		connection.getConfig().setProperty(
				DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
				new OracleDataTypeFactory());
		return connection;
	}

	public static void tableExport(IDatabaseConnection connection,
			String tableName, String outputFile) throws Exception {
		QueryDataSet partialDataSet = new QueryDataSet(connection);
		partialDataSet.addTable(tableName);
		XmlDataSet.write(partialDataSet, new FileOutputStream(outputFile));

		// write DTD file
		FlatXmlDataSet.write(connection.createDataSet(), new FileOutputStream(
				outputFile + ".dtd"));
	}

	public static void dependentTablesExport(IDatabaseConnection connection,
			String tableName, String outputFile) throws Exception {
		// dependent tables database export: export table X and all tables that
		// have a PK which is a FK on X, in the right order for insertion
		String[] depTableNames = TablesDependencyHelper.getAllDependentTables(
				connection, tableName);
		IDataSet depDataset = connection.createDataSet(depTableNames);
		FlatXmlDataSet.write(depDataset, new FileOutputStream(outputFile));
	}

	public static void allTablesExport(IDatabaseConnection connection,
			String outputFile) throws Exception {
		IDataSet fullDataSet = connection.createDataSet();
		XmlDataSet.write(fullDataSet, new FileOutputStream(outputFile));
	}

	public static void populateTable(IDatabaseConnection connection,
			String inputFile) throws Exception {
		// initialise your dataset here
		IDataSet dataSet = new XmlDataSet(new FileInputStream(inputFile));
		try {
			DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
		} finally {
			connection.close();
		}
	}
}
