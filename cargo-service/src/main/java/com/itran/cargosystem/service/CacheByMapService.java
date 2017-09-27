package com.itran.cargosystem.service;

import com.itran.cargosystem.entity.FlightSegment;
import com.itran.cargosystem.entity.vo.Flight;

import java.util.List;


/**  
* Map缓存Service  
*  
* @author lsf  
* @date 2017年8月1日  新建  
*/
public interface CacheByMapService {
	/**
	 * 更新航班缓存为最新
	 * @date 2017年7月31日 上午11:19:37 
	 * @return void
	 * @throws  
	 */
	public void addCacheMap(List<Flight> newFlights, String StartFdate);
	
	/**
	 * 添加页面显示标识符
	 * @date 2017年7月31日 上午11:19:37 
	 * @return void
	 * @throws  
	 */
	public void addShowIndex(List<Flight> newFlights);
	
	/**
	 * 更新航班限制数据为最新
	 * @date 2017年7月31日 上午11:19:37 
	 * @return void
	 * @throws  
	 */
	public void addFlightSegmentsCacheMap(List<FlightSegment> flightSegments);
}
  