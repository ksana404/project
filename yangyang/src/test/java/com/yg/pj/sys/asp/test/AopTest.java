package com.yg.pj.sys.asp.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.yg.pj.sys.common.vo.PageObject;
import com.yg.pj.sys.entity.SysLog;
import com.yg.pj.sys.service.impl.SysLogServiceImpl;

@SpringBootTest
public class AopTest {
	
	@Autowired
	private ApplicationContext ctx;
	//org.springframework.context.ApplicationContext;  
	//导包org.springframework   spring容器中spring框架包
	
	@Test
	public void testSysLogServiceImpl() {
		SysLogServiceImpl bean = ctx.getBean("sysLogServiceImpl",SysLogServiceImpl.class);
	
		PageObject<SysLog> findPageObject = bean.findPageObject("admin", 2);
		System.out.println("findPageObject:"+findPageObject.toString());
	}

}
