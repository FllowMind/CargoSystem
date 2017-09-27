package com.itran.cargosystem.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itran.cargosystem.entity.Employee;
import com.itran.cargosystem.entity.vo.Result;
import com.itran.cargosystem.common.util.CommonUtils;
import com.itran.cargosystem.dao.CommonDao;
import com.itran.cargosystem.dao.mapper.EmployeeMapper;
import com.itran.cargosystem.dao.util.GeneralQueryParam;
import com.itran.cargosystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private CommonDao commonDao;
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Override
	public Result save(Employee employee) throws Exception {
		Result result = new Result();
		employee.setPosition(employee.getPosition()-1);
		Employee source = selectByDateAndPosition(employee);
		if (source != null) {
			//如果数据库已存在记录，则更新数据库记录，
			source.setName(employee.getName());
			source.setSubstitute(employee.getSubstitute());
			commonDao.updateByPrimaryKeySelective(source);
			result.setStatus(true);
			return result;
		}
		employee.setId(CommonUtils.uuid());
		commonDao.insert(employee);
		result.setStatus(true);
		return result;
	}
	
	private Employee selectByDateAndPosition(Employee employee) {
		return employeeMapper.selectByDateAndPosition(employee.getDate(), employee.getPosition());
	}

	@Override
	public List<Employee> selectByDate(String date) throws Exception {
		
		String conditionExp = "date = #{conditionParam.date}";
		Map<String, Object> conditionParam = new HashMap<String, Object>();
		conditionParam.put("date", date);
		GeneralQueryParam queryParam = new GeneralQueryParam();
		queryParam.setConditionExp(conditionExp);
		queryParam.setConditionParam(conditionParam);
		List<Employee> employees = commonDao.selectAdvanced(Employee.class, queryParam);
		if (employees == null || employees.isEmpty())
			return Collections.emptyList();
		return employees;
	}
	

}
