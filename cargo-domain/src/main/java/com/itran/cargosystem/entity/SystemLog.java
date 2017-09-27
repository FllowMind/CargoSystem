package com.itran.cargosystem.entity;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName: SystemLog    
 * @author Administrator   
 * @date 2017年6月19日 上午9:21:57
 
 */
@Table(name = "SYSTEM_LOG")
public class SystemLog {
	
	@Id
	private String id;

    private String description;//描述

    private String method;//方法

    private Integer logType;//日志类型

    private String requestIp;//请求的ip

    private String exceptionCode;//异常码

    private String exceptionDetail;//异常细节

    private String params;//参数

    private String createUser;//创建者

    private Date createTime;//创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }
    
	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp == null ? null : requestIp.trim();
    }

    public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail == null ? null : exceptionDetail.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}

  