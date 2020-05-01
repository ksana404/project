package com.sb.sys.aspect.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import org.springframework.test.context.junit4.SpringRunner;

import com.sb.sys.service.SysLogService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AspectDemoTest {
	
	/*
	 * 1.隔壁SysLogServiceTest有调用测试
	 * 2.直接从bean容器中拿取 更加直观 
	 * 
	 *   ---1.通过	@Autowired 注解 把ApplicationContext对象实例注入到act引用变量里面
	 * 成为 AspectDemoTest 的一个属性。
	 * AspectDemoTest 实例化调用方法 getAroundTest() 
	 * ---2.方法内部  的行为是  act调用 act.getBean（）方法 获得Bean容器中的名字叫（key为）sysLogServiceImpl的对象
	 * 增加参数，确定其返回值类型，下面进一步调用内部方法调用
	 * */
	@Autowired
	private ApplicationContext act;
	//org.springframework  spring框架的
	
	@Test
	public void getAroundTest() {
		SysLogService bean = act.getBean("sysLogServiceImpl",SysLogService.class);
		
		int deleteObjects = bean.deleteObjects(10,11);
		System.out.println("------------>>>   删除了："+deleteObjects);
		
	}

}
