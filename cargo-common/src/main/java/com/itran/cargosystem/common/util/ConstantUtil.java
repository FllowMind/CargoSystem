package com.itran.cargosystem.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * 功能描述：常量类,用于设置在程序中一些公共常量
 * 
 * @author   create on: 2012-6-6
 * 
 */
public class ConstantUtil {

	/**
	 * 表格的行高
	 */
	private final static int TALBE_ITEM_ROW_HEIGHT = 29;

	private final static int TAXI_DAFAULT_FUEL = 3000;

	public final static String ONLINE = "Online";

	public final static String OFFLINE = "Offline";

	public final static String LOAD_LIST = "loadList";

	// 主舱
	public final static int MAIN_CHAMBER_INDEX = 1;
	// 腹舱
	public final static int BELLYHOLD_INDEX = 0;

	// 主舱
	public final static String MAIN_CHAMBER = "Main Deck";
	// 腹舱
	public final static String BELLYHOLD = "Lower Deck";
	
	public final static String DIRECTION_ANY = "Any";// 轮廓代码 列Direction方向  选项：Any 随意
	public final static String DIRECTION_LATERAL = "Lateral";// 轮廓代码 列Direction方向  选项：Lateral  横向
	public final static String DIRECTION_VERTICAL = "Vertical";// 轮廓代码 列Direction方向  选项：Vertical 竖向

	// 允许
	public final static String DESK_ALLOW = "Allow";
	// 不允许
	public final static String DESK_REJECT = "Reject";
	// 可用
	public final static String INUSE_YES = "Yes";
	// 不可用
	public final static String INUSE_NO = "No";

	// 左侧
	public final static int LEFT_SIDE = 1;
	// 右侧
	public final static int RIGHT_SIDE = 0;

	// 左侧
	public final static String LEFT_SIDE_STR = "Left Side";
	// 右侧
	public final static String RIGHT_SIDE_STR = "Right Side";

	public final static String TAKEOFF = "Take Off";// 起飞

	public final static String BERBON = "Landing";// 落地

	public final static String SUN = "SUN"; // 星期日
	public final static String MON = "MON"; // 星期一
	public final static String TUE = "TUE"; // 星期二
	public final static String WED = "WED"; // 星期三
	public final static String THU = "THU"; // 星期四
	public final static String FRI = "FRI"; // 星期五
	public final static String SAT = "SAT"; // 星期六

	//
	public final static int[] chamberArr = new int[] { MAIN_CHAMBER_INDEX, BELLYHOLD_INDEX };

	public final static List<String> chamberList = new ArrayList<String>();

	public final static String BLK = "BLK";
	// 小于
	public final static String LT = ")";
	// 小于等于
	public final static String LE = "]";
	// 大于
	public final static String GT = "(";
	// 大于等于
	public final static String GE = "[";

	public final static String LEFT = "left";
	public final static String RIGHT = "right";
	public final static String MID = "MID";

	public final static String KILOGRAM = "KG";// 公斤

	public final static String LB = "LB";// 磅

	public final static String CUBIC_FEET = "Cubic Feet";// 立方英尺

	public final static String LITER = "Liter";// 公升

	public final static String GALLON = "GAL";// 加仑

	public final static String IN = "IN";// 英寸

	public final static String SQUARE_FEET = "Square Feet";// 平方英尺

	public final static String CARGO = "C";

	static {
		chamberList.add(MAIN_CHAMBER);
		chamberList.add(BELLYHOLD);
	}

	public static int getTalbeItemRowHeight() {
		return TALBE_ITEM_ROW_HEIGHT;
	}

	public static int getTaxiDafaultFuel() {
		return TAXI_DAFAULT_FUEL;
	}

	// 报载格式
	public static final String CPM = "CPM";
	public static final String UWS = "UWS";

	// 空数据
	public static final String NIL = "NIL";

	/**
	 * 邮件类型
	 */
	public final static int MAIL_RECEIVE = 0;// 接收
	public final static int MAIL_SEND = 1; // 发送

	public final static int EMAIL = 0; // 邮件
	public final static int MESSAGE = 1; // 电报
	public final static int STATION = 2; // 提醒
	
	public final static int MAIL_NO_READ = 0;// 未读
	public final static int MAIL_IS_READ = 1;// 已读

	public final static int MAIL_NO_FILE = 0;// 无附件
	public final static int MAIL_IS_FILE = 1;// 有附件

	/**
	 * 邮件接收类型
	 * **/
	public final static String MAIL_REV_POP3 = "POP3";
	public final static String MAIL_REV_IMAP = "IMAP";

	/** 不使用代理 **/
	public final static int PROXY_NO = 0;
	/** 使用代理 **/
	public final static int PROXY_YES = 1;
	/** 使用IE代理 **/
	public final static int PROXY_IE = 2;

	/** 定义properties Key **/
	public final static String ISPROXY = "mail.isproxy";
	public final static String PROXY_HOST = "mail.proxy.host";
	public final static String PROXY_PORT = "mail.proxy.port";
	public final static String PROXY_USERNAME = "mail.proxy.username";
	public final static String PROXY_PASSWORD = "mail.proxy.password";
	public final static String EMAIL_SITA_HOUR = "email_sita_hour";
	public final static String EMAIL_DATE="emial_date";

	/** mail smtp pop3 key **/
	public final static String MAIL_KEY = "MAIL87654321_1947F249j15L";
	
	//public final static String MAIL_KEY = "MAIL87654321_4664D205j16M";
	
	/** mail imap key **/
	public final static String MAIL_IMAP_KEY = "IMAP87654321_4273A396l5oM";

	/** html text xml convert key **/
	public final static String HTML_KEY = "HtmlToXml87654321_6393B64DmRRN";

	/** 未知错误 */
	public final static int MAIL_ERROR = 1;
	/** 无效KEY */
	public final static int MAIL_KEY_ERROR = 2;
	/** 邮件无代理连接错误 **/
	public final static int MAIL_CONNECT_NO_PROXY_ERROR = 3;
	/** 邮件有代理连接错误 **/
	public final static int MAIL_CONNECT_YES_PROXY_ERROR = 4;
	/** 邮件关闭连接错误 **/
	public final static int MAIL_CLOSE_CONNECT_ERROR = 5;
	/** 邮件发送成功 **/
	public final static int MAIL_SEND_SUCCESS = 6;
	/** 邮件接收成功 **/
	public final static int MAIL_RECEIVE_SUCCESS = 7;
	/** 邮件登录错误 **/
	public final static int MAIL_LOGIN_ERROR = 8;
	/** 邮件imap key 无效 **/
	public final static int MAIL_IMAP_KEY_ERROR = 9;
	/** html key 无效 **/
	public final static int HTML_KEY_ERROR = 10;
	/** 没有设置邮件 **/
	public final static int MAIL_SETTING_ERROR = 11;
	/** 有设置邮件 **/
	public final static int MAIL_SETTING_SUCCESS = 12;
	/** 没有收到新邮件 **/
	public final static int MAIL_RECEIVE_NONEW = 13;

	/** 包线阶段 */
	public final static String RAMPTAXI = "RampTaxi";// 停机坪
	public final static String INFLIGHT = "Inflight";// 飞行中
	public final static String TOW = "TakeOff";// 起飞
	public final static String ZFW = "ZeroFuel";// 零油
	public final static String LW = "Landing";// 落地

	/* 水平安定面最大最小值 */
	public final static String AND = "AND";//
	public final static String ANU = "ANU";//

}
