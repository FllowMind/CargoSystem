package com.itran.cargosystem.controller.module_oms;


import com.itran.cargosystem.bean.FlightNote;
import com.itran.cargosystem.bean.vo.Result;
import com.itran.cargosystem.service.FlightNoteService;
import com.itran.cargosystem.service.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**  
* 航班组备注队
*  
* @author lsf  
* @date 2017年7月18日  新建  
*/
@Controller
@RequestMapping("/note")
public class NoteController {
	@Autowired
	FlightNoteService flightNoteService;
	
	@RequestMapping(value="/saveNote",method=RequestMethod.POST)
	@ResponseBody
	@Log(operationType="保存",operationName="保存航班组备注接口")
	public Result saveNote(FlightNote flightNote) throws Exception{
		Result result = new Result();
		if(flightNote.getSerialno()==null||flightNote.getSerialno()==""){
			result.setStatus(false);
			result.setMessage("保存失败,无航班组");
			return result;
		}
		try {
			flightNoteService.SaveNote(flightNote);
			result.setStatus(true);
			result.setMessage("保存成功");
			return result;
		} catch (Exception e) {
			result.setStatus(false);
			result.setMessage("保存失败");
			return result;
		}
		
		
	}

}
  