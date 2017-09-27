package com.itran.cargosystem.common.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	public static boolean isEmpty(String s) {
		if (s == null) {
			return true;
		}

		return (s.length() == 0);
	}

	public static void append(StringBuffer buff, String[] names) {

		buff.append(names[0]);
		for (int i = 1; i < names.length; ++i)
			buff.append(",").append(names[i]);
	}
	
	/**
	 * 
	 * @param sqlpath
	 * @param outStr
	 */
	public static void writeSql(String sqlpath, String outStr) {
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(sqlpath);
			OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
			writer.write(outStr);
			writer.flush();
			writer.close();
			fout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void appendv(StringBuffer buff, String[] names) {

		buff.append("'").append(names[0]).append("'");
		for (int i = 1; i < names.length; ++i) {
			buff.append(",").append("'");
			if (null != names[i] && !"null".equalsIgnoreCase(names[i])) {
				buff.append(names[i].replace("'", "''").replace(",", ";"));
			}
			buff.append("'");
		}
	}
	
	

	public static String toString(String[] names) {
		StringBuffer buff = new StringBuffer();
		append(buff, names);
		return buff.toString();
	}

	public static void append(StringBuffer buff, String[] names, boolean displayName, String appendString,
			String seperator) {

		if (displayName)
			buff.append(names[0]);
		buff.append(appendString);

		for (int i = 1; i < names.length; ++i) {
			buff.append(seperator);
			if (displayName)
				buff.append(names[i]);
			buff.append(appendString);
		}
	}

	public static String[] split(String s, String token) {
		if (s == null) {
			return null;
		}

		StringTokenizer st = new StringTokenizer(s, token);
		int size = st.countTokens();
		String[] result = new String[size];
		for (int i = 0; i < size; ++i) {
			result[i] = st.nextToken();
		}

		return result;
	}

	public static String[] splitAndTrim(String s, String token) {
		if (s == null) {
			return null;
		}

		StringTokenizer st = new StringTokenizer(s, token);
		int size = st.countTokens();
		String[] result = new String[size];
		for (int i = 0; i < size; ++i) {
			result[i] = st.nextToken().trim();
		}

		return result;
	}

	public static String keepSpaceInContent(String xmlStr, String content) {
		StringBuffer xml = new StringBuffer(xmlStr);
		int start = xml.indexOf("<content>");
		int end = xml.indexOf("</content>");

		xml.replace(start, end, "<content xml:space=\"preserve\"><![CDATA[" + content + "]]>");

		return xml.toString();
	}

	public static String getEvalError(String error) {
		StringBuffer buff = new StringBuffer(error);
		int start = buff.indexOf("Sourced file:");
		int end = buff.indexOf(". . . '' : ");
		if ((start >= 0) && (end >= 0) && (start < end)) {
			buff.replace(start, end + 11, "");
		}

		return buff.toString();
	}

	public static String toJavascriptValue(String[] s) {
		StringBuffer buff = new StringBuffer();
		buff.append("");
		for (int i = 0; i < s.length; ++i) {
			String t = s[i];
			if (i > 0)
				buff.append(",");

			buff.append("'").append(t).append("'");
		}

		return buff.toString();
	}
	
	/**
	 * 字符转字节
	 * @param str
	 * @return
	 */
	public static byte[] toByte(String str){
		if(!BlankUtil.isBlank(str)){
			try {
				return str.getBytes("UTF8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	

	
	/**
	 *   字节转字符
	 * @param bytes
	 * @return
	 */
	public static String toStringFoByte(byte[] bytes){
		if(!BlankUtil.isBlank(bytes)){
			try {
				return new String(bytes,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * html转文本
	 * 
	 * @param htm
	 * @return
	 */
	public static String htm2txt(String htm) {
		if (htm == null) {
			return htm;
		}
		htm = htm.replace("&amp;", "&");
		htm = htm.replace("&lt;", "<");
		htm = htm.replace("&gt;", ">");
		htm = htm.replace("&quot;", "\"");
		htm = htm.replace("&nbsp;", " ");
		htm = htm.replace("<br/>", "\n");
		return htm;
	}

	/**
	 *   
	 * 
	 * 功能描述：  判断字符串是否匹配     
	 * 
	 * @param str
	 * @param matches 表达式
	 * @return 
	 * @author liubinwang
	 * create on: 2013-4-9
	 *  true匹配正确        false空或不匹配
	 */
	public static boolean strPattern(String str,String matches){
		if(BlankUtil.isBlank(str)) return false;  
		Pattern pattern = Pattern.compile(matches);
		Matcher m = pattern.matcher(str);
		if(!m.matches() ){
		    return false;
		}
		return true;
	}
	
	/**
	 * 
	 * 
	 * 功能描述：验证数据字   
	 * 
	 * @param str
	 * @return 
	 * @author liubinwang
	 * create on: 2013-4-18
	 *  true匹配正确        false空或不匹配
	 */
	public static boolean strPatternNum(String str){
		return strPattern(str,"[0-9]*");
	}
	
	
	/**
	 * 
	 * 
	 * 功能描述：  把字符串按指定字符分隔成字符数组
	 * 
	 * @param str
	 * @param c
	 * @return 
	 * @author liubinwang
	 * create on: 2013-4-9
	 *
	 */
	public static  String[] stringAnalytical(String str, char c) {
		int i = 0;
		int count = 0;
		if (str.indexOf(c) == -1){
			return new String[] { str };
		}
		char[] cs = str.toCharArray();
		int length = cs.length;
		for (i = 1; i < length - 1; i++) {// 过滤掉第一个和最后一个是分隔符的情况
			if (cs[i] == c) {
				count++;// 得到分隔符的个数
			}
		}
		String[] strArray = new String[count + 1];
		int k = 0, j = 0;
		// 去掉第一个字符是分隔符的情况
		if ((k = str.indexOf(c)) == 0){
			str = str.substring(k + 1);
		}
		while ((k = str.indexOf(c)) != -1) {
			strArray[j++] = str.substring(0, k);
			str = str.substring(k + 1);
			if ((k = str.indexOf(c)) == -1 && str.length() > 0){
				strArray[j++] = str.substring(0);
			}
		}
		return strArray;
	}

	/**
	 *   
	 * 
	 * 功能描述：过滤掉指定表达式字符串
	 * 
	 * @param str
	 * @param matches 表达式
	 * @return 
	 * @author liubinwang
	 * create on: 2013-4-9
	 *
	 */
	public static String filterString(String str,String matches){
		Pattern pattern = Pattern.compile(matches);
		Matcher m = pattern.matcher(str);
		return m.replaceAll("").trim();
	} 
	
	/**
	 * 去除字符串中的空行
	 * @param str
	 * @return
	 */
	public static String getStrTrim(String str){
		return str.replaceAll("\n\r","");
	}
	
}