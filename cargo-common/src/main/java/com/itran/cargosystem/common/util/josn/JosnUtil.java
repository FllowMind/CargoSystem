package com.itran.cargosystem.common.util.josn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

/**  
* 类说明   
*  
* @author lsf  
* @date 2017年7月13日  新建  
*/
public class JosnUtil {
	
	
	 /** 
     * 使用正则表达式提取中括号中的内容 
     * @param msg 
     * @return  
     */  
    public static String extractMessageByRegular(String msg){  
          
        List<String> list=new ArrayList<String>();  
        Pattern p = Pattern.compile("(\\[[^\\]]*\\])");  
        Matcher m = p.matcher(msg);  
        while(m.find()){  
            list.add(m.group().substring(1, m.group().length()-1));  
        }  
        StringBuffer flightMsg = new StringBuffer();
        
        for(int i = 0;i<list.size();i++){
        	flightMsg.append(list.get(i));
        }
        
        return flightMsg.toString(); 
    }  
	
    /**
     *   
     * 
     * josn字符串转对象集合
     * @date 2017年7月13日 下午3:45:40 
     * @return List<T>
     * @throws  
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
    	List<T> list = new ArrayList<T>(); 
    	JSONArray jsonArray = JSONArray.fromObject(jsonString);//把String转换为json 
    	list = JSONArray.toList(jsonArray,clazz);//这里的t是Class<T> 
    	return list;
    }
    
    
    
    public static JSONObject filterNull(JSONObject jsonObj){  
        Iterator<String> it = jsonObj.keys();  
        Object obj = null;  
        String key = null;  
        while (it.hasNext()) {  
            key = it.next();  
            obj = jsonObj.get(key);  
            if(obj instanceof JSONObject){  
                filterNull((JSONObject)obj);  
            }  
            if(obj instanceof JSONArray){  
                JSONArray objArr = (JSONArray) obj;  
                for(int i=0; i<objArr.size(); i++){  
                    filterNull(objArr.getJSONObject(i));  
                }  
            }  
            if(obj == null || obj instanceof JSONNull){  
                jsonObj.put(key, "");  
            }  
        }
		return jsonObj;  
    }  

}
  