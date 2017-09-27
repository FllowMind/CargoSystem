package com.itran.cargosystem.service.module_oms.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.itran.cargosystem.entity.Employee;
import com.itran.cargosystem.entity.vo.Flight;
import com.itran.cargosystem.entity.vo.FlightDateVo;
import com.itran.cargosystem.common.util.CommonUtils;
import com.itran.cargosystem.common.util.IniUtils;
import com.itran.cargosystem.service.module_oms.EmployeeService;
import com.itran.cargosystem.service.module_oms.ExcelService;
import com.itran.cargosystem.service.module_oms.SplicingDataService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ini4j.Ini;
import org.ini4j.Profile.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ExcelServiceImpl implements ExcelService {

	@Autowired
	private SplicingDataService splicingDataService;
	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public XSSFWorkbook getWorkbook(String fileName,String date) throws Exception {
		Ini ini = IniUtils.getIni("excel.ini");
		XSSFWorkbook workbook = createWorkbook(date, fileName, ini);
		return workbook;
	}

	/*创建工作簿*/
	private XSSFWorkbook createWorkbook(String date, String fileName, Ini ini) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet(fileName);
		List<Section> sections = ini.getAll("row");
		XSSFRow row = null;
		XSSFCellStyle style = createCellStyle(workbook);
		for (Iterator<Section> iterator = sections.iterator(); iterator.hasNext();) {
			Section section = (Section) iterator.next();
			Set<String> keys = section.keySet();
			for (Iterator<String> iterator2 = keys.iterator(); iterator2.hasNext();) {
				String key = (String) iterator2.next();
				String value = section.get(key);
				if ("row".equals(key)) {
					row = createRow(spreadsheet, Integer.parseInt(value));
				} else {
					createCell(style, row, key, value);
				}
				if("row".equals(key) && "1".equals(value)) {
					List<Employee> employees = employeeService.selectByDate(date);
					for (Iterator<Employee> iterator3 = employees.iterator(); iterator3.hasNext();) {
						Employee employee = (Employee) iterator3.next();
						if(employee.getSubstitute() != null && !"".equals(employee.getSubstitute())) {
							createCell(style,row,employee.getPosition()+"",employee.getName()+"/"+employee.getSubstitute());
						} else {
							createCell(style,row,employee.getPosition()+"",employee.getName());
						}
					}
				}
			}
		}

		mergeCell(spreadsheet, ini);
		fillFlightData(spreadsheet, style, getFlightData(date));
		return workbook;
	}

	/*创建行*/
	private XSSFRow createRow(XSSFSheet spreadsheet, int value) {
		XSSFRow row;
		row = spreadsheet.createRow(value);
		row.setHeight((short)550);
		if (value == 0) {
			row.setHeight((short)800);
		}
		return row;
	}
	
	/*创建单元格样式*/
	private XSSFCellStyle createCellStyle(XSSFWorkbook workbook) {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		return style;
	}
	
	/*创建单元格*/
	private void createCell(XSSFCellStyle style, XSSFRow row, String key, String value) {
		Cell cell = row.createCell(Short.parseShort(key));
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}

	/*合并单元格*/
	private void mergeCell(XSSFSheet spreadsheet, Ini ini) {
		Section cellRangeAddresses = ini.get("cellRangeAddress");
		Collection<String> values = cellRangeAddresses.values();
		for (Iterator<String> iterator = values.iterator(); iterator.hasNext();) {
			String value = iterator.next();
			int[] cellRangeAddress = CommonUtils.StringToInt(value, ",");
			spreadsheet.addMergedRegion((new CellRangeAddress(
				cellRangeAddress[0],
				cellRangeAddress[1],
				cellRangeAddress[2],
				cellRangeAddress[3]))
			);
		}
	}
	
	/*填充数据*/
	private void fillFlightData(XSSFSheet spreadsheet, XSSFCellStyle style, List<Flight> flights)
			throws IllegalAccessException, InvocationTargetException, NoSuchFieldException {
	
		String[] fields = {"propertyname","fno","acno","type","leg","location","indynamic","outdynamic","limittime","impoupopid"
				,"cdpztime","sczjdotime","hwcctime","putWheel","kcmtime","xlxptime","hwksxjtime","xlcctime"
				,"gcmtime","eckcmtime","ecgcmtime"}; 
		for (int i=0; i < flights.size(); i++) {
			Flight flight = flights.get(i);
			XSSFRow row = spreadsheet.createRow(i + 3);	
			row.setHeight((short)550);
			row.createCell(0).setCellValue(i+1);
			for (int j = 0; j < fields.length; j++) {
				Class<? extends Flight> clazz = flight.getClass();
				Field field = clazz.getDeclaredField(fields[j]);
				field.setAccessible(true);
				String value = (String) field.get(flight);
				Cell cell = row.createCell(j+1);
				if (fields[j].equals("fno")) {
					value = value.replace(",", System.lineSeparator());	
					style.setWrapText(true);
				}
				cell.setCellStyle(style);
				cell.setCellValue(value);
			}
		}
	}
	
	/*获取航班数据*/
	private List<Flight> getFlightData(String date) throws Exception {
		FlightDateVo flightDateVo = new FlightDateVo();
		flightDateVo.setStartFdate(date);
		flightDateVo.setEndFdate(date);
		List<Flight> flights =new ArrayList<Flight>();
		flights = splicingDataService.queryFligh(flightDateVo);
		flights = splicingDataService.splicingFligh(flights,date);
		return flights;
	}
}
