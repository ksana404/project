package com.sb.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;




/*各个模块开始页面跳转Controller
 * 
 * */
@RequestMapping("/")
@Controller
public class PageController {
	
	/*http://localhost/doIndexUI
	 * */
	@RequestMapping("doIndexUI")
	public String doIndex() {
		return "starter";
	}
	
	@RequestMapping("doLogUI")
	public String doLogUI() {
		return "login";
	}
	
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}
	
	//通用页面跳转
	@RequestMapping("{module}/{moduleUI}")
	public String doModuleUI(@PathVariable String moduleUI) {
		return "sys/"+moduleUI;
	}
	
	
}
