package com.yg.pj.sys.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Aspect
@Component
@Slf4j
public class SysLogAspectDemo02 {

	//1. 定义切面 @Aspect
		//2. 定义切入点  @Pointcut bean表达式或者其他 
	//2.1  表达式 @Pointcut("bean(sysLogServiceImpl)")
	@Pointcut("bean(sysLogServiceImpl)")
	public void pointCut() {}
		
		//3. 描述切面中方法  @Around注解或者其他 返回值为Object 参数为ProceedingJoinPoint
		//@Aournd注解内部value属性的值为一个切入点表达式或者是切入点表达式的一个引用
	@Around(value ="pointCut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		Long begin =System.currentTimeMillis();
		log.info("----- ---- ???-------- begin:"+begin);
		Object proceed = pjp.proceed();
		Long after =System.currentTimeMillis();
		log.info("----- ---- ???--------after:"+after);
		
		return proceed;
		
	}
		//4. ProceedingJoinPoint类为一个连接点类型 作为参数  执行方法proceed()
		//4.1 Object proceed = pjp.proceed();
}
