package com.itran.cargosystem.dao.mapper;

import com.itran.cargosystem.entity.FlightSchedule;
import org.apache.ibatis.annotations.Param;

public interface FlightScheduleDao {

    FlightSchedule getFlightSchedule(@Param("id") String id);
}
