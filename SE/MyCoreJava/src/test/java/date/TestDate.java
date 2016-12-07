package date;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TestDate {
	private SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	private String testdata[] = { "01-Jan-1999", "14-Feb-2001", "31-Dec-2007",
			"31-Dec-1997", "29-Jun-1997" };

	// Cannot test bad cause thread assert fail does not register
	@Test
	public void testSDFThreadSafe() {
		Runnable r[] = new Runnable[testdata.length];
		for (int i = 0; i < r.length; i++) {
			final int i2 = i;
			r[i] = new Runnable() {
				public void run() {
					try {
						for (int j = 0; j < 1000; j++) {
							String str = testdata[i2];
							String str2 = null;

							synchronized (df) {
								Date d = df.parse(str);
								str2 = df.format(d);
							}
							if (!str.equals(str2)) {
								throw new RuntimeException(
										"date conversion failed after " + j
												+ " iterations. Expected "
												+ str + " but got " + str2);
							}
						}
					} catch (ParseException e) {
						throw new RuntimeException("parse failed");
					}
				}
			};
			new Thread(r[i]).start();
		}
	}

	@Test
	public void testDateRollAndAdd() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date d = sdf.parse("1-Dec-1977");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.roll(Calendar.MONTH, 1);

		String dateString = sdf.format(c.getTime());
		assertEquals("01-Jan-1977", dateString);
		// not 01-Jan-1978

		c.setTime(d);
		c.add(Calendar.MONTH, 1);
		dateString = sdf.format(c.getTime());
		assertEquals("01-Jan-1978", dateString);
		// not 01-Jan-1978

	}
}
