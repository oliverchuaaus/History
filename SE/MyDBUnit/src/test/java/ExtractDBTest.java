// KAZ Technology Services Pty Ltd (c) 2005

import org.dbunit.database.IDatabaseConnection;
import org.junit.Test;

/**
 * This is the ExtractDB class.
 */
public class ExtractDBTest {
	@Test
	public void testExtract() throws Exception {
		// database connection
		IDatabaseConnection connection = DBUnitUtility.getConnection();
		// NOTE case sensitive table names, from jpasandbox schema
		// table export
		DBUnitUtility.tableExport(connection, "INDIVIDUAL_SIMPLE_ENTITY",
				"output/table.xml");
		// all tables export
		DBUnitUtility.allTablesExport(connection, "output/allTables.xml");
		// dependent export
		DBUnitUtility.dependentTablesExport(connection, "ONETOMANY_LIST_ONE",
				"output/dependentTables.xml");
	}

	public void testPopulate() throws Exception {
		// database connection
		IDatabaseConnection connection = DBUnitUtility.getConnection();
		DBUnitUtility.populateTable(connection, "output/dependentTables.xml");
	}
}
