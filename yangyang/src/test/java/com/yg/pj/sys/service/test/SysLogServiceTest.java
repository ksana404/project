package com.yg.pj.sys.service.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yg.pj.sys.common.vo.PageObject;
import com.yg.pj.sys.entity.SysLog;
import com.yg.pj.sys.service.SysLogService;

/**
 * SysLogService 业务层测试
 * @author Administrator
 *
 */
@SpringBootTest
public class SysLogServiceTest {
	
	@Autowired
	private SysLogService sysLogService;
	
	@Test
	public void findPageObjectTest() {
	String name="admin";
	Integer pageCurrent =1;
		PageObject<SysLog> findPageObject = sysLogService.findPageObject(name, pageCurrent);
		
		System.out.println("findPageObjectTest -findPageObject:"+findPageObject.toString());
		List<SysLog> records = findPageObject.getRecords();
		int n=0;
		for(SysLog sysLog: records) {
			n++;
			System.out.println(n+"sysLog:"+sysLog.toString());
		}
		
		
		
		
		
	}

}
