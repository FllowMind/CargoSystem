package com.itran.cargosystem.service.module_oms.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.itran.cargosystem.entity.FlightSegment;
import com.itran.cargosystem.entity.vo.Flight;
import com.itran.cargosystem.common.Cache.CacheMgr;
import com.itran.cargosystem.service.module_oms.CacheByMapService;
import org.springframework.stereotype.Service;


/**  
* 类说明   
*  
* @author lsf  
* @date 2017年8月1日  新建  
*/
@Service
public class CacheByMapServiceImpl implements CacheByMapService {

	@Override
	public void addCacheMap(List<Flight> newFlights, String startFdate){
		CacheMgr cm=CacheMgr.getInstance();
		Map<String, Flight> newflightCacheMap = new LinkedHashMap<String, Flight>();
		//每条航班都放入缓存(k=日期,y=航班集合)
		for(int i = 0;i<newFlights.size();i++){
			newflightCacheMap.put(newFlights.get(i).getSerialno(), newFlights.get(i));
		}
		cm.addCache(startFdate, newflightCacheMap);
		cm.addCache("flightDataUpdate", true);
	}

	@Override
	public void addShowIndex(List<Flight> newFlights) {
		CacheMgr cm=CacheMgr.getInstance(); 
		List<Flight> flights = new ArrayList<Flight>();
		flights = newFlights;
		for(int i = 0;i<flights.size();i++){
			//航班集合是有序的，级别由高到低
			if(flights.get(i).getLevel()<11){//只要第一个没完成的航班下标
				cm.addCache("index", i+1);
				return;
			}
		}
	}

	@Override
	public void addFlightSegmentsCacheMap(List<FlightSegment> flightSegments) {
		CacheMgr cm=CacheMgr.getInstance(); 
		Map<String, FlightSegment> flightSegmentCacheMap = new HashMap<String, FlightSegment>();
		for(int i = 0;i<flightSegments.size();i++){
			flightSegmentCacheMap.put(flightSegments.get(i).getFlightType(), flightSegments.get(i));
		}
		cm.addCache("FlightSegment", flightSegmentCacheMap);
	}

}
  