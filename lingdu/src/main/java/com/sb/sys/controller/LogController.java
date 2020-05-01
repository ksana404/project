package com.sb.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sb.sys.entity.SysLog;
import com.sb.sys.entity.vo.JsonResult;
import com.sb.sys.entity.vo.PageObject;
import com.sb.sys.service.SysLogService;


/**日志管理模块控制层
 * 
 * */
@RequestMapping("/log/")
@Controller
public class LogController {
	@Autowired
	private SysLogService sysLogService;
	
	@RequestMapping("log_list")
	public String logList() {
		return "sys/log_list";
		
	}
	
	
	/**
	 * @param username 
	 * @param currentPage
	 * @return  JsonResult 返回结果数据封装
	 * http://localhost:8091/log/doFindObjects?username=admin&currentPage=3
	 * ?后面是参数 & 是把多个参数进行区分
	 */
	@ResponseBody
	@RequestMapping("doFindObjects")
	public JsonResult doFindObjects(String username,Integer pageCurrent) {
		PageObject<SysLog> page = sysLogService.findPageObjects(username, pageCurrent);
		
		return new JsonResult(page);
		
	}
	
	
	/**doDeleteObjects
	 * 根据用户提交的日志ID数组，用来删除日志
	 * @param ids
	 * @return 返回处理结果JsonResult
	 */
	@ResponseBody
	@RequestMapping("doDeleteObjects")
	public JsonResult doDeleteObjects(@RequestParam("ids") Integer...ids) {
		
	 int row= sysLogService.deleteObjects(ids);
	 
		return new JsonResult("恭喜你删除了"+row+"行");
		
	}

}
