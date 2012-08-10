package com.pccw.ehunter.utility;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

	public static int WEEK_DAY_NUM = 7;

	/**
	 * Cut time on the Date object including hour, min, sec and msec and return
	 * a new object contains year, month and day only.
	 * 
	 * @param date
	 *            The date to be trimmed
	 * @param unit
	 *            see Calendar.HOUR_OF_DAY, MINUTE, SECOND, etc
	 * @return
	 */
	public static Date trimDate(Date date, int unit) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			return trimDate(cal, unit).getTime();
		} else {
			return null;
		}
	}

	public static Calendar trimDate(Calendar cal, int unit) {
		Calendar calendar = (Calendar) cal.clone();

		switch (unit) {
		case Calendar.YEAR:
			calendar.set(Calendar.MONTH, 0);

		case Calendar.MONTH:
			calendar.set(Calendar.DAY_OF_MONTH, 0);

		case Calendar.DAY_OF_MONTH:
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 0);

		case Calendar.HOUR_OF_DAY:
			calendar.set(Calendar.MINUTE, 0);

		case Calendar.MINUTE:
			calendar.set(Calendar.SECOND, 0);

		case Calendar.SECOND:
			calendar.set(Calendar.MILLISECOND, 0);

		case Calendar.MILLISECOND:

			// clear nothing
			break;
		}

		return calendar;
	}

	public static String formatDateTime(String format, Date date) {
		if(date == null){
			return "";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	 public static String formatNetricsDate(String formate,Date d){
	    	SimpleDateFormat sdf = new SimpleDateFormat(formate, Locale.ENGLISH);
	    	return sdf.format(d);
	    }
	/**
	 * Return a proper formatted date string conform to Netrics Date
	 * standard(US)
	 * 
	 * <p>
	 * Notes: 1. Netrics date format follows US Locales
	 * 
	 * @param d
	 *            - any date
	 * @return String of the date comform to Netrics Standard
	 */
	public static String formatNetricsDate(Date d) {
		// UAT 3389, 24-APR-2012, KL
		//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.US);
		return sdf.format(d);
	}

	public static Calendar changeTimeZone(Calendar date, TimeZone timezone) {
		if (!date.getTimeZone().equals(timezone)) {
			Calendar newDate = Calendar.getInstance(timezone);

			newDate.setTimeInMillis(date.getTimeInMillis());

			return newDate;
		}

		return date;
	}

	/**
	 * used computer default timezone
	 */
	public static Calendar changeTimeZone(Calendar date) {
		return changeTimeZone(date, TimeZone.getDefault());
	}

	/**
	 * pass object to automatically change call the Calendar fields timezone and
	 * not trim the time
	 * 
	 * @param object
	 * @param timeZone
	 * @throws IntrospectionException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void changeInstanceFieldsTimeZone(Object object,
			TimeZone timeZone) throws IntrospectionException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

		for (PropertyDescriptor pd : pds) {
			if (pd.getPropertyType().equals(Calendar.class)
					&& (pd.getReadMethod().getParameterTypes().length == 0)) {
				Method getter = pd.getReadMethod();
				Method setter = pd.getWriteMethod();

				Calendar orginalc = (Calendar) getter.invoke(object, null);

				if (orginalc != null) {
					setter.invoke(object, new Object[] { changeTimeZone(
							orginalc, timeZone) });
				}
			}
		}
	}

	public static void changeInstanceFieldsTimeZone(Object object)
			throws IllegalArgumentException, IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		changeInstanceFieldsTimeZone(object, TimeZone.getDefault());
	}

	public static Date parse(String format, String dateString)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setLenient(false);
		return sdf.parse(dateString);
	}

	public static Calendar toCalendar(Date date) {
		if (date == null) {
			return null;
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			return cal;
		}
	}

	public static String toString(Calendar cal) {
		if (cal == null) {
			return null;
		} else {
			return "{" + cal.getTime().toString() + ", locale="
					+ cal.getTimeZone().getDisplayName() + "}";
		}
	}

	public static String toString(ArrayList<Calendar> cals) {
		if (cals == null) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		sb.append(cals.getClass().getSimpleName());
		sb.append("{");

		for (int i = 0; i < cals.size(); i++) {
			sb.append(toString(cals.get(i)));

			if (i < (cals.size() - 1)) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	public static int getDayOfWeek(Date date) {
		return toCalendar(date).get(Calendar.DAY_OF_WEEK);
	}

	public static String toString(Calendar[] cals) {
		if (cals == null) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		sb.append(cals.getClass().getSimpleName());
		sb.append("{");

		for (int i = 0; i < cals.length; i++) {
			sb.append(toString(cals[i]));

			if (i < cals.length) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	public static Date parseDate(String yyyy, String mm, String dd)
			throws ParseException,NumberFormatException {
		Date bdate = null;

		if (yyyy == null || yyyy.trim().length() == 0)
			return null;
		if (mm == null || mm.trim().length() == 0)
			return null;
		if (dd == null || dd.trim().length() == 0)
			return null;
		if (mm.length() == 1)
			mm = "0" + mm;
		if (dd.length() == 1)
			dd = "0" + dd;

		try {
			Integer.parseInt(yyyy);
			Integer.parseInt(mm);
			Integer.parseInt(dd);
			bdate = parseDate(yyyy + "-" + mm + "-" + dd, "yyyy-MM-dd");
		}catch(NumberFormatException e){
			throw e;
		}catch (ParseException e) {
			throw e;
		}
		return bdate;
	}

	public static Date parseDate(String strdate, String format)
			throws ParseException {
		Date bdate = null;
		try {
			if (null == strdate) {
				return bdate;
			}
			SimpleDateFormat dFormat = new SimpleDateFormat(format);
			dFormat.setLenient(false);
			bdate = new Date(dFormat.parse(strdate).getTime());
			// Sys.log.printDebug("Convert StringToDate : " + bdate);
		} catch (ParseException e) {

			throw e;
		}
		return bdate;
	}

	public static Date parseDate(String yyyy, String MM, String dd, String HH,
			String mm, String ss) throws ParseException {
		Date bdate = null;

		if (yyyy == null || yyyy.trim().length() == 0)
			return null;
		if (MM == null || MM.trim().length() == 0)
			return null;
		if (dd == null || dd.trim().length() == 0)
			return null;
		if (HH == null || HH.trim().length() == 0)
			return null;
		if (mm == null || mm.trim().length() == 0)
			return null;
		if (ss == null || ss.trim().length() == 0)
			return null;
		if (MM.length() == 1)
			MM = "0" + MM;
		if (dd.length() == 1)
			dd = "0" + dd;
		if (HH.length() == 1)
			HH = "0" + HH;
		if (mm.length() == 1)
			mm = "0" + mm;
		if (ss.length() == 1)
			ss = "0" + ss;

		try {
			bdate = parseDate(yyyy + "-" + MM + "-" + dd + " " + HH + ":" + mm
					+ ":" + ss, "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			throw e;
		}
		return bdate;
	}
	
	public static Date parseDate(String yyyy, String MM, String dd, String HH,
			String mm) throws ParseException,NumberFormatException {
		Date bdate = null;

		if (yyyy == null || yyyy.trim().length() == 0)
			return null;
		if (MM == null || MM.trim().length() == 0)
			return null;
		if (dd == null || dd.trim().length() == 0)
			return null;
		if (HH == null || HH.trim().length() == 0)
			return null;
		if (mm == null || mm.trim().length() == 0)
			return null;
		if (MM.length() == 1)
			MM = "0" + MM;
		if (dd.length() == 1)
			dd = "0" + dd;
		if (HH.length() == 1)
			HH = "0" + HH;
		if (mm.length() == 1)
			mm = "0" + mm;

		try {
			Integer.parseInt(yyyy);
			Integer.parseInt(MM);
			Integer.parseInt(dd);
			Integer.parseInt(HH);
			Integer.parseInt(mm);
			bdate = parseDate(yyyy + "-" + MM + "-" + dd + " " + HH + ":" + mm
					, "yyyy-MM-dd HH:mm");
		}catch(NumberFormatException e){
			throw e;
		}catch (ParseException e) {
			throw e;
		}
		return bdate;
	}

	// 计算两个任意时间中间的间隔天数
	public static int getIntervalDays(Date startday, Date endday) {
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (int) (ei / (1000 * 60 * 60 * 24));
	}
	
	public static Date getSystemDate () {
		return new Date();
	}
	public static String formatUsDateTime(String format, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.US);

		return sdf.format(date);
	}
	
	public static boolean checkDateFormat (String dateStringToCheck, String format)
			throws ParseException {
		
		boolean isDateFormatCorrect;
		
		try{
			parseDate(dateStringToCheck, format);
			isDateFormatCorrect = true;
		} catch (ParseException e){
			isDateFormatCorrect = false;
			throw e;
		}
		
		return isDateFormatCorrect;
		
	}
	
	/*
	 * Compare two dates with year, month and date but ignore time
	 *  compareDateIgnoreTime(Date startDate, Date endDate)
	 *  -1 : startDate > endDate
	 *   0 : startDate == endDate
	 *   1 : startDate < endDate
	 */
	public static int compareDateIgnoreTime(Date startDate, Date endDate) {
		Calendar calStartDate = Calendar.getInstance();
		Calendar calEndDate = Calendar.getInstance();
		calStartDate.setTime(startDate);
		calEndDate.setTime(endDate);
		int yearOfStartDate = calStartDate.get(Calendar.YEAR);
		int yearOfEndDate = calEndDate.get(Calendar.YEAR);
		int monthOfStartDate = calStartDate.get(Calendar.MONTH);
		int monthOfEndDate = calEndDate.get(Calendar.MONTH);
		int dateOfStartDate = calStartDate.get(Calendar.DATE);
		int dateOfEndDate = calEndDate.get(Calendar.DATE);
		
		if(yearOfStartDate > yearOfEndDate) {
			return -1;
		}
		else if(yearOfStartDate == yearOfEndDate) {
			if(monthOfStartDate > monthOfEndDate) {
				return -1;
			}
			else if(monthOfStartDate == monthOfEndDate) {
				if(dateOfStartDate > dateOfEndDate) {
					return -1;
				}
				else if(dateOfStartDate == dateOfEndDate) {
					return 0;
				}
				else {
					return 1;
				}
			}
			else {
				return 1;
			}
		}
		else {
			return 1;
		}
	}
	
	public static Date getAfterDays (Date date, int after) {
		Calendar cal = DateUtils.toCalendar( date );
		cal.add( Calendar.DAY_OF_MONTH, after );
		return cal.getTime();
	}
	
}
