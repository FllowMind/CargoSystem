package com.itran.cargosystem.service.impl;

import com.itran.cargosystem.bean.FlightNote;
import com.itran.cargosystem.bean.FlightSegment;
import com.itran.cargosystem.bean.vo.Flight;
import com.itran.cargosystem.bean.vo.FlightDateVo;
import com.itran.cargosystem.bean.vo.FlightMonitor;
import com.itran.cargosystem.common.Cache.CacheMgr;
import com.itran.cargosystem.common.util.date.FormatTime;
import com.itran.cargosystem.service.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.*;


/**
 * 类说明
 * 
 * @author lsf
 * @date 2017年7月17日 新建
 */
@Service
public class SplicingDataServiceImpl implements SplicingDataService {

	@Autowired
	FlightService flightService;
	@Autowired
	FlightMonitorService flightMonitorService;
	@Autowired
	FlightNoteService flightNoteService;
	@Autowired
	FlightSegmentService flightSegmentService;

	@Override
	public List<Flight> queryFligh(FlightDateVo fliDateVo) throws Exception {

		/** ---------------------------------------------数据准备--------------------------------------------- */
		String  dateDay = fliDateVo.getStartFdate();
		
		
		// 获取所有保障组
		fliDateVo.setMethods("GetFlightMonitor");
		List<FlightMonitor> allFlightMonitors = flightMonitorService.queryFlightMonitor(fliDateVo);
		Map<String, FlightMonitor> flightMonitorsMap = new HashMap<String, FlightMonitor>();
		if (allFlightMonitors.size() > 0) {
			for (int i = 0; i < allFlightMonitors.size(); i++) {
				flightMonitorsMap.put(((FlightMonitor) allFlightMonitors.get(i)).getSerialno(),
						(FlightMonitor) allFlightMonitors.get(i));
			}
		}

		// 获取所有航班
		fliDateVo.setMethods("GetFlightlist");
		List<Flight> allFlights = flightService.queryFlight(fliDateVo);
		// 加载所有航班备注
		flightNoteService.NotesPutCacheByDate(fliDateVo.getStartFdate());
		

		/** ---------------------------------------------组装航班数据--------------------------------------------- */
		// 缓存数据
		CacheMgr cm = CacheMgr.getInstance();
		// 封装好的航班
		List<Flight> flights = new ArrayList<Flight>();
		if (allFlights.size() == 0) {
			return null;
		} else {
			// 去除没有保障组的航班
			for (int i = 0; i < allFlights.size(); i++) {
				if (StringUtils.isNotBlank(allFlights.get(i).getSerialno())) {
					// 一条航班的封装
					Flight flight = allFlights.get(i);
					String serNo = flight.getSerialno();
					// 监装监卸
					FlightMonitor flightMonitor = flightMonitorsMap.get(serNo);
					String Oupopid = flightMonitor.getOupopid() == null ? "" : flightMonitor.getOupopid();
					String Impopid = flightMonitor.getImpopid() == null ? "" : flightMonitor.getImpopid();
					flight.setImpoupopid(Oupopid + "-" + Impopid);
					// 每条航班都添加对应保障组的备注
					FlightNote flightNote = (FlightNote) cm.getValue(serNo);
					if (flightNote != null) {
						flight.setNotecontent(flightNote.getNoteContent());
					}
					// 每条航班都添加对应保障组的动态节点时间
					if (flightMonitor != null) {
						BeanUtils.copyProperties(flight, flightMonitor);
					}
					flights.add(flight);
				}
			}
		}
		return flights;
	}

