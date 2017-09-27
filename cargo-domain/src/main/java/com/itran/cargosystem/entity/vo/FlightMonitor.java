package com.itran.cargosystem.entity.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 航班节点实体类
 * 
 * @author lsf
 * @date 2017年7月13日 新建
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightMonitor {

	/** 自动增长ID */
	private Integer id;
	/** 航班组号ID */
	private String serialno;
	/** 监装*/
	private String oupopid;
	/** 监卸*/
	private String impopid;	
	/** 舱单配载操作人 */
	private String cdpzopid;
	/** 舱单配载时间 */
	private String cdpztime;
	/** 货物出仓操作人 */
	private String hwccopid;
	/** 货物出仓时间 */
	private String hwcctime;
	/** 保障分配操作人 */
	private String bzfpopid;
	/** 保障分配时间 */
	private String bzfptime;
	/** 生成装机单操作人 */
	private String sczjdopid;
	/** 生成装机单时间 */
	private String sczjdotime;
	/** 开舱门操作人 */
	private String kcmopid;
	/** 开舱门时间 */
	private String kcmtime;
	/** 行李卸盘操作人 */
	private String xlxpopid;
	/** 行李卸盘时间 */
	private String xlxptime;
	/** 货物开始装机操作人 */
	private String hwksxjopid;
	/** 货物开始装机时间 */
	private String hwksxjtime;
	/** 行李出仓操作人 */
	private String xlccopid;
	/** 行李出仓时间 */
	private String xlcctime;
	/** 关舱门操作人 */
	private String gcmopid;
	/** 关舱门时间 */
	private String gcmtime;
	/** 二次开舱门操作人 */
	private String eckcmopid;
	/** 二次开舱门时间 */
	private String eckcmtime;
	/** 二次关舱门操作人 */
	private String ecgcmopid;
	/** 二次关舱门操作人 */
	private String ecgcmtime;
	/** 三次开舱门操作人 */
	private String sckcmopid;
	/** 三次开舱门时间 */
	private String sckcmtime;
	/** 三次关舱门操作人 */
	private String scgcmopide;
	/** 三次关舱门时间 */
	private String scgcmtime;
	/** 出港保障分配操作人 */
	private String cgbzfpopid;
	/** 出港保障分配时间 */
	private String cgbzfptime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getCdpzopid() {
		return cdpzopid;
	}
	public void setCdpzopid(String cdpzopid) {
		this.cdpzopid = cdpzopid;
	}
	public String getCdpztime() {
		return cdpztime;
	}
	public void setCdpztime(String cdpztime) {
		this.cdpztime = cdpztime;
	}
	public String getHwccopid() {
		return hwccopid;
	}
	public void setHwccopid(String hwccopid) {
		this.hwccopid = hwccopid;
	}
	public String getHwcctime() {
		return hwcctime;
	}
	public void setHwcctime(String hwcctime) {
		this.hwcctime = hwcctime;
	}
	public String getBzfpopid() {
		return bzfpopid;
	}
	public void setBzfpopid(String bzfpopid) {
		this.bzfpopid = bzfpopid;
	}
	public String getBzfptime() {
		return bzfptime;
	}
	public void setBzfptime(String bzfptime) {
		this.bzfptime = bzfptime;
	}
	public String getSczjdopid() {
		return sczjdopid;
	}
	public void setSczjdopid(String sczjdopid) {
		this.sczjdopid = sczjdopid;
	}
	public String getSczjdotime() {
		return sczjdotime;
	}
	public void setSczjdotime(String sczjdotime) {
		this.sczjdotime = sczjdotime;
	}
	public String getKcmopid() {
		return kcmopid;
	}
	public void setKcmopid(String kcmopid) {
		this.kcmopid = kcmopid;
	}
	public String getKcmtime() {
		return kcmtime;
	}
	public void setKcmtime(String kcmtime) {
		this.kcmtime = kcmtime;
	}
	public String getXlxpopid() {
		return xlxpopid;
	}
	public void setXlxpopid(String xlxpopid) {
		this.xlxpopid = xlxpopid;
	}
	public String getXlxptime() {
		return xlxptime;
	}
	public void setXlxptime(String xlxptime) {
		this.xlxptime = xlxptime;
	}
	public String getHwksxjopid() {
		return hwksxjopid;
	}
	public void setHwksxjopid(String hwksxjopid) {
		this.hwksxjopid = hwksxjopid;
	}
	public String getHwksxjtime() {
		return hwksxjtime;
	}
	public void setHwksxjtime(String hwksxjtime) {
		this.hwksxjtime = hwksxjtime;
	}
	public String getXlccopid() {
		return xlccopid;
	}
	public void setXlccopid(String xlccopid) {
		this.xlccopid = xlccopid;
	}
	public String getXlcctime() {
		return xlcctime;
	}
	public void setXlcctime(String xlcctime) {
		this.xlcctime = xlcctime;
	}
	public String getGcmopid() {
		return gcmopid;
	}
	public void setGcmopid(String gcmopid) {
		this.gcmopid = gcmopid;
	}
	public String getGcmtime() {
		return gcmtime;
	}
	public void setGcmtime(String gcmtime) {
		this.gcmtime = gcmtime;
	}
	public String getEckcmopid() {
		return eckcmopid;
	}
	public void setEckcmopid(String eckcmopid) {
		this.eckcmopid = eckcmopid;
	}
	public String getEckcmtime() {
		return eckcmtime;
	}
	public void setEckcmtime(String eckcmtime) {
		this.eckcmtime = eckcmtime;
	}
	public String getEcgcmopid() {
		return ecgcmopid;
	}
	public void setEcgcmopid(String ecgcmopid) {
		this.ecgcmopid = ecgcmopid;
	}
	public String getEcgcmtime() {
		return ecgcmtime;
	}
	public void setEcgcmtime(String ecgcmtime) {
		this.ecgcmtime = ecgcmtime;
	}
	public String getSckcmopid() {
		return sckcmopid;
	}
	public void setSckcmopid(String sckcmopid) {
		this.sckcmopid = sckcmopid;
	}
	public String getSckcmtime() {
		return sckcmtime;
	}
	public void setSckcmtime(String sckcmtime) {
		this.sckcmtime = sckcmtime;
	}
	public String getScgcmopide() {
		return scgcmopide;
	}
	public void setScgcmopide(String scgcmopide) {
		this.scgcmopide = scgcmopide;
	}
	public String getScgcmtime() {
		return scgcmtime;
	}
	public void setScgcmtime(String scgcmtime) {
		this.scgcmtime = scgcmtime;
	}
	public String getCgbzfpopid() {
		return cgbzfpopid;
	}
	public void setCgbzfpopid(String cgbzfpopid) {
		this.cgbzfpopid = cgbzfpopid;
	}
	public String getCgbzfptime() {
		return cgbzfptime;
	}
	public void setCgbzfptime(String cgbzfptime) {
		this.cgbzfptime = cgbzfptime;
	}
	public String getOupopid() {
		return oupopid;
	}
	public void setOupopid(String oupopid) {
		this.oupopid = oupopid;
	}
	public String getImpopid() {
		return impopid;
	}
	public void setImpopid(String impopid) {
		this.impopid = impopid;
	}
	@Override
	public String toString() {
		return "FlightMonitor [id=" + id + ", serialno=" + serialno + ", oupopid=" + oupopid + ", impopid=" + impopid
				+ ", cdpzopid=" + cdpzopid + ", cdpztime=" + cdpztime + ", hwccopid=" + hwccopid + ", hwcctime="
				+ hwcctime + ", bzfpopid=" + bzfpopid + ", bzfptime=" + bzfptime + ", sczjdopid=" + sczjdopid
				+ ", sczjdotime=" + sczjdotime + ", kcmopid=" + kcmopid + ", kcmtime=" + kcmtime + ", xlxpopid="
				+ xlxpopid + ", xlxptime=" + xlxptime + ", hwksxjopid=" + hwksxjopid + ", hwksxjtime=" + hwksxjtime
				+ ", xlccopid=" + xlccopid + ", xlcctime=" + xlcctime + ", gcmopid=" + gcmopid + ", gcmtime=" + gcmtime
				+ ", eckcmopid=" + eckcmopid + ", eckcmtime=" + eckcmtime + ", ecgcmopid=" + ecgcmopid + ", ecgcmtime="
				+ ecgcmtime + ", sckcmopid=" + sckcmopid + ", sckcmtime=" + sckcmtime + ", scgcmopide=" + scgcmopide
				+ ", scgcmtime=" + scgcmtime + ", cgbzfpopid=" + cgbzfpopid + ", cgbzfptime=" + cgbzfptime + "]";
	}
}
