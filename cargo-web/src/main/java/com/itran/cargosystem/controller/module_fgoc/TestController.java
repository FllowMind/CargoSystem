package com.itran.cargosystem.controller.module_fgoc;

import com.itran.cargosystem.entity.FlightSchedule;
import com.itran.cargosystem.controller.BaseController;
import com.itran.cargosystem.service.module_oms.FlightScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private FlightScheduleService flightScheduleService;

    @RequestMapping(value = "/queryFlight")
    @ResponseBody
    public FlightSchedule QueryFlight(String id) {
//        int i = 1 / 0;
       FlightSchedule f =  flightScheduleService.queryFlightScheduleById("1");
        flightScheduleService.addFlight(f);
        return f;
    }

    @RequestMapping(value = "/addFlight")
    @ResponseBody
    public String addFlight(FlightSchedule flightSchedule) {
        return "success";
    }

    @RequestMapping(value = "/toPage")
    public String toPage() {
        return "test";
    }

}
