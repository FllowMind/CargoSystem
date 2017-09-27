package com.itran.cargosystem.service;

import com.itran.cargosystem.entity.Employee;
import com.itran.cargosystem.entity.vo.Result;

import java.util.List;


public interface EmployeeService {
	
	/**
	 * 保存员工信息
	 * 
	 * @param employee
	 * @return
	 * @throws Exception
	 */
	Result save(Employee employee) throws Exception;
	
	/**
	 * 根据日期查找员工
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Employee> selectByDate(String date) throws Exception;

}
