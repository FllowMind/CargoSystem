package com.itran.cargosystem.bean.vo;
/**  
* 类说明   
*  
* @author lsf  
* @date 2017年7月14日  新建  
*/
public class Result {
	/**响应数据*/  
	private Object data;   
    /** 响应状态 */  
    private Boolean status = false;  
    /** 响应消息 */  
    private String message;
	/**	 * 预留	 */
	private String other;
    
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Result{" +
				"data=" + data +
				", status=" + status +
				", message='" + message + '\'' +
				", other='" + other + '\'' +
				'}';
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
  