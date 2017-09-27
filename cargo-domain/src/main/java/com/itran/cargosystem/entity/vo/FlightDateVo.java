package com.itran.cargosystem.entity.vo;

/**
 *	封装请求数据具体参数的vo
 * 
 * @author lsf
 * @date 2017年7月11日 新建
 */
public class FlightDateVo {
	
	private String action;//请求的action
	
	private String methods;//请求的方法

	private String startFdate;// 开始时间

	private String endFdate;// 结束时间

	private String fno;// 飞机号

	private String fdep;// 起飞地点

	private String fdest;// 到达地点

	public String getStartFdate() {
		return startFdate;
	}

	public void setStartFdate(String startFdate) {
		this.startFdate = startFdate;
	}

	public String getEndFdate() {
		return endFdate;
	}

	public void setEndFdate(String endFdate) {
		this.endFdate = endFdate;
	}

	public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public String getFdep() {
		return fdep;
	}

	public void setFdep(String fdep) {
		this.fdep = fdep;
	}

	public String getFdest() {
		return fdest;
	}

	public void setFdest(String fdest) {
		this.fdest = fdest;
	}

	public String getMethods() {
		return methods;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
}
