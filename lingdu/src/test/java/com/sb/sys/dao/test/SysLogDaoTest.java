package com.sb.sys.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sb.sys.dao.SysLogDao;
import com.sb.sys.entity.SysLog;

import lombok.extern.slf4j.Slf4j;

/*SysLogDao 测试类
 * 
 * */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysLogDaoTest {
	
	@Autowired
	private SysLogDao sysLogDao;
	
	@Test
	public void doFindCountTest() {
		String username ="admin";
		
		 Integer doFindCount = sysLogDao.doFindCount(username);
		
		System.out.println("查询日志记录总数为："+doFindCount);

	}
	
	@Test
	public void doFindSysLogsTest() {
		String username ="admin";
		Integer indexPage=2;
		Integer endPage=5;
		List<SysLog> doFindSysLogs = sysLogDao.doFindSysLogs(username, indexPage, endPage);
		
		for(SysLog sysLog:doFindSysLogs) {
			log.info("----------- >>> sysLog:"+sysLog.toString());
		}
	}
	
	@Test
	public void deleteObjectsTest() {
		
		
		int row = sysLogDao.deleteObjects(9,10,11);
		System.out.println("row:"+row);
	}
	
	@Test
	public void insertObjectTest() {
		SysLog sysLog =new SysLog();
		
		
		sysLog.setUsername("ksana");
		sysLog.setOperation("click");
		sysLog.setMethod("findSyslogObject");
		sysLog.setParams("10");
		sysLog.setTime(999L);
		sysLog.setIp("101.101.101.888");
		sysLog.setCreatedTime(new Date());
		
		sysLogDao.insertObject(sysLog);
	}

}
