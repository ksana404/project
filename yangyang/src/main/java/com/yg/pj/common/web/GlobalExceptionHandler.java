package com.yg.pj.common.web;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yg.pj.sys.common.vo.JsonResult;

@ControllerAdvice
/*全局异常处理类
 * 
 * 定义异常处理类，对控制层可能出现的异常，进行统一异常处理
 * */
public class GlobalExceptionHandler {
	
	
	//JDK中的自带的日志API
		@ExceptionHandler(RuntimeException.class)
	    @ResponseBody
		public JsonResult doHandleRuntimeException(RuntimeException e){
	    	e.printStackTrace();//也可以写日志
			return new JsonResult(e);//封装异常信息
		}
		
		
		
/**之所以要写这里 是因为这是使用了其他框架  不是在service层中的异常 
 * 当我们在执行登录操作时,为了提高用户体验,可对系统中的异常信息进行处理,
 * 例如,在统一异常处理类中添加如下方法:
 * */
		@ExceptionHandler(ShiroException.class)
	    @ResponseBody
		public JsonResult doHandleShiroException(ShiroException e){
	    	e.printStackTrace();//也可以写日志
	    	JsonResult r =new JsonResult();
	    	r.setState(0);
	    	if(e instanceof UnknownAccountException) {
	    		r.setMessage("账户不存在");
	    	}else if(e instanceof LockedAccountException) {
	    		r.setMessage("账户已被禁用");
	    	}else if(e instanceof IncorrectCredentialsException) {
	    		r.setMessage("密码不正确");
	    	}else if(e instanceof AuthorizationException) {
	    		r.setMessage("没有此操作权限");
	    	}else {
	    		r.setMessage("系统维护中");
	    	}
	    	e.printStackTrace();
	    	
			return r;//封装异常信息
		}	
		
}
