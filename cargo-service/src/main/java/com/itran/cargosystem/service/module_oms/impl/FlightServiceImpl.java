package com.itran.cargosystem.service.module_oms.impl;

import java.util.ArrayList;

import java.util.List;


import com.itran.cargosystem.entity.vo.Flight;
import com.itran.cargosystem.entity.vo.FlightDateVo;
import com.itran.cargosystem.common.util.josn.JosnUtil;
import com.itran.cargosystem.service.module_oms.FlightService;
import com.itran.cargosystem.service.module_oms.QueryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 航班serviceImpls
 * 
 * @author lsf
 * @date 2017年7月13日 新建
 */
@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	public QueryDataService queryDataService;

	@Override
	public List<Flight> queryFlight(FlightDateVo fliDateVo) throws Exception{
		String jsonString = queryDataService.queryData(fliDateVo);
		jsonString = jsonString.toLowerCase();//小写
		JSONObject jsonObj = JSONObject.fromObject(jsonString);
		// 去null,替换为""
		List<Flight> allFlightMsg = new ArrayList<Flight>();
		ObjectMapper mapper = new ObjectMapper();  
		jsonObj = JosnUtil.filterNull(jsonObj);
		//json数据异常的时候
		if(!jsonObj.containsKey("retentity")){
			return allFlightMsg;
		}
		JSONArray retEntity = jsonObj.getJSONArray("retentity");
		for (int i = 0; i < retEntity.size(); i++) {
			// 获取JSONArray 里面的 JsonObject对象
			JSONObject jsObj = retEntity.getJSONObject(i);
			//Json对象转fligh对象
			String jsStr = jsObj.toString();
			Flight flight = mapper.readValue(jsStr, Flight.class);
			allFlightMsg.add(flight);
		}
		return allFlightMsg;
	}
}
