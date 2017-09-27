package com.itran.cargosystem.dao.mapper;

import com.itran.cargosystem.entity.Employee;
import org.apache.ibatis.annotations.Param;


public interface EmployeeMapper {
	
	Employee selectByDateAndPosition(@Param("date") String date, @Param("position") int position);

}
