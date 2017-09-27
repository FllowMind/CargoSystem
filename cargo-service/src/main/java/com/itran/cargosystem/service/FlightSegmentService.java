package com.itran.cargosystem.service;

import com.itran.cargosystem.entity.FlightSegment;

import java.util.List;


public abstract interface FlightSegmentService{
	
	/**
	 * 跟据飞机类型查询航班保障时间长度
	 * @return
	 * @throws Exception
	 */
	FlightSegment getFlightSegmentMsgByType(String flightType) throws Exception;
  
	
	/**
	 * 查询所有航班保障时间长度
	 * @return
	 */
	List<FlightSegment>  getAllFlightSegmentMsg()throws Exception;
}

