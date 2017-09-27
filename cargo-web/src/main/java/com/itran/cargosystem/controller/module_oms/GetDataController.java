package com.itran.cargosystem.controller.module_oms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.itran.cargosystem.entity.Employee;
import com.itran.cargosystem.entity.FlightNote;
import com.itran.cargosystem.entity.vo.Flight;
import com.itran.cargosystem.entity.vo.FlightDateVo;
import com.itran.cargosystem.entity.vo.Result;
import com.itran.cargosystem.common.Cache.CacheMgr;
import com.itran.cargosystem.common.util.date.FormatTime;
import com.itran.cargosystem.function.log.Log;
import com.itran.cargosystem.service.module_oms.CacheByMapService;
import com.itran.cargosystem.service.module_oms.EmployeeService;
import com.itran.cargosystem.service.module_oms.SplicingDataService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 这是一个获取别的项目数据的Controller
 *
 * @author lsf
 * @date 2017年6月28日 新建
 */
@Controller
@RequestMapping("/getData")
public class GetDataController {

    @Autowired
    SplicingDataService splicingDataService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    CacheByMapService cacheByMapService;
    private static final Logger logger = Logger.getLogger(GetDataController.class);


    /**
     * 获取航班动态数据还有航班节点数据
     *
     * @date 2017年6月28日 上午10:27:43 
     * @return String  
     *  @throws  
     */
    @RequestMapping(value = "/getFlightsMsg", method = RequestMethod.POST)
    @ResponseBody
    @Log(operationType = "获取航班动态数据", operationName = "连接航班动态数据接口获取航班数据")
    public Result queryFlightlist(Model model, FlightDateVo fliDateVo) throws Exception {

        Result result = new Result();
        Map<String, Object> map = new HashMap<String, Object>();
        CacheMgr cm = CacheMgr.getInstance();
        List<Flight> flights = new ArrayList<Flight>();
        String today = FormatTime.TodayDate();
        //按日期查找缓存
        if (fliDateVo.getStartFdate() != null && fliDateVo.getStartFdate().equals(today)) {
            //是否进行过缓存操作
            if (cm.getValue("flightDataUpdate") == null) {
                //是第一次请求就添加缓存
                flights = splicingDataService.queryFligh(fliDateVo);
                flights = splicingDataService.splicingFligh(flights, fliDateVo.getStartFdate());
                cacheByMapService.addCacheMap(flights, fliDateVo.getStartFdate());
                //添加页面显示标识符
                cacheByMapService.addShowIndex(flights);
            }
        } else {
            //缓存是否存在其他日期的航班
            if (cm.getValue(fliDateVo.getStartFdate()) == null) {
                flights = splicingDataService.queryFligh(fliDateVo);
                try{
                flights = splicingDataService.splicingFligh(flights, fliDateVo.getStartFdate());
                }catch (Exception e){
                    e.printStackTrace();
                    result.setStatus(false);
                    result.setMessage("抱歉，查询失败!");
                    return result;
                }
                cacheByMapService.addCacheMap(flights, fliDateVo.getStartFdate());
                //添加页面显示标识符
                cacheByMapService.addShowIndex(flights);
            }
        }

        //获取缓存航班
        if (cm.getValue(fliDateVo.getStartFdate()) != null) {
            Map<String, Flight> flightCacheMap = (LinkedHashMap<String, Flight>) cm.getValue(fliDateVo.getStartFdate());
            Iterator iter = flightCacheMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Flight flight = (Flight) entry.getValue();
                //缓存航班添加缓存备注
                FlightNote fn = (FlightNote) cm.getValue(flight.getSerialno());
                if (fn != null) {//缓存有这个航班组信息就添加到对应航班
                    flight.setNotecontent(fn.getNoteContent());
                }
                flights.add((Flight) entry.getValue());
                //添加页面显示标识符
                cacheByMapService.addShowIndex(flights);
            }
        }

        //获取值班员
        List<Employee> employees = employeeService.selectByDate(fliDateVo.getStartFdate());

        //没数据
        if (flights == null || flights.size() == 0) {
            result.setStatus(false);
            result.setMessage("抱歉，没有查询到航班信息!");
            return result;
        }
        //有数据
        map.put("index", cm.getValue("index"));
        map.put("flights", flights);
        map.put("employees", employees);
        result.setData(map);
        result.setStatus(true);
        result.setMessage("查询成功!");
        return result;
    }

}
