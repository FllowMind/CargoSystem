package com.itran.cargosystem.service.module_oms;

import com.itran.cargosystem.entity.vo.Flight;
import com.itran.cargosystem.entity.vo.FlightDateVo;

import java.util.List;

/**  
* 数据拼接service
*  
* @author lsf  
* @date 2017年7月17日  新建  
*/
public interface SplicingDataService {
	/**
	 * 查询航班和对应节点，并封装航班
	 * @date 2017年7月17日 上午11:35:55 
	 * @return List<Flight>
	 * @throws  
	 */
	public List<Flight> queryFligh(FlightDateVo fliDateVo)throws Exception ;
	
	
	/**
	 * 航班封装成联程航班
	 * @date 2017年7月17日 上午11:35:55 
	 * @return List<Flight>
	 * @throws  
	 */
	public List<Flight> splicingFligh(List<Flight> flights, String dateDay)throws Exception ;


}
  