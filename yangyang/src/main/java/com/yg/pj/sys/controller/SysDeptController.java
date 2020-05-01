package com.yg.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yg.pj.sys.common.vo.JsonResult;
import com.yg.pj.sys.service.SysDeptService;

/**本模块通过业务层对象执行业务逻辑，
 * 再通过VO对象封装响应结果(主要对业务层数据添加状态信息)，
 * 最后将响应结果转换为JSON格式的字符串响应到客户端。
 * @author Administrator
 *
 */
@RequestMapping("/dept/")
@RestController
public class SysDeptController {
	
	@Autowired
	private SysDeptService sysDeptService;
	
	
	
	/**基于客户端请求,访问业务层对象方法,获取菜单节点对象,并封装返回
	 * http://localhost:9099/dept/doFindZTreeNodes
	 * */
	@RequestMapping("doFindZTreeNodes")
	 public JsonResult doFindZtreeMenuNodes(){
		 return new JsonResult(
				 sysDeptService.findZtreeMenuNodes());
	 }

	
	
	
	
}
