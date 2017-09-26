package com.itran.cargosystem.common.util.ip;

import javax.servlet.http.HttpServletRequest;

/**  
* 类说明   
*  
* @author lsf  
* @date 2017年6月20日  新建  
*/
public class IPUtils {
	/**
     * 获取请求的真实IP地址
     * @param request
     * @return
     */
    public static String getRealIPAddress(HttpServletRequest request){
        String realIP = request.getHeader("x-forwarded-for"); 
        if(realIP == null || realIP.length() == 0 || "unknown".equalsIgnoreCase(realIP)) { 
         realIP = request.getHeader("Proxy-Client-IP"); 
         } 
        if(realIP == null || realIP.length() == 0 || "unknown".equalsIgnoreCase(realIP)) { 
         realIP = request.getHeader("WL-Proxy-Client-IP"); 
         } 
        if(realIP == null || realIP.length() == 0 || "unknown".equalsIgnoreCase(realIP)) { 
         realIP = request.getRemoteAddr(); 
         } 
          return realIP;
          
    }


}
  