package com.yg.pj.sys.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 07文档
 * 将此日志切面类作为核心业务增强（一个横切面对象）类，用于输出业务执行时长
 * aspect:切面    一般为某个类和某个对象，用于控制和处理aop的整体逻辑
 * Advice:通知   在某个连接点上做功能增强
 * pointcut:切入点   对连接点拦截内容的定义，多个连接点的结合
 * joinpoint:连接点    程序执行某个特定的点   一般指拦截的方法

说明：
@Aspect 注解用于标识或者描述AOP中的切面类型，基于切面类型构建的
对象用于为目标对象进行功能扩展或控制目标对象的执行。
@Pointcut注解用于描述切面中的方法，并定义切面中的切入点（基于特定表达式的方式进行描述），
在本案例中切入点表达式用的是bean表达式，这个表达式以bean开头，bean括号中的内容为
一个spring管理的某个bean对象的名字。
@Around注解用于描述切面中方法，这样的方法会被认为是一个环绕通知
（核心业务方法执行之前和之后要执行的一个动作），
@Aournd注解内部value属性的值为一个切入点表达式或者是切入点表达式的一个引用
(这个引用为一个@PointCut注解描述的方法的方法名)。
ProceedingJoinPoint类为一个连接点类型，此类型的对象用于封装要执行的目标方法相关的一些信息。
一般用于@Around注解描述的方法参数。
 * */
//@Aspect
//@Component
@Slf4j
public class SysLogAspectDemo01 {
	//1. 定义切面 @Aspect
	//2. 定义切入点  @Pointcut bean表达式或者其他 
	//2.1  表达式 @Pointcut("bean(sysLogServiceImpl)")
	//3. 描述切面中方法  @Around注解或者其他
	//@Aournd注解内部value属性的值为一个切入点表达式或者是切入点表达式的一个引用
	//4. ProceedingJoinPoint类为一个连接点类型 作为参数  执行方法
	//4.1 Object proceed = pjp.proceed();
	//5.其他测试
	
	
	//1.定义切面 @Aspect
		//2.定义切入点  @Pointcut bean表达式或者其他 map kv
	//com.yg.pj.sys.service.impl.SysLogServiceImpl
	 @Pointcut("bean(sysLogServiceImpl)")
	public void pointCut(){}
		//3.描述切面中方法  @Around注解或者其他
	@Around(value = "pointCut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable 
 {
		Long begin =System.currentTimeMillis();
		log.info("-------------->>>  begin:"+begin);
		Object proceed = pjp.proceed();
		
		Long after =System.currentTimeMillis();
		log.info("-------------->>>  after:"+after);
		Long count =after-begin;
		log.info("-------------->>> 总耗时 count:"+count+"毫秒");
		return proceed;
		
	}
		//@Aournd注解内部value属性的值为一个切入点表达式或者是切入点表达式的一个引用
		//4.ProceedingJoinPoint类为一个连接点类型 作为参数  执行方法
		//5.其他测试
	
	

}
