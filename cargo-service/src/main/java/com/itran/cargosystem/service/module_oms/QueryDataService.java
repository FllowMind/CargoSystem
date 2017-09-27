package com.itran.cargosystem.service.module_oms;


import com.itran.cargosystem.entity.vo.FlightDateVo;

/**
* 获取数据Service
*  
* @author lsf  
* @date 2017年7月13日  新建  
*/
public interface QueryDataService {
	
	/**
	 * 
	 * 根据vo生成的url,查询数据
	 * @date 2017年7月13日 上午10:29:39 
	 * @return String
	 * @throws  
	 */
	public String queryData(FlightDateVo fliDateVo);

}
  