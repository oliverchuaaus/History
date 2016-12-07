package com.tougher.intranet.employee;

import java.io.File;

import com.tougher.util.FileUtil;
import com.tougher.util.HibernateUtil;
import com.tougher.util.ProgramAttributes;

import net.sourceforge.jwebunit.ExpectedRow;
import net.sourceforge.jwebunit.ExpectedTable;
import net.sourceforge.jwebunit.WebTestCase;

public class UnitTest extends WebTestCase {
	public void setUp() throws Exception {
		String sqlDir = ProgramAttributes.getProperty("sqlDir");
		HibernateUtil.runStatement("DELETE FROM Unit ORDER BY unitCode DESC");
		String statement = FileUtil.fileToString(new File(sqlDir + "Unit.sql"));
		HibernateUtil.runStatement(statement);
		getTestContext().setBaseUrl("http://localhost:8080/mini");
	}

	public void testSearch() {
		beginAt("/SearchUnit.do");
		assertTextNotPresent("No matching Records");
		assertTextNotPresent("Units");
		setFormElement("unitSubstring", "");
		submit();
		assertTextPresent("Units");
		assertTextInTable("unitList", "Application Designer/Developer");
		
		
		//check order
		ExpectedTable et = new ExpectedTable();
		et.appendRow(new ExpectedRow(new String[] {
				"ABS-OSD", "Update", "Delete" }));
		assertTableRowsEqual("unitList", 1, et);
		et = new ExpectedTable();
		et.appendRow(new ExpectedRow(new String[] {
				"Application Designer/Developer", "Update", "Delete" }));
		assertTableRowsEqual("unitList", 2, et);
		et = new ExpectedTable();
		et.appendRow(new ExpectedRow(new String[] {
				"System Administrator", "Update", "Delete" }));
		assertTableRowsEqual("unitList", 10, et);
	}
}
