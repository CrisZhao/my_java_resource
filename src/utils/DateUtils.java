package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Overwrite some function of Date class for financial using.
 * 
 * @author Cris Zhao
 */
public class DateUtils {

	public static final String DATE_FORMAT_STRING = "yyyy-MM-dd";
	// TODO: Please use synchronized block if you use DATE_FORMAT. This the best
	// way if no thread pools are used.
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			DATE_FORMAT_STRING);

	private static Logger log = LoggerFactory.getLogger(DateUtils.class);

	/**
	 * Constructs a Date with the given date set in the default time zone with
	 * the default locale. e.g. "20121434" returns the date 2013-03-06
	 * 
	 * @param y
	 *            int -- year
	 * @param m
	 *            int -- month, 1-based. e.g., 1 for January.
	 * @param d
	 *            int -- day
	 * @return Date -- the date of the input
	 */
	public static Date getDate(final int y, final int m, final int d) {
		Calendar t = new GregorianCalendar(y, m - 1, d);
		return t.getTime();
	}

	/**
	 * Get year of the input date in form of "yyyy".
	 * 
	 * @param date
	 *            Date -- input date
	 * @return int -- year of date, yyyy
	 */
	public static int getYear(final Date date) {
		DateFormat fyear = new SimpleDateFormat("yyyy");
		return Integer.parseInt(fyear.format(date).toString());
	}

	/**
	 * Get month of the input date in form of "mm".
	 * 
	 * @param date
	 *            Date -- input date
	 * @return int -- month of date, mm
	 */
	public static int getMonth(final Date date) {
		DateFormat fmonth = new SimpleDateFormat("MM");
		return Integer.parseInt(fmonth.format(date).toString());
	}

	/**
	 * Get day of the input date inf form of "dd".
	 * 
	 * @param date
	 *            Date -- input date
	 * @return int -- day, dd
	 */
	public static int getDay(final Date date) {
		DateFormat fday = new SimpleDateFormat("dd");
		return Integer.parseInt(fday.format(date).toString());
	}

	/**
	 * get the day of the week,0-6 means Sunday-Saturday.
	 * 
	 * @param date
	 *            Date -- input date
	 * @return int -- day of week
	 */
	public static int getWeek(final Date date) {
		Calendar t = new GregorianCalendar();
		t.setTime(date);
		return t.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * Convert String type date to Date. e.g. "20120208" return 2012-02-08,
	 * "20121434" returns the date 2013-03-06 throw NumberFormatException when
	 * the input strDate is not a string of number. throw
	 * IndexOutOfBoundsException when the lenth of input String is not 8.
	 * 
	 * @param strDate
	 *            string of Date "yyyymmdd"
	 * @return Date
	 */
	public static Date parse(final String strDate) {
		if (strDate == null) {
			return null;
		}
		int posOfYear;
		int posOfMonth;
		int posOfDay;
		synchronized (DATE_FORMAT) {

			if (strDate.length() != DATE_FORMAT.toPattern().length()) {
				throw new IllegalArgumentException("Date string " + strDate
						+ " does not match pattern " + DATE_FORMAT.toPattern());
			}

			posOfYear = DATE_FORMAT.toPattern().indexOf("yyyy");
			posOfMonth = DATE_FORMAT.toPattern().indexOf("MM");
			posOfDay = DATE_FORMAT.toPattern().indexOf("dd");
		}
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Integer.parseInt(strDate.substring(posOfYear, posOfYear + 4)),
				Integer.parseInt(strDate.substring(posOfMonth, posOfMonth + 2)) - 1,
				Integer.parseInt(strDate.substring(posOfDay, posOfDay + 2)));
		return cal.getTime();
	}

	/**
	 * 
	 * Formats a Date into a date string.
	 * 
	 * @param date
	 *            the time value to be formatted into a date string in pattern
	 *            yyyy-MM-dd.
	 * @return the formatted date string.
	 */
	public static String toString(final Date date) {
		synchronized (DATE_FORMAT) {
			return DATE_FORMAT.format(date);
		}
	}

	/**
	 * Get days between time. Return negative value when the endDay is before
	 * startDay. It will be counted 1 if the interval time is less than one day.
	 * 
	 * @param startDay
	 *            Date
	 * @param endDay
	 *            Date
	 * @return int days between time
	 */
	public static int getIntervalDay(final Date startDay, final Date endDay) {
		Long intervalMillis = endDay.getTime() - startDay.getTime();
		Long dayMillis = 1000L * 60L * 60L * 24L;
		if ((intervalMillis % dayMillis) != 0) {
			return (int) (convertLong2Day(intervalMillis) + 1L);
		} else {
			return (int) (convertLong2Day(intervalMillis));
		}
	}

	/**
	 * @function convert the 1/1000 seconds data between two dates into days
	 *           between two dates
	 * @param interMiliSec
	 *            of long type, 1/1000 seconds of the difference between two
	 *            days
	 * @return days between the two dates
	 */
	public static double convertLong2Day(final long interMiliSec) {
		return interMiliSec / 24.0 / 60 / 60 / 1000;
	}

	/**
	 * @function convert the 1/1000 seconds data between two dates into years
	 *           between two dates
	 * @param interMiliSec
	 *            of long type, 1/1000 seconds of the difference between two
	 *            dates
	 * @return years between the two dates, convertLong2Day / 365
	 */
	public static double convertLong2Year(final long interMiliSec) {
		return convertLong2Day(interMiliSec) / 365.0;
	}

	/**
	 * get days between times. throw InvalidDataTypeException, when the
	 * dimension of the input arrays is mismatched.
	 * 
	 * @param startDate
	 *            Date[]
	 * @param endDate
	 *            Date[]
	 * @return int[] days between startDates and endDates
	 */
	public static int[] getIntervalDay(final Date[] startDate,
			final Date[] endDate) {
		if (startDate.length != endDate.length) {
			log.error("dimension mismatch");
//			throw new InvalidDataTypeException(
//					ErrorCode.INVALID_PARAMETER_VALUE, "dimension mismatch");
		}

		int[] iDays = new int[startDate.length];

		for (int i = 0; i < startDate.length; i++) {
			iDays[i] = DateUtils.getIntervalDay(startDate[i], endDate[i]);
		}
		return iDays;
	}

	/**
	 * add by month. Special case: end of month will output the end of the new
	 * month. e.g. 8.31+1=9.30
	 * 
	 * @param date
	 *            Date
	 * @param counts
	 *            int
	 * @return Date
	 */
	public static Date addMonth(final Date date, final int counts) {
		Calendar t = new GregorianCalendar();
		t.setTime(date);
		t.add(Calendar.MONTH, counts);
		return t.getTime();
	}

	/**
	 * add by year. Special case: end of month will output the end of the same
	 * month. e.g. 2012.2.29+1=2013.2.28
	 * 
	 * @param date
	 *            Date
	 * @param counts
	 *            int
	 * @return Date
	 */
	public static Date addYear(final Date date, final int counts) {
		Calendar t = new GregorianCalendar();
		t.setTime(date);
		t.add(Calendar.YEAR, counts);
		return t.getTime();
	}

	/**
	 * add by day.
	 * 
	 * @param date
	 *            Date
	 * @param counts
	 *            int
	 * @return Date
	 */
	public static Date addDay(final Date date, final int counts) {
		Calendar t = new GregorianCalendar();
		t.setTime(date);
		t.add(Calendar.DATE, counts);
		return t.getTime();
	}

	/**
	 * Return true when the day of the date is weekend(Saturday and Sunday).
	 * 
	 * @param date
	 *            Date
	 * @return boolean true if the input date is weekend
	 */
	public static boolean checkWeekend(final Date date) {
		Calendar t = new GregorianCalendar();
		t.setTime(date);
		if (t.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY

		|| t.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		}
		return false;
	}

	/**
	 * Return true when the date is one of holiday, weekend included.
	 * 
	 * @param date
	 *            Date
	 * @return boolean true if the input is holiday
	 */
	public static boolean checkHoliday(final Date date) {
		if (checkWeekend(date)) {
			return true;
		}

		// TODO more holiday need to be checked

		return false;
	}

	/**
	 * Return the date of the next working day after the input date if the date
	 * is not a working day, else return the input date.
	 * 
	 * @param date
	 *            Date
	 * @return Date -- next working day if the date is not a working day, else
	 *         return the input date
	 */
	public static Date getNextWorkingDay(final Date date) {
		Date nextDate = addDay(date, 1);

		while (checkHoliday(nextDate)) {
			nextDate = addDay(nextDate, 1);
		}
		return nextDate;
	}

	/**
	 * Return the date of the working day before the input date if the date is
	 * not a working day, else return the input date.
	 * 
	 * @param date
	 *            Date
	 * @return Date
	 */
	public static Date getPreviousWorkingDay(final Date date) {
		Date nextDate = addDay(date, -1);

		while (checkHoliday(nextDate)) {
			nextDate = addDay(nextDate, -1);
		}
		return nextDate;
	}

	/**
	 * Return the end date of the month of the input date.
	 * 
	 * @param date
	 *            Date
	 * @return Date
	 */
	public static Date getEndOfMonth(final Date date) {
		return addDay(getStartOfMonth(addMonth(date, 1)), -1);
	}

	/**
	 * Return the start date of the month of the input date.
	 * 
	 * @param date
	 *            Date
	 * @return Date
	 */
	public static Date getStartOfMonth(final Date date) {
		Calendar t = new GregorianCalendar();
		t.setTime(date);
		t.set(Calendar.DATE, 1);
		return t.getTime();
	}

	/**
	 * Return the numbers of days from current date to end dates (in pairs) If
	 * endDate < currentDate return a negative int number.
	 * 
	 * @param currentDate
	 *            Date -- current date
	 * @param endDate
	 *            Date[] -- end dates
	 * @return int[] -- numbers of days
	 */
	public static int[] getIntervalDays(final Date currentDate,
			final Date[] endDate) {
		int[] intervalDays = new int[endDate.length];
		for (int i = 0; i < endDate.length; i++) {
			intervalDays[i] = getIntervalDay(currentDate, endDate[i]);
		}
		return intervalDays;
	}

	/**
	 * return the sub date array from startIndex to endIndex. (0 < startIndex <=
	 * endIndex < lenth) else throw InvalidDataTypeException
	 * 
	 * @param date
	 *            Date[] -- date array
	 * @param startIndex
	 *            int -- start index (min=0)
	 * @param endIndex
	 *            int -- end index(max=array.length-1)
	 * @return Date[] -- the sub date array
	 */
	public static Date[] getSubDates(final Date[] date, final int startIndex,
			final int endIndex) {

		if (startIndex < 0) {
			log.error("start position error:" + startIndex);
//			throw new InvalidDataTypeException(
//					ErrorCode.INVALID_PARAMETER_VALUE, "start position error:"
//							+ startIndex);
		}

		if (endIndex < startIndex || endIndex > date.length - 1) {
			log.error("end position error:" + endIndex);
//			throw new InvalidDataTypeException(
//					ErrorCode.INVALID_PARAMETER_VALUE, "end position error:"
//							+ endIndex);
		}

		Date[] subDate = new Date[endIndex - startIndex + 1];
		for (int i = 0; i < subDate.length; i++) {
			subDate[i] = date[i + startIndex];
		}
		return subDate;
	}

	public static boolean isLeapYear(Date date) {
		int year = DateUtils.getYear(date);
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param startDate
	 *            ,
	 * @param endDate
	 *            ,
	 * @return ,
	 */
	@SuppressWarnings("deprecation")
	public static int getIntervalMonths(Date startDate, Date endDate) {

		return 12 * (endDate.getYear() - startDate.getYear())
				- endDate.getMonth() + startDate.getMonth();
	}
}
