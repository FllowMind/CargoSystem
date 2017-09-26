package com.itran.cargosystem.bean;

import java.util.Date;

import javax.persistence.Id;

/**
 * 航班备忘
 * 
 * @author lsf
 * @date 2017年7月17日 新建
 */
public class FlightNote {

	@Id
	private String id;
	/** 航班组号ID */
	private String serialno;
	/** 创建时间 */
	private String creationDate;
	/** 最后更新时间 */
	private Date updateTime;
	/** 最后更新人 */
	private String updateUser;
	/** 备忘内容 */
	private String noteContent;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "FlightNote [id=" + id + ", serialno=" + serialno + ", creationDate=" + creationDate + ", updateTime="
				+ updateTime + ", updateUser=" + updateUser + ", noteContent=" + noteContent + "]";
	}

}
