package date;

public class DaysCalculator {

	public int calculate(final String dateString1, final String dateString2) {
		MyDate date1 = MyDate.parse(dateString1);
		MyDate date2 = MyDate.parse(dateString2);
		if (date2.isLessThan(date1)) {
			// date1 should always be less, so swap if not
			final MyDate temp = date1;
			date1 = date2;
			date2 = temp;
			System.out.println("Swapped Dates!");
		}
		final int year1 = date1.getYear();
		final int year2 = date2.getYear();

		int days = 0;
		for (int year = year1; year < year2; year++) {
			if (year == year1) {
				if (year1 != year2) {
					// if up to next year, count days for first year.
					days += daysInYear(year) - daysSince1Jan(date1, year);
				}
			} else {
				// add years
				days += daysInYear(year);
			}
		}

		// If same year, will not go into loop
		if (year1 == year2) {
			System.out.println("same year");
			// if up to this year only, count days between dates.
			int from = daysSince1Jan(date2, year2);
			int to = daysSince1Jan(date1, year1);
			System.out.println("from: " + from);
			System.out.println("to: " + to);
			days += from - to;
		} else {
			days += daysSince1Jan(date2, date2.getYear());
		}

		if (days >= 1) {
			// exclude end date, but only if it is >= 1
			days -= 1;
		}
		System.out.println("days: " + days + "\n");
		return days;
	}

	public int daysInYear(int year) {
		if (isLeapYear(year)) {
			return 366;
		}
		return 365;
	}

	public int daysSince1Jan(MyDate date, final int year) {
		int days = date.getDay();
		for (int i = 1; i < date.getMonth(); i++) {
			days += getDaysInMonth(i, year);
		}
		return days;
	}

	public int getDaysInMonth(final int month, final int year) {
		int days = 0;
		switch (month) {
		case 2:
			if (!isLeapYear(year)) {
				days = 28;
			} else {
				days = 29;
			}
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		default:
			days = 31;
			break;
		}
		return days;
	}

	public boolean isLeapYear(final int year) {
		return ((year % 400) == 0) || ((year % 4) == 0) && ((year % 100) != 0);
	}
}

/**
 *
 * 07/11/1972 08/11/1972 0 02/06/1983 22/06/1983 19 04/07/1984 25/12/1984 173
 * 03/08/1983 03/01/1989 1979
 *
 * 02/06/1983 01/07/1983 19
 *
 * 03/08/1983 03/02/1984
 **/
