package com.itran.cargosystem.common.util;

import java.util.StringTokenizer;
import java.util.UUID;

public class CommonUtils {
	
	/**
	 * 返回一个不重复的字符串
	 * 
	 * @return 32位uuid
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	
	/**
	 * 字符串转整形数组
	 * 
	 * @param str
	 * @return 
	 */
	public static int[] StringToInt(String str, String separator) {
		int ret[] = new int[str.length()];
		StringTokenizer tokenizer = new StringTokenizer(str, separator);
		int i = 0;
		while (tokenizer.hasMoreElements()) {
			ret[i++] = Integer.valueOf(tokenizer.nextToken());
		}
		return ret;
	}

}
