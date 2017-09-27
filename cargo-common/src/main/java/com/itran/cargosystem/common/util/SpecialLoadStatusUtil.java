package com.itran.cargosystem.common.util;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * 功能描述：特殊集装器类型类
 * 
 * @author   create on: 2013-4-12
 * 
 */
public class SpecialLoadStatusUtil {

	/** 开鼻门 */
	public static final String OPENING = "Opening";
	/** 中央装载 **/
	public static final String CENTRAL = "Central";
	/** 横向装载 **/
	public static final String LATERAL = "Lateral";

	private static final Map<String, String> STOWAGESTATUSS = new HashMap<String, String>();
	private static final Map<String, Integer> ORDERMAP = new HashMap<String, Integer>();
	static {
		STOWAGESTATUSS.put(OPENING,"开鼻门");
		STOWAGESTATUSS.put(CENTRAL,"中央装载");
		STOWAGESTATUSS.put(LATERAL,"横向装载");

		STOWAGESTATUSS.put("开鼻门",OPENING);
		STOWAGESTATUSS.put("中央装载",CENTRAL);
		STOWAGESTATUSS.put("横向装载",LATERAL);
		
		ORDERMAP.put(LATERAL, 0);
		ORDERMAP.put(CENTRAL, 1);
		ORDERMAP.put(LATERAL, 2);
	}

	/**
	 * 功能描述：获取对应状态描述
	 * 
	 */
	public static String getStowageStatus(String type) {
		return STOWAGESTATUSS.get(type);
	}
		
}
