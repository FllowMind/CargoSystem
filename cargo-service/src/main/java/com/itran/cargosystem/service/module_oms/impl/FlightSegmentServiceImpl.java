package com.itran.cargosystem.service.module_oms.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itran.cargosystem.entity.FlightSegment;
import com.itran.cargosystem.dao.module_common.CommonDao;
import com.itran.cargosystem.dao.util.GeneralQueryParam;
import com.itran.cargosystem.service.module_oms.FlightSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightSegmentServiceImpl implements FlightSegmentService {
	@Autowired
	private CommonDao commonDao;

	public FlightSegment getFlightSegmentMsgByType(String flightType) throws Exception {
		String conditionExp = "FLIGHT_TYPE = #{conditionParam.FLIGHT_TYPE}";
		Map<String, Object> conditionParam = new HashMap();
		conditionParam.put("FLIGHT_TYPE", flightType);
		GeneralQueryParam queryParam = new GeneralQueryParam();
		queryParam.setConditionExp(conditionExp);
		queryParam.setConditionParam(conditionParam);
		List<FlightSegment> result = null;
		result = this.commonDao.selectAdvanced(FlightSegment.class, queryParam);
		return (FlightSegment) result.get(0);
	}

	@Override
	public List<FlightSegment> getAllFlightSegmentMsg() throws Exception {
		List<FlightSegment> flightSegments = this.commonDao.selectAdvanced(FlightSegment.class, new GeneralQueryParam());
		return flightSegments;
	}

}
