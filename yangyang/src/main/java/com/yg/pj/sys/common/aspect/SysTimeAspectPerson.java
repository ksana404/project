package com.yg.pj.sys.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**所有通知类型的demo 切入点为bean
 * @author Administrator
 *
 */
//@Aspect
//@Service
@Slf4j
public class SysTimeAspectPerson {
	//1. 定义切面 @Aspect @Service/@Component
	
		//2. 定义切入点  @Pointcut bean表达式或者其他 
		//2.1  表达式 @Pointcut("bean(sysLogServiceImpl)")
	//3. 描述切面中方法  
	//@Around @Before @TagetMethod @Around @After @AfterReturning @AfterThrowing
		//4. Around切入点参数类型ProceedingJoinPoint, 其他的通知为JoinPoint.
	
	
	@Pointcut("@annotation(com.yg.pj.sys.common.anno.RequestTime)")
	//@Pointcut("bean(sysUserServiceImpl)")
	public void doTime() {};
		//3. 描述切面中方法  
	
	   @Before("doTime()")
		public void doBefore(JoinPoint jp) {
			log.info("[][]--[][] doBefore ");
		}
	   
	   @After("doTime()")
		public void doAfter(JoinPoint jp) {
			log.info("[][]--[][] After ");
		}
	   @AfterReturning("doTime()")
	   public void doAfterReturning(JoinPoint jp) {
		   log.info("[][]--[][] AfterReturning ");
	   }
	   @AfterThrowing("doTime()")
	   public void doAfterThrowing(JoinPoint jp) {
		   log.info("[][]--[][] AfterThrowing ");
	   }
	   
	   @Around("doTime()")
	   public Object doAround(ProceedingJoinPoint jp) throws Throwable {
		   
		   log.info("[][]--[][] Around 00000000000 SysTimeAspectPerson");
		   
		   Object proceed = jp.proceed();
		   
		   log.info("[][]--[][] Around 11111111111 SysTimeAspectPerson");
		   
		return proceed;
	   }
	  
		//5.触发测试
}
