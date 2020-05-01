package com.sb.sys.aspect;

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
import com.sb.sys.anno.AnnoSysLog;
import com.sb.sys.dao.SysLogDao;
import com.sb.sys.entity.SysLog;
import com.sb.sys.util.IPUtils;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class AspectSysLog {
	
	//1. 定义切面 @Aspect
	//2. 定义切入点  @Pointcut bean表达式或者其他   注解
	//2.1  表达式 @Pointcut("bean(sysLogServiceImpl)")
	//@Pointcut("@annotation(com.yg.pj.sys.common.anno.RequestLogP)")
	@Pointcut("@annotation(com.sb.sys.anno.AnnoSysLog)")
	private void piontCut() {};
	
	//3. 描述切面中方法  @Around注解或者其他
	//@Aournd注解内部value属性的值为一个切入点表达式或者是切入点表达式的一个引用
	//4. ProceedingJoinPoint类为一个连接点类型 作为参数  执行方法 Object返回值
	//4.1 Object proceed = pjp.proceed();
	//为了计算耗时
	
	@Around("piontCut()")
	public Object getAround(ProceedingJoinPoint pj) throws Throwable {
		
		Long start =System.currentTimeMillis();
	//	log.info("----------------[] [][][][][][]start:"+start);
		
		Object proceed = pj.proceed();
		
		Long end =System.currentTimeMillis();
		//log.info("----------------[] end:"+end);
		
		Long count =end -start;
		log.info("----------------[] [][][][][][] 总耗时 count:"+count);
		
		//5. 调用日志插入方法
		saveLog(pj,count);
		
		
		return proceed;
		
	}

	@Autowired
	private SysLogDao sysLogDao;
	
	//6.日志插入方法 定义
	private void saveLog(ProceedingJoinPoint pjp, Long totalTime) throws NoSuchMethodException, SecurityException, JsonProcessingException {
		
		

		/**用户名  操作
		 * 操作类型  操作方法  方法参数   操作耗时   IP地址   日志创建时间
		 * 
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
		AnnoSysLog annotation = targetMethod.getAnnotation(AnnoSysLog.class);
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
		
		sysLog.setUsername("ksana");
		sysLog.setOperation(operation);
		sysLog.setMethod(method);
		sysLog.setParams(params);
		sysLog.setTime(totalTime);
		sysLog.setCreatedTime(new Date());
		
		log.info("----------------[] [][][][][][] sysLog"+sysLog.toString());
		
		 //3.将日志存储到数据库
		sysLogDao.insertObject(sysLog);
		
		
	}
	
	

}
