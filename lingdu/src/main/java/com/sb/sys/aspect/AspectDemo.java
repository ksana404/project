package com.sb.sys.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 核心组件
aspect:切面   切面对象，一般为一个具体的类对象（通过@Aspect声明）
Advice:通知  在切面的几个特定连接点执行功能扩展，例如（around,before,after等）
Pointcut:切入点  对连接点拦截内容的定义，一般是多个连接点的结合
Jointpoint:连接点  程序执行时某个特定的点，一般指被拦截到的方法
 *
 *依赖：
 */
@Aspect
@Component
@Slf4j
public class AspectDemo {
	
	//1. 定义切面 @Aspect
	
		//2. 定义切入点  @Pointcut bean表达式或者其他 
	//2.1  表达式 @Pointcut("bean(sysLogServiceImpl)")
	@Pointcut("bean(sysLogServiceImpl)")
	public void pointCut() {}
		
	
		//3. 描述切面中方法  @Around注解或者其他
	//@Aournd注解内部value属性的值为一个切入点表达式或者是切入点表达式的一个引用
			//4. ProceedingJoinPoint类为一个连接点类型 作为参数  执行方法 Object返回值
	//4.1 Object proceed = pjp.proceed();
	//4.2 log输出
	@Around("pointCut()")
	public Object getAround(ProceedingJoinPoint jp) throws Throwable {
		
		log.info("------------->>>   开始啰");
		Object proceed = jp.proceed();
		log.info("------------->>>   结束罗");
		
		return proceed;
		
	}
	
	
		
		
		//5.其他测试
	

}
