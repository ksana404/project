package com.yg.pj.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yg.pj.sys.common.vo.JsonResult;
import com.yg.pj.sys.common.vo.PageObject;
import com.yg.pj.sys.entity.SysLog;
import com.yg.pj.sys.service.SysLogService;

/**日志控制层
 * @author Administrator
 * ###注解里面只有一个值，就一定是给value的值
 * 1----@RequestParam() String username
 * 注解大解析@RequestParam(),写在参数之前，当这个参数一定要有的时候，加上这个注解
 * 若不是必须的，不加
 * @RequestParam() String username
 *  http://localhost:9099/log/doFindPageObjects?pageCurrent=-5       no!
 *  http://localhost:9099/log/doFindPageObjects?username=admin&pageCurrent=-5      yes!
 *  
 *  
 *  2----- @RequestParam("name") String username
 *写在参数之前,括号里面填写客户端传过来URL的参数的名字
 *当客户端名字一定时，而服务端不一样，使用这种形式进行匹配，参数。
 *
 * @RequestParam("page") Integer pageCurrent
 * http://localhost:9099/log/doFindPageObjects?pageCurrent=5  no!
 * http://localhost:9099/log/doFindPageObjects?page=5  yes!
 * 
 */
@Controller
@RequestMapping("/log/")
public class SysLogController {
	
	@Autowired
	private SysLogService sysLogService;
	
	/**分页请求处理方法  通过用户名和当前页码值查询页面信息 */
	/**
	 * @param username 用户名
	 * @param pageCurrent 当前页码
	 * @return JsonResult 封装处理结果 转化为json格式字符串 springmvc 利用jackson API
	 * localhost:9099/log/doFindPageObjects?username=admin&pageCurrent=5
	 * @throws JsonProcessingException 
	 */
	
	@ResponseBody
	@RequestMapping(value = "doFindPageObjects",method = RequestMethod.GET) //@RequestParam()
	public JsonResult doFindPageObjects( String username,
			@RequestParam() Integer pageCurrent) throws JsonProcessingException {
		//方法调用
		PageObject<SysLog> pageObject = sysLogService.findPageObject(username, pageCurrent);
		
		//打桩输出
		List<SysLog> records = pageObject.getRecords();
		for(SysLog sysLog:records) {
			System.out.println("sysLog:"+sysLog);
		}
		System.out.println("pageObject:"+pageObject.toString());
		
		//返回结果
		return new JsonResult(pageObject);
		
	}
	/**
	 * 在日志控制层对象中，添加用于处理日志删除请求的方法。
	 * 首先在此方法中通过形参接收客户端提交的数据，
	 * 然后调用业务层对象执行删除操作，最后封装执行结果，
	 * 并在运行时将响应对象转换为JSON格式的字符串，响应到客户端。
	 * */
	@ResponseBody
	@RequestMapping("doDeleteObjects")
	 public JsonResult doDeleteObjects (Integer... ids){
		
		//1.数据转换    框架会把从url接收到的数据自动进行转换
		
		//2.调用service处理
		int row =sysLogService.doDeleteObjects(ids);
		//3.返回响应结果   响应成功！！！失败会自动报错
		return  new JsonResult("恭喜，成功删除了"+row+"行！！");
		}

}
