package com.tougher.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

/**
* Formats BigDecimal to String, with appropriate grouping characters,
* as well as parsing String to BigDecimal.
* <P>
* This class may be subclassed so that an application with a different 
* requirement for number formatting can change the number format objects, without 
* having to recode the methods. To accomplish this, just subclass this class, 
* then initialize the appropriate number format using a static initializer.
* 
* @author  Kristoffer Chua
* @version 0.1 August 26, 2003
*/
public class NumberFormatter {
	private static NumberFormat intNf= NumberFormat.getInstance();
	private static NumberFormat nf= NumberFormat.getInstance();

	static {
		nf.setGroupingUsed(true);
		nf.setMinimumFractionDigits(2);
		intNf.setGroupingUsed(true);
	}

	/**
	 * @param number
	 * @return String repersentation of number. Number has 2 digits for the
	 * number's fractional part. Thousand separator is also used. 
	 */
	public static String format(int number) {
		return intNf.format(number);
	}

	/**
	 * @param number
	 * @return String repersentation of number. Number has 2 digits for the
	 * number's fractional part. Thousand separator is also used. If the number
	 * is negative, the number is enclosed in parenthesis.
	 *  
	 */
	public static String format(java.math.BigDecimal number) {
		if (number.signum() == -1) {
			return "(" + nf.format(number.abs()) + ")";
		}
		return nf.format(number);
	}

	/**
	 * @param bigDecimalString
	 * @return BigDecimal
	 * @throws ParseException If bigDecimalString is not a valid number 
	 */
	public static BigDecimal parseBigDecimal(String bigDecimalString)
		throws ParseException {
		return new BigDecimal(bigDecimalString);
	}

}