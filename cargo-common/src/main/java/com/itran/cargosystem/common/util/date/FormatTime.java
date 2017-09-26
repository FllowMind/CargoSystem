package com.itran.cargosystem.common.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 类说明
 * 
 * @author lsf
 * @date 2017年7月17日 新建
 */
public class FormatTime {

	/*	*//**
			 * 转成前端显示用的格式
			 * 
			 * @param str
			 * @return date
			 * @throws ParseException
			 */
	/*
	 * public static String StrToDate(String str) throws ParseException {
	 * 
	 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 * Date date = null; try { date = format.parse(str); } catch (ParseException
	 * e) { e.printStackTrace(); } Calendar calendar = Calendar.getInstance();
	 * String dateFormat = format.format(new Date());
	 * calendar.setTime(format.parse(dateFormat));
	 * 
	 * return null; }
	 * 
	 * 
	 *//**
		 * 获取今天的年月日  * @date 2017年7月31日 下午4:08:08   @return String  * @throws  
		 *//*
		 * public static String TodayDate(){ Date d = new Date();
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d"); String
		 * dateNowStr = sdf.format(d); return dateNowStr; }
		 */

	public static Date transferDateTime(String dateStr) throws ParseException {
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		Date date = sdf.parse(dateStr);
		return date;
	}

	public static String transferDateToString(Date date) {
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dateStr = sdf.format(date);
		return dateStr;
	}

	/**
	 * 获取今天的日期
	 * @return
	 */
	public static String TodayDate() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		String dateNowStr = sdf.format(d);
		return dateNowStr;
	}
	
	/**
	 * 时间添加日期
	 * @param hms
	 * @return
	 */
	public static String HMSJointYMD(String hms) {
		String ymd = TodayDate();
		String dateStr = ymd + hms + ":00";
		return dateStr;
	}
	
	/**
	 * 时间添加分钟
	 * @param dateStr
	 * @param minute
	 * @return
	 * @throws ParseException
	 */
	public static String dateAddMinute(String dateStr, int minute) throws ParseException {
		Date date = transferDateTime(dateStr);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date);
		calendar2.add(12, minute);
		String newDate = transferDateToString(calendar2.getTime());
		return newDate;
	}
	
	/**
	 * 获取时间的时分
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static String getTimeByDateString(String dateStr) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = formatter.parse(dateStr);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		String timeStr = format.format(date);
		return timeStr;
	}
	
	/**
	 * 获取参数的第二天
	 * @param today
	 * @return
	 */
	public static Date getTomorrowByDate(Date today){ 
        Calendar c = Calendar.getInstance();  
        c.setTime(today);  
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天  
        Date tomorrow = c.getTime();
		return tomorrow;  
	}

}
