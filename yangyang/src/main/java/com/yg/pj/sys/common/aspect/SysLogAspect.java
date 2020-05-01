package com.yg.pj.sys.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yg.pj.sys.common.anno.RequestLogP;
import com.yg.pj.sys.common.util.IPUtils;
import com.yg.pj.sys.common.util.ShiroUtil;
import com.yg.pj.sys.entity.SysLog;
import com.yg.pj.sys.service.SysLogService;

import lombok.extern.slf4j.Slf4j;

/**
  因为日志是写的操作,对数据库有更新,而有些被注解所修饰的方法(切入点)
    的事物是 只读 ,基于默认事物传播特性,这个日志操作也只是只读.
 1.需要增加日志插入的优先级@Order(int 1-10),这样就不会被默认传播事物所限制
 @Order(3)  3比较高,这个日志切面  先执行  事务切面执行    后结束
 
 2.给日志加上新的事物  避免受到其他事物的影响
 * */



//@Order(3)
@Aspect
@Slf4j
@Component   //如果该类还有错误则把@Component 注释掉，不然会出现类实例创建失败
public class SysLogAspect {
	
	
		// @Pointcut("bean(sysUserServiceImpl)")
		// @Pointcut("@annotation(com.cy.pj.common.annotation.RequiredLog)")
	//注解使用方式
	@Pointcut("@annotation(com.yg.pj.sys.common.anno.RequestLogP)")
	public void doLogPoint() {}
	//3. 描述切面中方法  @Around注解或者其他
	//@Aournd注解内部value属性的值为一个切入点表达式或者是切入点表达式的一个引用
	
	
	//4. ProceedingJoinPoint类为一个连接点类型 作为参数  执行方法  Object返回值
	//4.1 Object proceed = pjp.proceed();	
	@Around("doLogPoint()")
     public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
    //5.1获取方法执行时间
		Long start =System.currentTimeMillis();
		 Object proceed = pjp.proceed();   //执行下一步
		Long end =System.currentTimeMillis();
		Long totalTime =end -start;
		
    	log.info("【】------------------- 【】执行时长："+totalTime);
    	
    	//6.0 调用日志保存方法
    	// Future<Integer> saveSysLog = saveSysLog(pjp, totalTime);
    	saveSysLog(pjp, totalTime);
    	 
    	// Integer rows = saveSysLog.get();
    	 //log.info("[][][]-----------[][][] Future<Integer> rows:"+rows);
    	//7.0 返回值
		return proceed;
		
	}
		
	@Autowired
	private SysLogService sysLogService;
	
	
	//定义saveSysLog(jointPoint,totalTime);
	/**
	 * @param jointPoint  连接点
	 * @param totalTime   耗时
	 * @throws NoSuchMethodException  没有找到方法异常
	 * @throws SecurityException   安全异常
	 * @throws JsonProcessingException	连接点异常
	 */
	 //在需要异步执行的业务方法上，使用@Async方法进行异步声明。  //@Async
	 private void saveSysLog(ProceedingJoinPoint pjp, Long totalTime)
			 throws NoSuchMethodException, SecurityException, JsonProcessingException 
{
	
		
		/**用户名  操作
		 * 操作类型  操作方法  方法参数   操作耗时   IP地址   日志创建时间
		 * */
		
		 //1.获取用户行为日志
		 //1.1获取目标方法对象(class,jp)
		 //1.1.1获取方法签名(通过连接点获取)
		MethodSignature ms = (MethodSignature)pjp.getSignature();
		 //1.1.2获取目标类类型
		Class<? extends Object> targetClass = pjp.getTarget().getClass();
		 //1.1.3获取目标方法对象
		
		Method targetMethod=
				 targetClass.getDeclaredMethod(ms.getName(),
				 ms.getParameterTypes());
		 //1.2获取目标方法对象上RequiredLog注解中定义的操作名
		String operation ="operation";
		RequestLogP annotation = targetMethod.getAnnotation(RequestLogP.class);
		if(annotation!=null) {
			operation=annotation.value();
		}
		 //1.3获取目标方法对象对应的类名,方法名
	String method =targetClass.getName()+"."+targetMethod.getName();
		
		 //1.4获取方法执行的实际参数
		String params = new ObjectMapper().writeValueAsString(pjp.getArgs());
		
		String ipAddr = IPUtils.getIpAddr();
		log.info("---------->>> IP:"+ipAddr);
		 //2.封装日志
		SysLog sysLog =new SysLog();
		sysLog.setIp(ipAddr);
		
		sysLog.setUsername(ShiroUtil.getUsername());
		sysLog.setMethod(method);
		sysLog.setOperation(operation);
		sysLog.setParams(params);
		sysLog.setTime(totalTime);
		sysLog.setCreatedTime(new Date());
		
	
		
		/*
		 * new Thread() { public void run() { //目标业务 //3.将日志存储到数据库
		 * sysLogService.saveSysLog(sysLog); String tName22 =
		 * Thread.currentThread().getName();
		 * System.out.println("[][][][]------------saveSysLog run-tName22:"+tName22); };
		 * }.start();
		 */
		

		
		//目标业务
		//3.将日志存储到数据库
		sysLogService.saveSysLog(sysLog);
		
		//获取返回结果
		
	
	}

	
	

}
