package com.yg.pj.sys.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yg.pj.sys.dao.SysLogDao;
import com.yg.pj.sys.entity.SysLog;

/*SysLogDao接口测试类*/
@SpringBootTest
public class SysLogDaoTest {
	
	@Autowired
	private SysLogDao sysLogDao;
	
	/**基于条件统计用户行为日志 有多少行*/
	@Test
	public void getRowCoutTest() {
		String username ="admin";
		int rowCout = sysLogDao.getRowCount(username);
		System.out.println("rowCout:"+rowCout);
	}
	
	/**基于条件username,从指定位置startIndex，查询pageSize条数据*/
	@Test
	public void findPageObjectsTest() {
		String username ="admin";
		Integer startIndex =2;
		Integer pageSize =5;
		
		
		List<SysLog> findPageObjects = sysLogDao.findPageObjects(username, startIndex, pageSize);
		for(SysLog log:findPageObjects) {
			System.out.println("log；"+log);
		}
		
	}
	
	@Test
	public void insertOjectTest() {
		
		SysLog sysLog =new SysLog();
		
		sysLog.setUsername("ksana");
		sysLog.setOperation("click");
		sysLog.setMethod("findSyslogObject");
		sysLog.setParams("10");
		sysLog.setTime(999L);
		sysLog.setIp("101.101.101.888");
		sysLog.setCreatedTime(new Date());
		
		//Integer id =10033;
		//SysLog sysLog1 =new SysLog(id,"ksana","狂点","方法1","21",1111L,"104.105.108.965.235",new Date());
		int row = sysLogDao.insertOject(sysLog);
		System.out.println("row:"+row);
	}
}
