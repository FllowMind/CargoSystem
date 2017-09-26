package com.itran.cargosystem.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itran.cargosystem.dao.CommonDao;
import com.itran.cargosystem.dao.mapper.CommonMapper;
import com.itran.cargosystem.dao.util.GeneralMapperReflectUtil;
import com.itran.cargosystem.dao.util.GeneralQueryParam;
import com.itran.cargosystem.dao.util.PersistentUtil;
import com.itran.cargosystem.dao.util.SQLColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CommonDaoImpl implements CommonDao {
	
	@Autowired
	private CommonMapper commonMapper;

	@Override
	public <T> T selectByPrimaryKey(Class<T> clazz, Serializable pk) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String tableName = PersistentUtil.getTableName(clazz);
		String primaryKey = PersistentUtil.getPrimaryKey(clazz);
		List<String> queryColumn = GeneralMapperReflectUtil.getAllColumnNames(clazz);
		param.put("tableName", tableName);
		param.put("primaryKey", primaryKey);
		param.put("primaryValue", pk);
		param.put("queryColumn", queryColumn);
		Map<String, Object> resultMap = commonMapper.selectByPrimaryKey(param);
		return GeneralMapperReflectUtil.parseToBean(resultMap, clazz);
	}

	@Override
	public <T> int insert(T t) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		Class<?> clazz = t.getClass();
		String tableName = PersistentUtil.getTableName(clazz);
		List<SQLColumn> SQLColumns = GeneralMapperReflectUtil.getAllSQLColumns(t);
		param.put("tableName", tableName);
		param.put("SQLColumns", SQLColumns);
		return commonMapper.insert(param);
	}
	
	/*@Override
	public <T> int insertExceptPk(T t) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		Class<?> clazz = t.getClass();
		String tableName = PersistentUtil.getTableName(clazz);
		List<SQLColumn> SQLColumns = GeneralMapperReflectUtil.getAllSQLColumns(t);
		param.put("tableName", tableName);
		param.put("SQLColumns", SQLColumns);
		return commonMapper.insertExceptPk(param);
	}*/

	@Override
	public <T> int insertSelective(T t) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		Class<?> clazz = t.getClass();
		String tableName = PersistentUtil.getTableName(clazz);
		List<SQLColumn> SQLColumns = GeneralMapperReflectUtil.getSQLColumnsExceptNull(t);
		param.put("tableName", tableName);
		param.put("SQLColumns", SQLColumns);

		return commonMapper.insert(param);
	}

	@Override
	public <T> int insertBatch(List<T> list) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		String tableName = "";
		List<String> columnNames = new ArrayList<String>();
		List<List<SQLColumn>> dataList = new ArrayList<List<SQLColumn>>(list.size() + 1);
		for (T t : list) {
			if (tableName.equals("")) {
				Class<?> clazz = t.getClass();
				tableName = PersistentUtil.getTableName(clazz);
			}
			if (columnNames.size() == 0) {
				Class<?> clazz = t.getClass();
				columnNames = GeneralMapperReflectUtil.getAllColumnNames(clazz);
			}
			List<SQLColumn> SQLColumns = GeneralMapperReflectUtil.getAllSQLColumns(t);
			dataList.add(SQLColumns);
		}

		param.put("tableName", tableName);
		param.put("columnNames", columnNames);
		param.put("dataList", dataList);

		return commonMapper.insertBatch(param);
	}

	@Override
	public <T> int deleteByPrimaryKey(Class<T> clazz, Serializable pk) {
		Map<String, Object> param = new HashMap<String, Object>();

		String tableName = PersistentUtil.getTableName(clazz);
		String primaryKey = PersistentUtil.getPrimaryKey(clazz);

		param.put("tableName", tableName);
		param.put("primaryKey", primaryKey);
		param.put("primaryValue", pk);

		return commonMapper.deleteByPrimaryKey(param);
	}

	@Override
	public <T> int deleteByCondition(Class<T> clazz, String conditionExp, Map<String, Object> conditionParam) {
		Map<String, Object> param = new HashMap<String, Object>();
		String tableName = PersistentUtil.getTableName(clazz);
		param.put("tableName", tableName);
		param.put("conditionExp", conditionExp);
		param.put("conditionParam", conditionParam);

		return commonMapper.deleteByCondition(param);
	}

	@Override
	public <T> int updateByPrimaryKey(T t) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		Class<?> clazz = t.getClass();

		String tableName = PersistentUtil.getTableName(clazz);
		String primaryKey = PersistentUtil.getPrimaryKey(clazz);

		List<SQLColumn> SQLColumns = GeneralMapperReflectUtil.getSQLColumns(t,false,true);

		Object primaryValue = GeneralMapperReflectUtil.getPrimaryValue(t);

		param.put("tableName", tableName);
		param.put("primaryKey", primaryKey);
		param.put("primaryValue", primaryValue);
		param.put("SQLColumns", SQLColumns);

		return commonMapper.updateByPrimaryKey(param);
	}
	
	@Override
	public <T> int updateByPrimaryKeySelective(T t) throws Exception {
		
		Map<String, Object> param = new HashMap<String, Object>();

		Class<?> clazz = t.getClass();

		String tableName = PersistentUtil.getTableName(clazz);
		String primaryKey = PersistentUtil.getPrimaryKey(clazz);

		List<SQLColumn> SQLColumns = GeneralMapperReflectUtil.getSQLColumns(t,false,false);

		Object primaryValue = GeneralMapperReflectUtil.getPrimaryValue(t);

		param.put("tableName", tableName);
		param.put("primaryKey", primaryKey);
		param.put("primaryValue", primaryValue);
		param.put("SQLColumns", SQLColumns);

		return commonMapper.updateByPrimaryKey(param);
	}

	@Override
	public <T> int updateByConditionSelective(Class<T> clazz, Map<String, Object> columnValueMapping,
			String conditionExp, Map<String, Object> conditionParam) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		String tableName = PersistentUtil.getTableName(clazz);

		param.put("tableName", tableName);
		param.put("columnValueMapping", columnValueMapping);
		param.put("conditionExp", conditionExp);
		param.put("conditionParam", conditionParam);

		return commonMapper.updateByConditionSelective(param);
	}

	@Override
	public <T> List<T> selectAdvanced(Class<T> clazz, GeneralQueryParam queryParam) throws Exception {
		List<T> result = new ArrayList<T>();

		if (queryParam.getQueryColumn() == null || queryParam.getQueryColumn().size() < 0) {
			queryParam.setQueryColumn(GeneralMapperReflectUtil.getAllColumnNames(clazz));
		}

		List<Map<String, Object>> list = selectAdvancedByColumn(clazz, queryParam);

		if (list != null && list.size() != 0) {
			for (Map<String, Object> mapping : list) {
				result.add(GeneralMapperReflectUtil.parseToBean(mapping, clazz));
			}
		}
		return result;
	}
	
	@Override
	public <T> List<Map<String, Object>> selectAdvancedByColumn(Class<T> clazz, GeneralQueryParam queryParam)
			throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		String tableName = PersistentUtil.getTableName(clazz);

		param.put("tableName", tableName);
		param.put("queryColumn", queryParam.getQueryColumn());
		param.put("conditionExp", queryParam.getConditionExp());
		param.put("conditionParam", queryParam.getConditionParam());
		param.put("orderExp", queryParam.getOrderExp());

		if (queryParam.getPageSize() != null && queryParam.getPageNo() != null) {
			Map<String, Integer> page = new HashMap<String, Integer>();
			page.put("endRowNo", queryParam.getEndRowNo());
			page.put("startRowNo", queryParam.getStartRowNo());
			param.put("page", page);
		}

		return commonMapper.selectAdvanced(param);
	}

}
