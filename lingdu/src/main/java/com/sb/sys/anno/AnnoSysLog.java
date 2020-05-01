package com.sb.sys.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *com.yg.pj.sys.common.anno.RequestLog
 * @Target 注解用于描述自己定义的注解  可以对哪些元素进行描述
 * @Retention 注解描述自己定义的注解何时有效
 * @author Administrator
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoSysLog {
	
	 String value() default "";
     //String operation() default "operation";

}
