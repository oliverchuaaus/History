package date;

import java.util.StringTokenizer;

public class MyDate {
	public static MyDate parse(final String date) {
		final StringTokenizer st = new StringTokenizer(date, "/");
		final int day = Integer.parseInt((String) st.nextElement());
		final int month = Integer.parseInt((String) st.nextElement());
		final int year = Integer.parseInt((String) st.nextElement());
		return new MyDate(day, month, year);
	}

	private final int day;

	private final int month;

	private final int year;

	public MyDate(final int day, final int month, final int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public boolean isLessThan(final MyDate date2) {
		if (year < date2.year) {
			return true;
		} else if (year == date2.year) {
			if (month < date2.month) {
				return true;
			} else if (month == date2.month) {
				if (day < date2.day) {
					return true;
				}
			}
		}
		return false;
	}

}
