package ch.makery.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

	/** The date pattern that is used for conversion. Change as you wish. */
	private static final String datePattern = "dd.MM.yyyy";
	
	// the date formatter

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);

	 
    /**
     * Returns the given date as a well formatted String. The above defined 
     * {@link DateUtil#DATE_PATTERN} is used.
     * 
     * @param date the date to be returned as a string
     * @return formatted string
     */

	public static String format(LocalDate date)
	{
		if(date == null) {
			return null;
		}
		return dateFormatter.format(date);
	}
	//Check if String date is valid
	public static LocalDate parse(String dateString) {
		try {
			return dateFormatter.parse(dateString, LocalDate::from);
			
		}catch(DateTimeParseException e)
		{
			return null;
		}
	}
	 /**
     * Checks the String whether it is a valid date.
     * 
     * @param dateString
     * @return true if the String is a valid date
     */

	  public static boolean validDate(String dateString) {
	        // Try to parse the String.
	        return DateUtil.parse(dateString) != null;
	    }

	
}
