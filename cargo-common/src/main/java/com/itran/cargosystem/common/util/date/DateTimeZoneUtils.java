package com.itran.cargosystem.common.util.date;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**  
* 时区工具类
*  
* @author lsf  
* @date 2017年6月26日  新建  
*/
public class DateTimeZoneUtils {
	/** 
	 * 任意时区转换,但是需要两个参数,获取更改时区后的日期 ,
	 * @param date 日期 
	 * @param oldZone 旧时区对象 
	 * @param newZone 新时区对象 
	 * @return 日期 
	 */  
	public static Date changeTimeZone(Date date, TimeZone oldZone, TimeZone newZone) {  
	    Date dateTmp = null;  
	    if (date != null) {  
	        int timeOffset = oldZone.getRawOffset() - newZone.getRawOffset();  
	        dateTmp = new Date(date.getTime() - timeOffset);  
	    }  
	    return dateTmp;  
	}  
	
	/**
	 * 各用户的本地时间转换成0时区的时间
	 * @param date 各用户的本地时间
	 * @return GMTTime零时区时间
	 */
	public static Date getGMTTime(Date date){
		Date GMTTime = null;  
		//服务器当前时区
		 Calendar cal = Calendar.getInstance();
		 TimeZone oldZone = cal.getTimeZone();
		//0时区
		 TimeZone newZone =  TimeZone.getTimeZone("GMT");
		 if (date != null) {  
		        int timeOffset = oldZone.getRawOffset() - newZone.getRawOffset();  
		        GMTTime = new Date(date.getTime() - timeOffset);  
		    }  
		return GMTTime;  
	}
	
	/**
	 * 用法参考
	 */
	public static void main(String[] args) {  
	    Date date = new Date();
	    Date date2 = new Date();
	    //任意时区转换，但是需要两个参数
	    date = changeTimeZone(date, TimeZone.getTimeZone("Asia/Shanghai"), TimeZone.getTimeZone("GMT"));  
	    System.out.println("新时区时间："+date);  
	    //任意用户当前时间对应的零时区
	    date = getGMTTime(date2);
	    System.out.println("零时区时间："+date);  
	    
	}  
	
}
  