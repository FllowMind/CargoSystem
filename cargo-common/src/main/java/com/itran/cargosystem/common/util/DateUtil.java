package com.itran.cargosystem.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

	static SimpleDateFormat format = new SimpleDateFormat("ddMMMyy", Locale.ENGLISH);

	static SimpleDateFormat format3 = new SimpleDateFormat("dd/MMM/yy", Locale.ENGLISH);

	static SimpleDateFormat format1 = new SimpleDateFormat("HHmm", Locale.ENGLISH);

	static SimpleDateFormat format2 = new SimpleDateFormat("dd", Locale.ENGLISH);

	static SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd");

	static SimpleDateFormat format5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	static SimpleDateFormat format6 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	static SimpleDateFormat format7 = new SimpleDateFormat("ddHHmm");

	static SimpleDateFormat format8 = new SimpleDateFormat("ddMMM", Locale.ENGLISH);

	static SimpleDateFormat format9 = new SimpleDateFormat("yyyyMMddHHmmss");
	
	static SimpleDateFormat format10 = new SimpleDateFormat("yyyyMMddHHmm");
	
	static SimpleDateFormat format11 = new SimpleDateFormat("ddMMMyyyy",
			Locale.ENGLISH);

	static SimpleDateFormat GTM_ZONE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static SimpleDateFormat LOCATL_ZONE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	static Calendar calendar = Calendar.getInstance();

	static int offsetTimes = 0;

	static {
		GTM_ZONE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT+0"));
		// 计算当前时区与国际时间的时间差(单位为:毫秒)
		offsetTimes = TimeZone.getDefault().getRawOffset() - TimeZone.getTimeZone("GMT+0").getRawOffset();
	}

	/**
	 * 
	 * 
	 * 功能描述：把系统日期转换成国际时间(GTM)
	 * 
	 * @param date
	 * @return
	 * @author zhaoyuxin create on: 2013-6-22
	 * 
	 */
	public static Date toGTMDate(Date date) {
		// String str = GTM_ZONE_FORMAT.format(date);
		// try {
		// return LOCATL_ZONE_FORMAT.parse(str);
		// } catch (ParseException e) {
		// }
		// return new Date();
		calendar.setTime(date);
		calendar.add(Calendar.MILLISECOND, -offsetTimes);

		return calendar.getTime();
	}

	/**
	 * 
	 * 
	 * 功能描述：把国际时间(GTM)转换成本地系统时间
	 * 
	 * @param date
	 * @return
	 * @author zhaoyuxin create on: 2013-6-22
	 * 
	 */
	public static Date toLocalDate(Date date) {
		calendar.setTime(date);
		calendar.add(Calendar.MILLISECOND, offsetTimes);

		return calendar.getTime();
	}

	public static String toddMMMyy(Date date) {
		return format.format(date);
	}

	public static String toddMMMyy1(Date date) {
		return format3.format(date);
	}

	public static String toyyyyMMddhhmmss(Date date) {
		return format5.format(date);
	}

	public static String toyyyyMMddhhmmss2(Date date) {
		return format9.format(date);
	}
	
	public static String toyyyyMMddhhmm(Date date) {
		return format10.format(date);
	}

	public static String toHHmm(Date date) {
		return format1.format(date);
	}

	public static String todd(Date date) {
		return format2.format(date);
	}

	public static String toyyyyMMdd(Date date) {
		return format4.format(date);
	}

	public static String toddHHmm(Date date) {
		return format7.format(date);
	}

	public static String toddMMM(Date date) {
		return format8.format(date);
	}

	public static Date getBeforTime(Integer beforTime) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, -beforTime);// 前多少个小时
		return cal.getTime();
	}

	public static Date getBeforTime(Date date, Integer beforTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, -beforTime);// 前多少个小时
		return cal.getTime();
	}

	public static Date getAfterTime(Integer afterTime) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, afterTime);// 后多少个小时
		return cal.getTime();
	}

	public static Date toBJ(Integer afterTime, Date d) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(d);
		cal.add(Calendar.HOUR_OF_DAY, afterTime);// 后多少个小时
		return cal.getTime();
	}

	public static Date toGTM(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(d);
		cal.add(Calendar.HOUR_OF_DAY, -8);
		return cal.getTime();
	}

	/**
	 * 返回当前时间的国际时间 如 系统部署在北京时间，现在是9:00 则取当前服务器国际时间应该是1:00
	 * 
	 * @return
	 */
	public static Date getInternationalDate() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeZone(TimeZone.getTimeZone("GMT+0"));
		String dateString = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " "
				+ calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			date = new Date();
		}
		return date;
	}

	public static Date getParseDate(String dateValue, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
		try {
			return format.parse(dateValue);
		} catch (ParseException e) {
			throw new RuntimeException("Parse date error.");
		}
	}

	public static String getFormatDate(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
		return format.format(date);
	}

	/**
	 * Format yyyy-MM-dd string to date.
	 * 
	 * @param String
	 * @return Date
	 */
	public static Date stringToDate(String date) {
		return getParseDate(date, "yyyy-MM-dd");
	}

	/**
	 * 
	 * @param day
	 *            字符串 天数
	 * @return 返回当年 当月 的日期 yyyy-MM-dd
	 */
	public static String dayToString(String day) {
		if (!BlankUtil.isBlank(day) && StringUtil.strPatternNum(day) && checkDate(day, "dd")) {
			Calendar cal = Calendar.getInstance();
			StringBuffer datesb = new StringBuffer(day);
			if (day.length() == 1) {
				datesb.insert(0, 0);
			}
			datesb.insert(0, "-");
			int month = cal.get(Calendar.MONTH) + 1;
			datesb.insert(0, cal.get(Calendar.MONTH) + 1);
			if (month < 10) {
				datesb.insert(0, 0);
			}
			datesb.insert(0, "-");
			datesb.insert(0, cal.get(Calendar.YEAR));
			return datesb.toString();
		}
		return null;
	}

	/**
	 * 是否是日期 格式
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static boolean checkDate(String str, String pattern) {
		if (str != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
			sdf.setLenient(false);
			try {
				sdf.format(sdf.parse(str));
			} catch (Exception e) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * 
	 * 功能描述： 国际时间 转本地（北京）时间
	 * 
	 * @param dateStr
	 *            Tue, 14 May 2013 01:12:58 GMT
	 * @return
	 * @author liubinwang create on: 2013-5-14
	 * 
	 */
	public static Date getLocalDate(String dateStr) {
		Date date = getParseDate(dateStr, "EEE, dd MMM yyyy HH:mm:ss");
		return toLocalDate(date);
	}

	/**
	 * 
	 * 
	 * 功能描述：当天
	 * 
	 * @return
	 * @author liubinwang create on: 2013-6-20
	 * 
	 */
	public static String getNowDate() {
		return format4.format(new Date());
	}

	public static Date toyyyyMMddDate(String date) {
		try {
			return format4.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException("Parse date error.");
		}
	}

	/**
	 * 
	 * 功能描述：日期比较
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @author likeyong create on: 2013-7-11
	 * 
	 */
	public static boolean checkDateAffterDate(String beginDate, String endDate) {
		if (beginDate == null || endDate == null || "".equals(beginDate) || "".equals(endDate))
			return true;
		try {
			Date beg = format6.parse(beginDate);
			Date end = format6.parse(endDate);
			if (beg.after(end)) {
				return false;
			} else {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static Date toddMMMyyyy(String date) {
		try {
			return format11.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException("Parse date error.  " + date);
		}
	}
}
