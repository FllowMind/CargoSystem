package com.itran.cargosystem.service;

import com.itran.cargosystem.bean.vo.FlightDateVo;
import com.itran.cargosystem.bean.vo.FlightMonitor;

import java.util.List;


/**  
* 航班节点Servi
*  
* @author lsf  
* @date 2017年7月13日  新建  
*/
public interface FlightMonitorService {
	/**
	 * 
	 * 获取所有航班的节点
	 * @date 2017年7月13日 上午10:50:22 
	 * @return Flight
	 * @throws 
	 */
	public List<FlightMonitor>  queryFlightMonitor(FlightDateVo fliDateVo)throws Exception;
}
  