	@Override
	public List<Flight> splicingFligh(List<Flight> flights ,String dateDay)throws Exception {
		if (flights.size() == 0) {
			return null;
		}
		
		//加载航班保障数据
		List<FlightSegment> flightSegments =  flightSegmentService.getAllFlightSegmentMsg();
		Map<String, FlightSegment> flightSegmentsMap = new HashMap<String, FlightSegment>();
		for(int i = 0;i<flightSegments.size();i++){
			flightSegmentsMap.put(flightSegments.get(i).getFlightType(), flightSegments.get(i));
			}
		
		Map<String, Flight> resuFlightsMap = new HashMap<String, Flight>();
		List<Flight> resuFlightsList = new ArrayList<>();
		String serialNo = null;
		Flight flight = null;
		Flight resuFlight = null;
		for (int i = 0; i < flights.size(); i++) {
			flight = flights.get(i);
			serialNo = flight.getSerialno();
			resuFlight = resuFlightsMap.get(serialNo);
			// map里面没有保障组对应的航班就添加，有就拼接航班
			if (resuFlight == null) {
				// 单进港
				if (flight.getFdestname().trim().equals("珠海")) {
					// 单进港添加进港动态
					Flight newResuFlight = inDynamic(flight, null);
					// 添加航段
					newResuFlight.setLeg(
							newResuFlight.getFdepcharcode().trim() + "-" + newResuFlight.getFdestcharcode().trim());
					// 添加保障信息
					FlightSegment flightSegment = flightSegmentsMap.get(newResuFlight.getType());
					newResuFlight = flightAddLimitMsg(newResuFlight,flightSegment,dateDay);

					resuFlightsMap.put(newResuFlight.getSerialno(), newResuFlight);
				}
				// 单出港
				if (flight.getFdepname().trim().equals("珠海")) {
					// 单出港添加出港动态
					Flight newResuFlight = outDynamic(flight, null);
					// 添加航段
					newResuFlight.setLeg(
							newResuFlight.getFdepcharcode().trim() + "-" + newResuFlight.getFdestcharcode().trim());
					// 添加保障信息
					FlightSegment flightSegment = flightSegmentsMap.get(newResuFlight.getType());
					newResuFlight = flightAddLimitMsg(newResuFlight,flightSegment,dateDay);

					resuFlightsMap.put(newResuFlight.getSerialno(), newResuFlight);
				}
			} else {
					// 保障组已经存在，拼接成联程航班
					// 追加进港信息
				if (flight.getFdestname().trim().equals("珠海")) {
					// 航班号拼接
					resuFlight.setFno(flight.getFno() + "," + resuFlight.getFno());

					// 航段拼接(进港航班角度)
					resuFlight.setLeg(flight.getFdepcharcode().trim() + "-" + flight.getFdestcharcode().trim() + "-"
							+ resuFlight.getFdestcharcode().trim());

					// 添加进港状态再添加出港状态
					resuFlight = inDynamic(resuFlight, flight);

					// 添加保障信息
					FlightSegment flightSegment = flightSegmentsMap.get(resuFlight.getType());
					resuFlight = flightAddLimitMsg(resuFlight,flightSegment,dateDay);

					resuFlightsMap.put(resuFlight.getSerialno(), resuFlight);

				} else {
					// 追加出港信息
					// 航班号拼接
					resuFlight.setFno(resuFlight.getFno() + "," + flight.getFno());

					// 航段拼接(进港航班角度)
					resuFlight.setLeg(resuFlight.getFdepcharcode().trim() + "-" + resuFlight.getFdestcharcode().trim()
							+ "-" + flight.getFdestcharcode().trim());

					// 添加进港状态再添加出港状态
					resuFlight = outDynamic(resuFlight, flight);

					// 添加保障信息
					FlightSegment flightSegment = flightSegmentsMap.get(resuFlight.getType());
					resuFlight = flightAddLimitMsg(resuFlight,flightSegment,dateDay);

					
					resuFlightsMap.put(resuFlight.getSerialno(), resuFlight);
				}
			}
		}
		// map转list排序
		for (Flight fligh : resuFlightsMap.values()) {
			resuFlightsList.add(fligh);
		}
		Collections.sort(resuFlightsList, new DescComparator());

		return resuFlightsList;
	}

