package com.itran.cargosystem.function.quartz;

import com.itran.cargosystem.entity.vo.Flight;
import com.itran.cargosystem.entity.vo.FlightDateVo;
import com.itran.cargosystem.common.util.date.FormatTime;
import com.itran.cargosystem.service.CacheByMapService;
import com.itran.cargosystem.service.FlightSegmentService;
import com.itran.cargosystem.service.SplicingDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


/**  
* 定时获取航班数据 
*  
* @author lsf  
* @date 2017年7月28日  新建  
*/
public class GenerateInterfaceDateQuartz {
	@Autowired
	SplicingDataService splicingDataService;
	@Autowired
	CacheByMapService cacheByMapService;
	@Autowired
	FlightSegmentService flightSegmentService;
	public void executeTasks() throws Exception{
		List<Flight> newflights =new ArrayList<Flight>();
		FlightDateVo fliDateVo = new FlightDateVo();
		fliDateVo.setStartFdate(FormatTime.TodayDate());
		fliDateVo.setEndFdate(FormatTime.TodayDate());
		newflights = splicingDataService.queryFligh(fliDateVo);
		newflights = splicingDataService.splicingFligh(newflights,fliDateVo.getStartFdate());
		//新旧缓存对比
		//TODO:现在的实现没有对比这个功能，考虑用两个字段，一个表示是第一次的操作，一次是定时器的操作。如果是第一次的话，执行一次查找，保证是第一次操作的数据是最新的但是会很慢,不是的话就用缓存的。
		
		//添加至缓存
		cacheByMapService.addCacheMap(newflights,FormatTime.TodayDate());
		//添加页面显示标识符
		cacheByMapService.addShowIndex(newflights);
		
	}
}
  