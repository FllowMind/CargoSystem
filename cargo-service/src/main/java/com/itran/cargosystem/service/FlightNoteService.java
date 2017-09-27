package com.itran.cargosystem.service;


import com.itran.cargosystem.entity.FlightNote;

/**
* 航班组备注Service
*  
* @author lsf  
* @date 2017年7月18日  新建  
*/
public interface FlightNoteService {
	
	/**
	 * 查询日期备注并直接添加到缓存里
	 * @date 2017年7月18日 下午2:44:02 
	 * @return List<FlightNote>
	 */
	public void NotesPutCacheByDate(String date) throws Exception ; 
	
	
	/**
	 * 保存航班组备注
	 * @date 2017年7月19日 下午3:32:42 
	 * @return int
	 * @throws  
	 */
	public int SaveNote(FlightNote flightNote) throws Exception;
}
  