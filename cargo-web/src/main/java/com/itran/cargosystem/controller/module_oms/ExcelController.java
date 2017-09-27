package com.itran.cargosystem.controller.module_oms;

import java.io.IOException;
import java.io.OutputStream;


import com.itran.cargosystem.entity.Employee;
import com.itran.cargosystem.entity.vo.Result;
import com.itran.cargosystem.function.log.Log;
import com.itran.cargosystem.service.module_oms.EmployeeService;
import com.itran.cargosystem.service.module_oms.ExcelService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/excel")
public class ExcelController {
	
	@Autowired
	private ExcelService excelService;
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/exportExcel")
	@Log(operationType="excel导出操作:",operationName="导出航班信息")
	public @ResponseBody
	String exportExcel(HttpServletResponse response, String date) throws Exception {
		response.setHeader("Content-Disposition", "attachment;filename=" + date.replace("-", "") + ".xlsx");
		XSSFWorkbook workbook = excelService.getWorkbook("record.xlsx",date);
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			workbook.write(out);
		} catch (IOException e) {
			throw new RuntimeException("文件读写异常");
		} finally {
			release(out, workbook);
		}
		return "status";
	}
		
	/*释放资源*/
	private void release(OutputStream out, XSSFWorkbook workbook) {
		if(out != null) {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		if(workbook != null) {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/recordEmployee", method = RequestMethod.POST)
	@Log(operationType="员工录入操作:",operationName="录入员工信息")
	public @ResponseBody Result recordEmployee(Employee e) throws Exception {
		Result result =	employeeService.save(e);
		return result;
	}
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public @ResponseBody Result test() {
		Result res = new Result();
		res.setData("aabb");
		res.setMessage("成功");
		res.setStatus(true);
		return res;
	}

}
