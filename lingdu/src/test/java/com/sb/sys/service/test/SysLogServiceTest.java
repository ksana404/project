package com.sb.sys.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sb.sys.entity.SysLog;
import com.sb.sys.entity.vo.PageObject;
import com.sb.sys.service.SysLogService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class SysLogServiceTest {
	/*****
	 * @RunWith(SpringRunner.class)
	 * 关于版本问题，有些版本测试类必须加上这个，否则容器启动失败，出现空指针异常
	 * */
	@Autowired
	private SysLogService sysLogService;
	
	@Test
	public void findPageObjectsTest() {
		String username ="admin";
		Integer currentPage =1;
		PageObject<SysLog> findPageObjects = sysLogService.findPageObjects(username, currentPage);
		log.info("test ----------- 【】【】【】findPageObjects:"+findPageObjects.toString());
		List<SysLog> records = findPageObjects.getRecords();
		for(SysLog sysLog:records) {
			log.info("test----------- >>> sysLog:"+sysLog.toString());
		}
		
	}
	
	
	@Test
	public void deleteObjectsTest() {
		
		
		int row = sysLogService.deleteObjects(12,13);
		System.out.println("------->>>>  row:"+row);
	}
}
