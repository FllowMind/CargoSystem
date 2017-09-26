package com.itran.cargosystem.service;

import com.itran.cargosystem.bean.FlightSchedule;

public interface FlightScheduleService {
    FlightSchedule queryFlightScheduleById(String id);

    int addFlghtSchedule(FlightSchedule flightSchedule);

    int updateFlight(FlightSchedule flightSchedule);

    int deleteFlight(String id);

    void addFlight(FlightSchedule flightSchedule);

}
