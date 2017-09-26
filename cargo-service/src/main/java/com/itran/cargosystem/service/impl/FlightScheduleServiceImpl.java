package com.itran.cargosystem.service.impl;

import com.itran.cargosystem.bean.FlightSchedule;
import com.itran.cargosystem.dao.CommonDao;
import com.itran.cargosystem.dao.mapper.FlightScheduleDao;
import com.itran.cargosystem.dao.util.GeneralQueryParam;
import com.itran.cargosystem.service.FlightScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService {
    @Autowired
    CommonDao commonDao ;
    @Autowired
    FlightScheduleDao fsDao;

    @Override
    public FlightSchedule queryFlightScheduleById(String id) {

        try {
            GeneralQueryParam params = new GeneralQueryParam();
            return fsDao.getFlightSchedule(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int addFlghtSchedule(FlightSchedule flightSchedule) {
        try {
            commonDao.insert(flightSchedule);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateFlight(FlightSchedule flightSchedule) {
        return 0;
    }

    @Override
    public int deleteFlight(String id) {
        return 0;
    }

    @Override
    public void addFlight(FlightSchedule flightSchedule) {

    }
}
