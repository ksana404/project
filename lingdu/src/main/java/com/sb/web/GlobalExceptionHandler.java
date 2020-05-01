package com.sb.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sb.sys.entity.vo.JsonResult;

@ControllerAdvice
public class GlobalExceptionHandler {
	//JDK中的自带的日志API
		@ExceptionHandler(RuntimeException.class)
	    @ResponseBody
		public JsonResult doHandleRuntimeException(
				RuntimeException e){
	    	e.printStackTrace();//也可以写日志
			return new JsonResult(e);//封装异常信息
		}
}
