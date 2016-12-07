package date;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.junit.Test;

public class DaysCalculatorTest {
	DaysCalculator dc = new DaysCalculator();

	public static int diff(Date date1, Date date2) {
		int days = Math.abs(Days.daysBetween(new LocalDate(date1),
				new LocalDate(date2)).getDays());
		if (days >= 1) {
			// exclude end date, but only if it is > 1
			days -= 1;
		}
		return days;
	}

	@Test
	public void testLessThan() throws ParseException {
		assertEquals(false,
				new MyDate(01, 01, 2000).isLessThan(new MyDate(01, 01, 2000)));
		assertEquals(true,
				new MyDate(03, 8, 1983).isLessThan(new MyDate(03, 01, 1984)));
	}

	@Test
	public void testLeapYear() throws ParseException {
		assertEquals(true, dc.isLeapYear(2016));
		assertEquals(false, dc.isLeapYear(2001));
		assertEquals(true, dc.isLeapYear(1984));
		assertEquals(true, dc.isLeapYear(1972));
	}

	@Test
	public void testDaysInYear() throws ParseException {
		assertEquals(366, dc.daysInYear(2016));
		assertEquals(365, dc.daysInYear(2001));
	}

	@Test
	public void testDaysInMonth() throws ParseException {
		assertEquals(29, dc.getDaysInMonth(2, 2016));
		assertEquals(28, dc.getDaysInMonth(2, 2001));
		assertEquals(30, dc.getDaysInMonth(9, 2001));
		assertEquals(31, dc.getDaysInMonth(7, 2001));
	}

	@Test
	public void testDaysSince1Jan() throws ParseException {
		assertEquals(1, dc.daysSince1Jan(new MyDate(01, 01, 2000), 2016));
		assertEquals(2, dc.daysSince1Jan(new MyDate(02, 01, 2000), 2016));
		assertEquals(366, dc.daysSince1Jan(new MyDate(31, 12, 2000), 2016));
		assertEquals(365, dc.daysSince1Jan(new MyDate(31, 12, 2000), 2015));
	}

	@Test
	public void testSameYear() throws ParseException {
		int actual, expected;
		String date1, date2;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		date1 = "07/11/1972";
		date2 = "07/11/1972";
		actual = dc.calculate(date1, date2);
		expected = diff(sdf.parse(date1), sdf.parse(date2));
		assertEquals(expected, actual);

		date1 = "07/11/1972";
		date2 = "08/11/1972";
		actual = dc.calculate(date1, date2);
		expected = diff(sdf.parse(date1), sdf.parse(date2));
		assertEquals(expected, actual);

		date1 = "01/01/2000";
		date2 = "03/01/2000";
		actual = dc.calculate(date1, date2);
		expected = diff(sdf.parse(date1), sdf.parse(date2));
		assertEquals(expected, actual);

		date1 = "01/03/1972";
		date2 = "01/04/1972";
		actual = dc.calculate(date1, date2);
		expected = diff(sdf.parse(date1), sdf.parse(date2));
		assertEquals(expected, actual);

		date1 = "02/06/1983";
		date2 = "22/06/1983";
		actual = dc.calculate(date1, date2);
		expected = diff(sdf.parse(date1), sdf.parse(date2));
		assertEquals(expected, actual);

		date1 = "04/07/1984";
		date2 = "25/12/1984";
		actual = dc.calculate(date1, date2);
		expected = diff(sdf.parse(date1), sdf.parse(date2));
		assertEquals(expected, actual);

		date2 = "04/07/1984";
		date1 = "25/12/1984";
		actual = dc.calculate(date1, date2);
		expected = diff(sdf.parse(date1), sdf.parse(date2));
		assertEquals(expected, actual);
	}

	@Test
	public void testDifferentYear() throws ParseException {
		int actual, expected;
		String date1, date2;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		date1 = "03/08/1983";
		date2 = "03/01/1984";
		actual = dc.calculate(date1, date2);
		expected = diff(sdf.parse(date1), sdf.parse(date2));
		assertEquals(expected, actual);

		date1 = "03/08/1983";
		date2 = "03/01/1989";
		actual = dc.calculate(date1, date2);
		expected = diff(sdf.parse(date1), sdf.parse(date2));
		assertEquals(expected, actual);

		date2 = "03/08/1983";
		date1 = "03/01/1984";
		actual = dc.calculate(date1, date2);
		expected = diff(sdf.parse(date1), sdf.parse(date2));
		assertEquals(expected, actual);

		date2 = "03/08/1983";
		date1 = "03/01/1989";
		actual = dc.calculate(date1, date2);
		expected = diff(sdf.parse(date1), sdf.parse(date2));
		assertEquals(expected, actual);

	}
}
