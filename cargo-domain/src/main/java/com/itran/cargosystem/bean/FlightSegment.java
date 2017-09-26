package com.itran.cargosystem.bean;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "FLIGHT_SEGMENT")
public class FlightSegment {
	@Id
	private String id;
	/**飞机类型*/
	private String flightType;
	/**飞机类型别名*/
	private String flightTypeAliase;
	/**座位分类*/
	private String seatType;
	/**任务时间长度*/
	private int taskTimeLength;
	/**警告时间长度*/
	private int warningTimeLength;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlightType() {
		return flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public String getFlightTypeAliase() {
		return flightTypeAliase;
	}

	public void setFlightTypeAliase(String flightTypeAliase) {
		this.flightTypeAliase = flightTypeAliase;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public int getTaskTimeLength() {
		return taskTimeLength;
	}

	public void setTaskTimeLength(int taskTimeLength) {
		this.taskTimeLength = taskTimeLength;
	}

	public int getWarningTimeLength() {
		return warningTimeLength;
	}

	public void setWarningTimeLength(int warningTimeLength) {
		this.warningTimeLength = warningTimeLength;
	}

}