	/**
	 * 为进港航班添加进港动态 如果是联程航班的话再额外添加出港动态 有参数flightnext代表有相同航班组的航班
	 */
	public Flight inDynamic(Flight flight, Flight flightnext) {
		if (flightnext == null) {// 单项
			// 添加进港动态:
			// 进港动态指进港航班的RealDestTime，如果没有，则取EstDestTime，如果也没有，则取DestTime
			if (StringUtils.isNotBlank(flight.getRealdesttime())) {
				flight.setIndynamic(flight.getRealdesttime());
			} else {
				if (StringUtils.isNotBlank(flight.getEstdeptime())) {
					flight.setIndynamic(flight.getEstdeptime());
				} else {
					flight.setIndynamic(flight.getDesttime());
				}
			}
		} else {// 联程,不仅要添加进港，还要添加出港，出港的动态值为同一个航班组号的flightnext的出港动态值。然后在集合里删除flightnext，达到合成一条数据的效果。
				// 添加进港动态:
				// 进港动态指进港航班的RealDestTime，如果没有，则取EstDestTime，如果也没有，则取DestTime
			if (StringUtils.isNotBlank(flight.getRealdesttime())) {
				flight.setIndynamic(flight.getRealdesttime());
			} else {
				if (StringUtils.isNotBlank(flight.getEstdeptime())) {
					flight.setIndynamic(flight.getEstdeptime());
				} else {
					flight.setIndynamic(flight.getDesttime());
				}
			}
			// 添加出港动态:出港动态指出港航班的RealDepTime，如果没有，则取EstDepTime，如果也没有，则取DepTime.
			if (StringUtils.isNotBlank(flightnext.getRealdeptime())) {
				flight.setOutdynamic(flightnext.getRealdeptime());
			} else {
				if (StringUtils.isNotBlank(flightnext.getEstdeptime())) {
					flight.setOutdynamic(flightnext.getEstdeptime());
				} else {
					flight.setOutdynamic(flightnext.getDeptime());
				}
			}
		}
		return flight;
	}

	/**
	 * 为出港航班添加出港动态 如果是联程航班的话再额外添加进港动态 有参数flightnext代表有相同航班组的航班
	 */
	public Flight outDynamic(Flight flight, Flight flightnext) {
		if (flightnext == null) {// 单项
			// 添加出港动态:出港动态指出港航班的RealDepTime，如果没有，则取EstDepTime，如果也没有，则取DepTime
			if (StringUtils.isNotBlank(flight.getRealdeptime())) {
				flight.setOutdynamic(flight.getRealdeptime());
			} else {
				if (StringUtils.isNotBlank(flight.getEstdeptime())) {
					flight.setOutdynamic(flight.getEstdeptime());
				} else {
					flight.setOutdynamic(flight.getDeptime());
				}
			}
		} else {// 联程,不仅要添加出港，还要添加进港，进港的动态值为同一个航班组号的flightnext的进港动态值。然后在集合里删除flightnext，达到合成一条数据的效果。
				// 添加出港动态:出港动态指出港航班的RealDepTime，如果没有，则取EstDepTime，如果也没有，则取DepTime
			if (StringUtils.isNotBlank(flight.getRealdeptime())) {
				flight.setOutdynamic(flight.getRealdeptime());
			} else {
				if (StringUtils.isNotBlank(flight.getEstdeptime())) {
					flight.setOutdynamic(flight.getEstdeptime());
				} else {
					flight.setOutdynamic(flight.getDeptime());
				}
			}
			// 添加进港动态:
			// 进港动态指进港航班的RealDestTime，如果没有，则取EstDestTime，如果也没有，则取DestTime
			if (StringUtils.isNotBlank(flightnext.getRealdesttime())) {
				flight.setIndynamic(flightnext.getRealdesttime());
			} else {
				if (StringUtils.isNotBlank(flightnext.getEstdeptime())) {
					flight.setIndynamic(flightnext.getEstdeptime());
				} else {
					flight.setIndynamic(flightnext.getDesttime());
				}
			}
		}
		return flight;
	}

