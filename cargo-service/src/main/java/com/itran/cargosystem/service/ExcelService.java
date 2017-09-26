package com.itran.cargosystem.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface ExcelService {
	
	
	/**
	 * 获取工作簿
	 * 
	 * @param fileName
	 * @return 返回工作簿
	 * @throws Exception 
	 */
	public XSSFWorkbook getWorkbook(String date, String fileName) throws Exception;

}
