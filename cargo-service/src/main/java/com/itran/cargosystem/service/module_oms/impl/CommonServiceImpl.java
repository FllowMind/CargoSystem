package com.itran.cargosystem.service.module_oms.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.itran.cargosystem.dao.module_common.CommonDao;
import com.itran.cargosystem.dao.util.GeneralQueryParam;
import com.itran.cargosystem.service.module_oms.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	private CommonDao commonDao;

	@Override
	public <T> T selectByPrimaryKey(Class<T> clazz, Serializable pk) throws Exception {
		return commonDao.selectByPrimaryKey(clazz, pk);
	}

	@Override
	public <T> int insert(T t) throws Exception {		
		return commonDao.insert(t);
	}

	@Override
	public <T> int insertSelective(T t) throws Exception {
		return commonDao.insertSelective(t);
	}

	@Override
	public <T> int insertBatch(List<T> list) throws Exception {
		return commonDao.insertBatch(list);
	}

	@Override
	public <T> int deleteByPrimaryKey(Class<T> clazz, Serializable pk) {
		return commonDao.deleteByPrimaryKey(clazz, pk);
	}

	@Override
	public <T> int deleteByCondition(Class<T> clazz, String conditionExp, Map<String, Object> conditionParam) {
		return commonDao.deleteByCondition(clazz, conditionExp, conditionParam);
	}

	@Override
	public <T> int updateByPrimaryKey(T t) throws Exception {
		return commonDao.updateByPrimaryKey(t);
	}

	@Override
	public <T> int updateByPrimaryKeySelective(T t) throws Exception {
		return commonDao.updateByPrimaryKeySelective(t);
	}

	@Override
	public <T> int updateByConditionSelective(Class<T> clazz, Map<String, Object> columnValueMapping,
			String conditionExp, Map<String, Object> conditionParam) throws Exception {
		return commonDao.updateByConditionSelective(clazz, columnValueMapping, conditionExp, conditionParam);
	}

	@Override
	public <T> List<T> selectAdvanced(Class<T> clazz, GeneralQueryParam queryParam) throws Exception {
		return commonDao.selectAdvanced(clazz, queryParam);
	}

	@Override
	public <T> List<Map<String, Object>> selectAdvancedByColumn(Class<T> clazz, GeneralQueryParam queryParam)
			throws Exception {
		return commonDao.selectAdvancedByColumn(clazz, queryParam);
	}
	
	
	
}
