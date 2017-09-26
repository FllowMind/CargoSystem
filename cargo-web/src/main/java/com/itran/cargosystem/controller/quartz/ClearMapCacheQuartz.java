package com.itran.cargosystem.controller.quartz;

import com.itran.cargosystem.common.Cache.CacheMgr;
import com.itran.cargosystem.common.util.date.FormatTime;
import org.apache.log4j.Logger;


/**  
*缓存清理定时器
*  
* @author lsf  
* @date 2017年8月1日  新建  
*/
public class ClearMapCacheQuartz {
	public void executeTasks(){
		final Logger logger = Logger.getLogger(ClearMapCacheQuartz.class);
		CacheMgr cm=CacheMgr.getInstance();
		int size = cm.getSize();
		Boolean clearMap= cm.clearCache();
		if(clearMap){
			logger.info(FormatTime.TodayDate()+"前的缓存清理成功,清理前大小： "+size+" 清理后大小：  "+cm.getSize());
		}
	}
}
  