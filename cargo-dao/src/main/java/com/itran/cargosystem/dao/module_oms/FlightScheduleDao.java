package com.itran.cargosystem.dao.module_oms;

import com.itran.cargosystem.entity.FlightSchedule;
import org.apache.ibatis.annotations.Param;

public interface FlightScheduleDao {

    FlightSchedule getFlightSchedule(@Param("id") String id);
}
