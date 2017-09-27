package com.itran.cargosystem.service.module_oms;

import com.itran.cargosystem.entity.vo.Flight;
import com.itran.cargosystem.entity.vo.FlightDateVo;

import java.util.List;


/**  
* 航班service
*  
* @author lsf  
* @date 2017年7月13日  新建  
*/
public interface FlightService {
	
	/**
	 * 
	 * 获取航班信息
	 * @date 2017年7月13日 上午10:50:22 
	 * @return Flight
	 * @throws 
	 */
	public List<Flight>  queryFlight(FlightDateVo fliDateVo)throws Exception;

}
  