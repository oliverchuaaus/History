// KAZ Technology Services Pty Ltd (c) 2005

import java.io.File;

import org.dbunit.database.IDatabaseConnection;
import org.junit.Test;

/**
 * This is the ExtractDB class.
 */
public class ExtractDBTest {
	private static final String RESOURCE = ".output";

	@Test
	public void testExtract() throws Exception {
		// database connection
		IDatabaseConnection connection = DBUnitUtility.getConnection();
		// NOTE case sensitive table names, from jpasandbox schema
		// table export
		
		File output = new File(RESOURCE);
		output.mkdir();
		
		DBUnitUtility.tableExport(connection, "INDIVIDUAL_SIMPLE_ENTITY",
				RESOURCE +"/table.xml");
		// all tables export
		DBUnitUtility.allTablesExport(connection, RESOURCE+ "/allTables.xml");
		// dependent export
		DBUnitUtility.dependentTablesExport(connection, "ONETOMANY_LIST_ONE",
				RESOURCE +"/dependentTables.xml");
	}

	public void testPopulate() throws Exception {
		// database connection
		IDatabaseConnection connection = DBUnitUtility.getConnection();
		DBUnitUtility.populateTable(connection, RESOURCE +"/dependentTables.xml");
	}
}
