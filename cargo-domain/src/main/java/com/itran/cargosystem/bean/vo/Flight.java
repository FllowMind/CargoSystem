package com.itran.cargosystem.bean.vo;

import java.lang.reflect.Field;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*import com.fasterxml.jackson.annotation.JsonProperty;*/

/**
 * 航班实体类
 * 
 * @author lsf
 * @date 2017年7月13日 新建
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Flight {

	/** 自动产生的序列号 */
	private Integer fid;
	/** 任务 */
	private String propertyname;
	/** 航班号 */
	private String fno;
	/** 机号 */
	private String acno;
	/** 机型 */
	private String type;
	/** 航段 */
	private String leg;
	/** 机位 */
	private String location;
	/** 进港动态 */
	private String indynamic;
	/** 出港动态 */
	private String outdynamic;
	/** 保障时限 */
	private String limittime;
	/** 备注内容 */
	private String notecontent;

	// 节点数据
	/** 监装监卸 */
	private String impoupopid;
	/** 吨位预配 */
	private String cdpztime;
	/** 配载出装机单时间 */
	private String sczjdotime;
	/** 货物出库时间 */
	private String hwcctime;
	/** 航班到达 */
	private String putWheel;
	/** 开货舱门时间 */
	private String kcmtime;
	/** 行李上盘时间 */
	private String xlxptime;
	/** 开始装机时间 */
	private String hwksxjtime;
	/** 行李出仓时间 */
	private String xlcctime;
	/** 关货舱门时间 */
	private String gcmtime;
	/** 二次开舱门时间 */
	private String eckcmtime;
	/** 二次关舱门时间 */
	private String ecgcmtime;

	// 辅助字段
	/** 实际降落时间 */
	private String realdesttime;
	/** 计划降落时间 */
	private String estdesttime;
	/** 预计降落时间 */
	private String desttime;
	/** 实际起飞时间 */
	private String realdeptime;
	/** 计划降落时间*/
	private String estdeptime;
	/** 预计起飞时间 */
	private String deptime;
	/** 起飞站 */
	private String fdepname;
	/** 起飞站单字 */
	private String fdepcharcode;
	/** 降落站 */
	private String fdestname;
	/** 降落站单字 */
	private String fdestcharcode;
	/** 备降站 */
	private String bjz;
	/** 备降站 单字*/
	private String jtzcharcode;
	/** 经停站 */
	private String jtzname;
	/** 航班组号ID */
	private String serialno;
	/**保障状态*/
	private int status;

	/** 航班等級 */
	private int Level = getLevel();

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getFno() {
		return fno.toUpperCase();
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public String getAcno() {
		return acno;
	}

	public void setAcno(String acno) {
		this.acno = acno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLeg() {
		return leg;
	}

	public void setLeg(String leg) {
		this.leg = leg;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getIndynamic() {
		return indynamic;
	}

	public void setIndynamic(String indynamic) {
		this.indynamic = indynamic;
	}

	public String getOutdynamic() {
		return outdynamic;
	}

	public void setOutdynamic(String outdynamic) {
		this.outdynamic = outdynamic;
	}

	public String getLimittime() {
		return limittime;
	}

	public void setLimittime(String limittime) {
		this.limittime = limittime;
	}

	public String getCdpztime() {
		return cdpztime;
	}

	public void setCdpztime(String cdpztime) {
		this.cdpztime = formatTime(cdpztime);
	}

	public String getSczjdotime() {
		return sczjdotime;
	}

	public void setSczjdotime(String sczjdotime) {
		this.sczjdotime = formatTime(sczjdotime);
	}

	public String getHwcctime() {
		return hwcctime;
	}

	public void setHwcctime(String hwcctime) {
		this.hwcctime = formatTime(hwcctime);
	}

	public String getPutWheel() {
		return putWheel;
	}

	public void setPutWheel(String putWheel) {
		this.putWheel = formatTime(putWheel);
	}

	public String getKcmtime() {
		return kcmtime;
	}

	public void setKcmtime(String kcmtime) {
		this.kcmtime = formatTime(kcmtime);
	}

	public String getXlxptime() {
		return xlxptime;
	}

	public void setXlxptime(String xlxptime) {
		this.xlxptime = formatTime(xlxptime);
	}

	public String getHwksxjtime() {
		return hwksxjtime;
	}

	public void setHwksxjtime(String hwksxjtime) {
		this.hwksxjtime = formatTime(hwksxjtime);
	}

	public String getXlcctime() {
		return xlcctime;
	}

	public void setXlcctime(String xlcctime) {
		this.xlcctime = formatTime(xlcctime);
	}

	public String getGcmtime() {
		return gcmtime;
	}

	public void setGcmtime(String gcmtime) {
		this.gcmtime = formatTime(gcmtime);
	}

	public String getEckcmtime() {
		return eckcmtime;
	}

	public void setEckcmtime(String eckcmtime) {
		this.eckcmtime = formatTime(eckcmtime);
	}

	public String getEcgcmtime() {
		return ecgcmtime;
	}

	public void setEcgcmtime(String ecgcmtime) {
		this.ecgcmtime = formatTime(ecgcmtime);
	}

	public String getRealdesttime() {
		return realdesttime;
	}

	public void setRealdesttime(String realdesttime) {
		this.realdesttime = realdesttime;
	}

	public String getEstdesttime() {
		return estdesttime;
	}

	public void setEstdesttime(String estdesttime) {
		this.estdesttime = estdesttime;
	}

	public String getDesttime() {
		return desttime;
	}

	public void setDesttime(String desttime) {
		this.desttime = desttime;
	}

	public String getRealdeptime() {
		return realdeptime;
	}

	public void setRealdeptime(String realdeptime) {
		this.realdeptime = realdeptime;
	}

	public String getEstdeptime() {
		return estdeptime;
	}

	public void setEstdeptime(String estdeptime) {
		this.estdeptime = estdeptime;
	}

	public String getDeptime() {
		return deptime;
	}

	public void setDeptime(String deptime) {
		this.deptime = deptime;
	}

	public String getFdepname() {
		return fdepname;
	}

	public void setFdepname(String fdepname) {
		this.fdepname = fdepname;
	}

	public String getFdestname() {
		return fdestname;
	}

	public void setFdestname(String fdestname) {
		this.fdestname = fdestname;
	}

	public String getBjz() {
		return bjz;
	}

	public void setBjz(String bjz) {
		this.bjz = bjz;
	}

	public String getJtzname() {
		return jtzname;
	}

	public void setJtzname(String jtzname) {
		this.jtzname = jtzname;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	private String formatTime(String time) {
		if (time != null && !time.equals("")) {
			time = time.substring(time.indexOf(" "), time.lastIndexOf(":"));
			time = time.replace(":", "");
		}
		return time;
	}

	public String getImpoupopid() {
		return impoupopid;
	}

	public void setImpoupopid(String impoupopid) {
		this.impoupopid = impoupopid;
	}

	public String getPropertyname() {
		return propertyname;
	}

	public void setPropertyname(String propertyname) {
		this.propertyname = propertyname;
	}

	public String getNotecontent() {
		return notecontent;
	}

	public void setNotecontent(String notecontent) {
		this.notecontent = notecontent;
	}

	public int getLevel() {
		String[] nodes = { "cdpztime", "sczjdotime", "hwcctime", "putWheel", "kcmtime", "xlxptime", "hwksxjtime",
				"xlcctime", "gcmtime", "eckcmtime", "ecgcmtime" };
		for (int j = nodes.length - 1; j > -1; j--) {
			Class<? extends Flight> clazz = Flight.class;
			Field field;
			try {
				field = clazz.getDeclaredField(nodes[j]);
				field.setAccessible(true);
				String value = (String) field.get(this);
				if (value != null && value != "") {
					return j+1;
				}
			} catch (NoSuchFieldException e) {

				e.printStackTrace();
			} catch (SecurityException e1) {

				e1.printStackTrace();
			} catch (IllegalArgumentException e2) {

				e2.printStackTrace();
			} catch (IllegalAccessException e3) {

				e3.printStackTrace();
			} catch (Exception e4) {
				e4.printStackTrace();
			}

		}
		return 0;
	}

	public String getFdepcharcode() {
		return fdepcharcode;
	}

	public void setFdepcharcode(String fdepcharcode) {
		this.fdepcharcode = fdepcharcode;
	}

	public String getFdestcharcode() {
		return fdestcharcode;
	}

	public void setFdestcharcode(String fdestcharcode) {
		this.fdestcharcode = fdestcharcode;
	}

	public String getJtzcharcode() {
		return jtzcharcode;
	}

	public void setJtzcharcode(String jtzcharcode) {
		this.jtzcharcode = jtzcharcode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
	
}