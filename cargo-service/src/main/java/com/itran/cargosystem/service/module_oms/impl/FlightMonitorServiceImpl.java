package com.itran.cargosystem.service.module_oms.impl;


import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import com.itran.cargosystem.entity.vo.FlightDateVo;
import com.itran.cargosystem.entity.vo.FlightMonitor;
import com.itran.cargosystem.common.util.josn.JosnUtil;
import com.itran.cargosystem.service.module_oms.FlightMonitorService;
import com.itran.cargosystem.service.module_oms.QueryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**  
* 类说明   
*  
* @author lsf  
* @date 2017年7月13日  新建  
*/
@Service
public class FlightMonitorServiceImpl implements FlightMonitorService {
	@Autowired
	public QueryDataService queryDataService;

	@Override
	public List<FlightMonitor> queryFlightMonitor(FlightDateVo fliDateVo) throws Exception{
		// 查询所有节点
		String jsonString = queryDataService.queryData(fliDateVo);
		jsonString = jsonString.toLowerCase();//小写
		JSONObject jsonObj = JSONObject.fromObject(jsonString);
		// 去null,替换为""
		jsonObj = JosnUtil.filterNull(jsonObj);
		//json数据异常的时候
		if(!jsonObj.containsKey("retentity")){
			return Collections.emptyList();
		}
		List<FlightMonitor> allFlightMonitorMsg = new ArrayList<FlightMonitor>();
		ObjectMapper mapper = new ObjectMapper();  
		JSONArray retEntity = jsonObj.getJSONArray("retentity");
		for (int i = 0; i < retEntity.size(); i++) {
			// 获取JSONArray 里面的 JsonObject对象
			JSONObject jsObj = retEntity.getJSONObject(i);
			String jsstr = jsObj.toString();
			//Json对象转FlightMonitor对象
			FlightMonitor flightMonitor = mapper.readValue(jsstr, FlightMonitor.class);
			allFlightMonitorMsg.add(flightMonitor);
		}
		return allFlightMonitorMsg;
		
	}

}
  