package com.itran.cargosystem.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

/**
 * UTC国际协调时工具类，UTC时间与国际时相差不到1秒，默认为相同，UTC+1即需要国际时+1
 * @author Administrator
 *
 */
public class UTCTimeUtil {

	private  Map<String,String> uTCMap=new LinkedHashMap<String,String>();
	private static UTCTimeUtil uTCTimeUtil;
	private String defaulTime;
	private final String UTCTIMECODE="UTCTIMECODE";
	private int gmt0RawOffset;
	private int defaulGMTRawOffset;
	private Calendar calendar;
	
	private UTCTimeUtil(){
		uTCMap.put("UTC+12", "GMT+12");
		uTCMap.put("UTC+11", "GMT+11");
		uTCMap.put("UTC+10", "GMT+10");
		uTCMap.put("UTC+9", "GMT+9");
		uTCMap.put("UTC+8", "GMT+8");
		uTCMap.put("UTC+7", "GMT+7");
		uTCMap.put("UTC+6", "GMT+6");
		uTCMap.put("UTC+5", "GMT+5");
		uTCMap.put("UTC+4", "GMT+4");
		uTCMap.put("UTC+3", "GMT+3");
		uTCMap.put("UTC+2", "GMT+2");
		uTCMap.put("UTC+1", "GMT+1");
		uTCMap.put("UTC", "GMT+0");
		uTCMap.put("UTC-1", "GMT-1");
		uTCMap.put("UTC-2", "GMT-2");
		uTCMap.put("UTC-3", "GMT-3");
		uTCMap.put("UTC-4", "GMT-4");
		uTCMap.put("UTC-5", "GMT-5");
		uTCMap.put("UTC-6", "GMT-6");
		uTCMap.put("UTC-7", "GMT-7");
		uTCMap.put("UTC-8", "GMT-8");
		uTCMap.put("UTC-9", "GMT-9");
		uTCMap.put("UTC-10", "GMT-10");
		uTCMap.put("UTC-11", "GMT-11");
		uTCMap.put("UTC-12", "GMT-12");
		

		Properties p = PropertiesUtil.getProperties();
		if(p!=null){
			defaulTime = p.getProperty(UTCTIMECODE);
			if(defaulTime==null||"".equals(defaulTime)){
				defaulTime="GMT+8";
			}
		}
		calendar = Calendar.getInstance();
		gmt0RawOffset=TimeZone.getTimeZone("GMT+0").getRawOffset();
		defaulGMTRawOffset=TimeZone.getTimeZone(defaulTime).getRawOffset();
	}
	
	/**
	 * 获取UTC工具类
	 * @return
	 */
	public synchronized static UTCTimeUtil getInstance(){
		if(uTCTimeUtil==null){
			uTCTimeUtil=new UTCTimeUtil();
		}
		return uTCTimeUtil;
	}
	public Map<String,String> getUTCMap(){
		return uTCMap;
	}
	public String getGMTByKey(String key){
		return uTCMap.get(key);
	}

	public String getDefaulTime() {
		return defaulTime;
	}

	/**
	 * 设置时区
	 * @param defaulTime
	 */
	public void setDefaulTime(String defaulTime) {
		if(defaulTime!=null&&!"".equals(defaulTime)){
			Properties p = PropertiesUtil.getProperties();
			p.setProperty("UTCTIMECODE", defaulTime);
			PropertiesUtil.storeProperties(p);
			this.defaulTime = defaulTime;	
			defaulGMTRawOffset=TimeZone.getTimeZone(defaulTime).getRawOffset();
		}
	}
	
	/**
	 * 转成国际时间
	 * @param date
	 * @return
	 */
	public Date toGMT0Date(Date date){
		calendar.setTime(date);
		calendar.add(Calendar.MILLISECOND,gmt0RawOffset-defaulGMTRawOffset);

		return calendar.getTime();
	}
	
	/**
	 * 转成默认时区时间
	 * @param date
	 * @return
	 */
	public Date toDefaulDate(Date date){
		calendar.setTime(date);
		calendar.add(Calendar.MILLISECOND,defaulGMTRawOffset-gmt0RawOffset);

		return calendar.getTime();
	}
}
