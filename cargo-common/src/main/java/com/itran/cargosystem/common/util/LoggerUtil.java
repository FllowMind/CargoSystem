package com.itran.cargosystem.common.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.slf4j.LoggerFactory;

/**
 * 自定义Log<br>
 * 
 * 自己指定log文件的存放位置
 * 
 * @author WJRONG
 */
public class LoggerUtil {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(LoggerUtil.class);

	/** 存放Log的文件夹 **/
	private static String logPathDir = "C://clps//H2SyncE//";

	/**
	 * 得到要记录的日志的路径及文件名称
	 * 
	 * @return 存放Log的文件路径
	 */

	private static String getLogName(Level level, String pathDir) {

		StringBuffer logPath = new StringBuffer();

		logPath.append(logPathDir);

		if (!BlankUtil.isBlank(pathDir)) {
			logPath.append(pathDir);
		}

		File file = new File(logPath.toString());

		if (!file.exists())

			file.mkdir();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");

		logPath.append(level.getName()).append(".");

		logPath.append(sdf.format(new Date()) + ".log");

		return logPath.toString();

	}

	/**
	 * 得到要记录的日志的路径及文件名称
	 */
	@SuppressWarnings("unused")
	private static String getLogName(Level level) {
		String logFliePath = getLogName(level, null);
		return logFliePath.toString();
	}

	/**
	 * 
	 * 配置Logger对象输出日志文件路径
	 * 
	 * @param logger
	 */
	public static void setLogingProperties(Logger logger) {
		setLogingProperties(logger, Level.ALL);
	}

	/**
	 * 
	 * 配置Logger对象输出日志文件路径
	 * 
	 * @param logger
	 * 
	 * @param level
	 *            在日志文件中输出level级别以上的信息
	 * 
	 */
	public static void setLogingProperties(Logger logger, Level level, String pathDir) {

		FileHandler fileHandler;

		try {

			fileHandler = new FileHandler(getLogName(level, pathDir), true);

			logger.addHandler(fileHandler);// 日志输出文件

			logger.setLevel(Level.INFO);

			fileHandler.setFormatter(new SimpleFormatter());// 输出格式
			
			//输出到控制台
			ConsoleHandler consoleHandler =new ConsoleHandler();
            consoleHandler.setLevel(Level.SEVERE); 
            logger.addHandler(consoleHandler); 

		} catch (SecurityException e) {
			log.debug("安全性错误", e);
		} catch (IOException e) {
			log.debug("读取文件日志错误", e);
		}

	}

	/**
	 * 
	 * 配置Logger对象输出日志文件路径
	 * 
	 * @param logger
	 * 
	 * @param level
	 *            在日志文件中输出level级别以上的信息
	 */
	public static void setLogingProperties(Logger logger, Level level) {
		setLogingProperties(logger, level, null);
	}

	/**
	 * 查找或创建一个日志记录器。
	 * 
	 * @param name
	 *            记录器的名称
	 * @return 日志记录器
	 * 
	 * @param path
	 *            存日志的目录
	 */
	public static Logger getLogger(String name, String path) {
		Logger logger = Logger.getLogger(name);
		LoggerUtil.setLogingProperties(logger, Level.ALL, path);
		return logger;
	}

	/**
	 * 查找或创建一个日志记录器。
	 * 
	 * @param name
	 *            记录器的名称
	 * @return 日志记录器
	 */
	public static Logger getLogger(String name) {
		Logger logger = Logger.getLogger(name);
		LoggerUtil.setLogingProperties(logger);
		return logger;
	}

	public static void main(String[] args) {

		Logger logger = LoggerUtil.getLogger("LoggerUtil");

		try {
			System.out.println(1 / 0);
		} catch (Exception e) {
			logger.log(Level.INFO, "测试自定义日志记录器", e);
		}
	}

}