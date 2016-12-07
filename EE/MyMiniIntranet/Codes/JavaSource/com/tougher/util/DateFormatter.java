package com.tougher.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
* <P>
* For Formatting Date objects to String and parsing String to Date
* objects. 
* <P>
* Since the format for displaying dates are the same throughout the 
* application, encapsulating the functionality in one class and using 
* it consistently across all classes ensures the uniformity of the 
* format of the displayed date.
* <P>
* Since this will be used so frequently, a significant saving in the 
* instantiation of the SimpleDateFormat will be achieved as well as 
* less garbage collection load for the JVM.
* <P>
* The common representation of a date in html is a drop down for the month
* with values (01 for January to 12 for December), a drop down for the date, 
* with values (01 to 31),  and a text box for the year. That is why there is
* a different SDF for parsing, since the format for the month is different 
* when parsing (01 to 12) than when formatting (January to December)
* <P>
* Note that only four digit years will be recognized. years having three digits
* must explicitly be entered with leading zeroes. This is to prevent accidental
* omission of digits.
* <P>
* The DateFormatter may be subclassed so that an application with a different 
* requirement for date formats can change the date format objects, without 
* having to recode the parse and format methods. To accomplish this, just 
* subclass this class, then initialized the appropriate SimpleDateFormat
* objects to the correct date format using a static initializer.
*
* @author  Kristoffer Chua, toffer@bigfoot.com, ABSi
* @version 0.2 August 26, 2003
* @version 0.1 October 10, 2000
*/
public class DateFormatter {
	protected static SimpleDateFormat parseDateSdf=
		new SimpleDateFormat("MM/dd/yyyy");
	protected static SimpleDateFormat parseDateMonthSdf=
		new SimpleDateFormat("MM");
	protected static SimpleDateFormat parseDateDateSdf=
		new SimpleDateFormat("dd");
	protected static SimpleDateFormat parseDateYearSdf=
		new SimpleDateFormat("yyyy");

	protected static SimpleDateFormat formatDateSdf=
		new SimpleDateFormat("MM/dd/yy");
	protected static SimpleDateFormat emailFormatDateSdf=
		new SimpleDateFormat("MMMMM d, yyyy hh:mm:ss aaa");
  protected static SimpleDateFormat emailNoTimeDateSdf=
		new SimpleDateFormat("MMMMM d, yyyy");    

	static {
		parseDateSdf.setLenient(false);
	}

	protected DateFormatter() {}

	/**
	* The date is parsed using parseDateSdf.
	* @param dateString The String to be converted to Date.
	* @return date object.
	* @throws ParseException if the dateString represents an invalid date or is in wrong format.
	*/
	private static Date parseDate(
		String dateString,
		SimpleDateFormat parseDateSdf)
		throws ParseException {
		if (dateString == null)
			throw new ParseException("Date entered is null", 0);
		Date d= new Date(parseDateSdf.parse(dateString).getTime());
		//needed because 3 digit years are accepted
		if (!dateString.equals(parseDateSdf.format(d))) {
			throw new ParseException("Date entered is invalid", 0);
		}
		return d;
	}

	//**************parseDateSdf METHODS

	/**
	* The date is parsed using parseDateSdf.
	* @param dateString The String to be converted to Date.
	* @return date object.
	* @throws ParseException if the dateString represents an invalid date or is in wrong format.
	*/
	public static Date parseDate(
		String dateStringMonth,
		String dateStringDay,
		String dateStringYear)
		throws ParseException {
			if (dateStringYear.length()!=4){
				throw new ParseException("Invalid year",0);
			}
		String dateString=
			dateStringMonth + "/" + dateStringDay + "/" + dateStringYear;
		return parseDate(dateString, parseDateSdf);
	}

	/**
	* The date is formatted using parseDateMonthSdf
	* @param date The Date object to be converted to month
	* @return String date representation of the date.
	*/
	public static String formatMonth(java.util.Date date) {
		return parseDateMonthSdf.format(date);
	}
	/**
	* The date is formatted using parseDateDateSdf
	* @param date The Date object to be converted to date
	* @return String date representation of the date.
	*/
	public static String formatDate(java.util.Date date) {
		return parseDateDateSdf.format(date);
	}
	/**
	* The date is formatted using parseDateYearSdf
	* @param date The Date object to be converted to year
	* @return String date representation of the date.
	*/
	public static String formatYear(java.util.Date date) {
		return parseDateYearSdf.format(date);
	}

	//*********************formatDateSdf METHODS	
	/**
	* The date is parsed using formatDateSdf.
	* @param dateString The String to be converted to Date.
	* @return date object.
	* @throws ParseException if the dateString represents an invalid date or is in wrong format.
	*/
	public static Date parseDateMMddyy(String dateString) throws ParseException {
		return parseDate(dateString, formatDateSdf);
	}

	/**
	* The date is formatted using formatDateSdf
	* @param date The Date object to be converted to String
	* @return String date representation of the date.
	*/
	public static String formatShort(java.util.Date date) {
		return formatDateSdf.format(date);
	}

	//*********************emailFormatDateSdf METHODS	
	/**
	* The date is formatted using emailFormatDateSdf
	* @param date The Date object to be converted to String
	* @return String date representation of the date.
	*/
	public static String formatLong(java.util.Date date) {
		return emailFormatDateSdf.format(date);
	}

	//*********************emailNoTimeDateSdf METHODS	
	/**
	* The date is formatted using emailNoTimeDateSdf
	* @param date The Date object to be converted to String
	* @return String date representation of the date.
	*/
	public static String formatNoTimeLong(java.util.Date date) {
		return emailNoTimeDateSdf.format(date);
	}

}
