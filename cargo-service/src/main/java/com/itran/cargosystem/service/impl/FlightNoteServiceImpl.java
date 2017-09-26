package com.itran.cargosystem.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itran.cargosystem.bean.FlightNote;
import com.itran.cargosystem.common.Cache.CacheMgr;
import com.itran.cargosystem.common.util.CommonUtils;
import com.itran.cargosystem.dao.CommonDao;
import com.itran.cargosystem.dao.util.GeneralQueryParam;
import com.itran.cargosystem.service.FlightNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**  
* 类说明   
*  
* @author lsf  
* @date 2017年7月18日  新建  
*/
@Service
public class FlightNoteServiceImpl implements FlightNoteService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public void NotesPutCacheByDate(String date) throws Exception {
		CacheMgr cm=CacheMgr.getInstance();

    	//缓存里面没有的时候添加到缓存里面。
	    //根据页面显示的日期查找，即数据库备忘的创建时间
		String conditionExp = "CREATION_DATE = #{conditionParam.CREATION_DATE}";
		Map<String, Object> conditionParam = new HashMap<String, Object>();
		conditionParam.put("CREATION_DATE", date);
		GeneralQueryParam queryParam = new GeneralQueryParam();
		queryParam.setConditionExp(conditionExp);
		queryParam.setConditionParam(conditionParam);
		
		List<FlightNote> result = commonDao.selectAdvanced(FlightNote.class, queryParam);
		if(result.size()>0){
			for(int i = 0;i<result.size();i++){
				//添加到缓存
				cm.addCache(result.get(i).getSerialno(),result.get(i));
			}
		}
	}
	@Override
	public int SaveNote(FlightNote flightNote) throws Exception {
		//先看是否有这个航班组号的备注，没有就创建，有就更新。
		String Serialno =  flightNote.getSerialno();
		String conditionExp = "SERIALNO = #{conditionParam.SERIALNO}";
		Map<String, Object> conditionParam = new HashMap<String, Object>();
		conditionParam.put("SERIALNO", Serialno);
		GeneralQueryParam queryParam = new GeneralQueryParam();
		queryParam.setConditionExp(conditionExp);
		queryParam.setConditionParam(conditionParam);
		List<FlightNote> result = commonDao.selectAdvanced(FlightNote.class, queryParam);
		
		if(result.size()==1){//如果有这个记录，就覆盖，然后更新缓存
			Map<String, Object> columnValueMapping = new HashMap<String, Object>();
			columnValueMapping.put("UPDATE_TIME", new Date());
			columnValueMapping.put("UPDATE_USER", "admin");
			columnValueMapping.put("NOTE_CONTENT", flightNote.getNoteContent());
			int i = commonDao.updateByConditionSelective(FlightNote.class, columnValueMapping, conditionExp, conditionParam);
			if(i>0){
				//覆盖记录后更新缓存为最新的记录
				List<FlightNote> newresult = commonDao.selectAdvanced(FlightNote.class, queryParam);
				CacheMgr cm=CacheMgr.getInstance();  
				cm.addCache(Serialno, newresult.get(0));
			}
		}else{//没有就保存，更新时间时间在本地new,再存到日志
			flightNote.setId(CommonUtils.uuid());
			flightNote.setUpdateTime(new Date());
			flightNote.setUpdateUser("admin");
			commonDao.insertSelective(flightNote);
			CacheMgr cm=CacheMgr.getInstance();  
			cm.addCache(Serialno, flightNote);
		}
		return 1;
	}
	
	

}
  