	/**
	 * 航班添加其他信息
	 * @param flight
	 * @param dateDay 用于动态时间查询
	 * @return
	 * @throws ParseException 
	 */
	public Flight flightAddLimitMsg(Flight flight,FlightSegment flightSegment,String dateDay) throws ParseException {
		//获取航班保障限制数据
		CacheMgr cm = CacheMgr.getInstance();
		//有开舱时间才会有保障限制
		if (StringUtils.isNotBlank(flight.getKcmtime())){
			//保障时限
			String kcmTime = flight.getKcmtime();
			String khh = kcmTime.substring(0, 3);
			String kmm = kcmTime.substring(3, 5);
			kcmTime = khh + ":" + kmm;
			kcmTime = dateDay +kcmTime+ ":00";
			/*kcmTime = FormatTime.HMSJointYMD(kcmTime);*/
			//机型没有时间长度默认为60分
			String taskTime = FormatTime.dateAddMinute(kcmTime, flightSegment.getTaskTimeLength()==0?60:flightSegment.getTaskTimeLength());
			int warning = -(flightSegment.getWarningTimeLength());
			String warningStr = FormatTime.dateAddMinute(taskTime, warning);
			String khhmm = FormatTime.getTimeByDateString(taskTime);
			khh = khhmm.substring(0, 3);
			kmm = khhmm.substring(3, 5);
			khhmm = khh + kmm;
			flight.setLimittime(khhmm);
			
			long nowTim = new Date().getTime();//当前时间
		    long warningTim = FormatTime.transferDateTime(warningStr).getTime();//警告时间
		    long taskTim = FormatTime.transferDateTime(taskTime).getTime();//保障时间
			
		    //Status:1正常,2警告,3超时
		    //无关舱门时间
			if(StringUtils.isBlank(flight.getGcmtime())){
				if (nowTim < warningTim) {
			          flight.setStatus(1);
			        }
			    if ((nowTim > warningTim) && (nowTim < taskTim)) {
			          flight.setStatus(2);
			        }
			    if (nowTim > taskTim) {
			          flight.setStatus(3);
			        }
			}else{
				//有关舱时间
				String gcmTime = flight.getGcmtime();
				String ghh = gcmTime.substring(0, 3);
				String gmm = gcmTime.substring(3, 5);
				gcmTime = ghh + ":" +gmm;
				gcmTime = dateDay +gcmTime+ ":00";
				Date kcmDate = FormatTime.transferDateTime(kcmTime);
				Date gcmDate = FormatTime.transferDateTime(gcmTime);
				//如果关舱门时间在开舱门时间之前，关舱门天数加一
				if(kcmDate.compareTo(gcmDate)>0){
					gcmDate = FormatTime.getTomorrowByDate(gcmDate);
				}
				long gcmTim = gcmDate.getTime();
				if (gcmTim < warningTim) {
			          flight.setStatus(1);
			        }
			    if ((gcmTim > warningTim) && (gcmTim < taskTim)) {
			          flight.setStatus(2);
			        }
			    if (gcmTim > taskTim) {
			          flight.setStatus(3);
			        }
			}	
		}
		return flight;
	}
	
}



/** 航班等级比较器 */
class DescComparator implements Comparator<Flight> {
	String[] nodes = { "cdpztime", "sczjdotime", "hwcctime", "putWheel", "kcmtime", "xlxptime", "hwksxjtime",
			"xlcctime", "gcmtime", "eckcmtime", "ecgcmtime" };

	@Override
	public int compare(Flight p1, Flight p2) {
		Class<? extends Flight> clazz = Flight.class;
		Field field1;
		Field field2;
		if (p2.getLevel() != 0 && p2.getLevel() == p1.getLevel()) {
			// 等级一样就获取等级对应的时间，再对比时间
			try {
				field1 = clazz.getDeclaredField(nodes[p1.getLevel() - 1]);
				field2 = clazz.getDeclaredField(nodes[p2.getLevel() - 1]);
				field1.setAccessible(true);
				field2.setAccessible(true);
				try {
					String value = ((String) field1.get(p1)).trim();
					String value2 = ((String) field2.get(p2)).trim();
					return Integer.parseInt(value2) - Integer.parseInt(value);

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		return p2.getLevel() - p1.getLevel();
	}
}
