import com.itran.cargosystem.entity.FlightSchedule;
import com.itran.cargosystem.service.module_oms.FlightScheduleService;
import org.junit.Test;

import javax.annotation.Resource;

public class FlightScheduleTest {
    @Resource
    FlightScheduleService flightScheduleService;

    @Test
    public void test() {
        FlightSchedule flightSchedule = flightScheduleService.queryFlightScheduleById("1");
        System.out.println(flightSchedule.toString());
    }
}
