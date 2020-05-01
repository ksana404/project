package com.sb.sys.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sb.sys.service.SysMenuService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class SysMenuServiceTest {
	
	
	/*****
	 * @RunWith(SpringRunner.class)
	 * 关于版本问题，有些版本测试类必须加上这个，否则容器启动失败，出现空指针异常
	 * */
	@Autowired
	private SysMenuService sysMenuService;
	
	@Test
	public void deleteObjectTest() {
		sysMenuService.deleteObject(48);
		
	}
	
	

}
