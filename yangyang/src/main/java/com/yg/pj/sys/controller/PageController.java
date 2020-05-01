package com.yg.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yg.pj.sys.common.util.ShiroUtil;
import com.yg.pj.sys.entity.SysUser;


/**基于此Controller对象处理项目所有页面请求
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/")
public class PageController {
	
	
		/*返回首页面 */
	@RequestMapping("doIndexUI")
	public String doIndexUI( Model model) {
		SysUser user = ShiroUtil.getUser();
		model.addAttribute("user",user);
		
		return "starter";
	}
	
	/*返分页页面 */
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}
	
	/*返回列表日志页面 */
	@RequestMapping("log/log_list")
	public String doLogListUI() {
		return "sys/log_list";
	}
	
	/*返回菜单列表页面 */
	@RequestMapping("menu/menu_list")
	public String doMenuUI() {
		return "sys/menu_list";
	}
	
	
	/*
	 * @PathVariable 用法？
	 * */
	/*页面跳转共性提取 module*/
	@RequestMapping("{module}/{moduleUI}")
	public String doModuleUI(@PathVariable  String moduleUI) {
		
		return "sys/"+moduleUI;
		
	}
	
	@RequestMapping("doLoginUI")
	public String doLoginUI(){
			return "login";
	}
	
	
	
}
