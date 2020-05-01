package com.yg.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yg.pj.sys.common.anno.RequestLogP;
import com.yg.pj.sys.common.config.PageProperties;
import com.yg.pj.sys.common.vo.PageObject;
import com.yg.pj.sys.dao.SysLogDao;
import com.yg.pj.sys.entity.SysLog;
import com.yg.pj.sys.service.SysLogService;

/* com.yg.pj.sys.service.impl.SysLogServiceImpl
 * 日志业务接口及实现类，用于具体执行日志业务数据的分页查询操作*/
/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysLogServiceImpl implements SysLogService {
	
	@Autowired
	private SysLogDao sysLogDao;
	
	@Autowired
	private PageProperties pageProperties;
	
	//@Async
	@RequestLogP("findPageObject") //默认为value,value默认为""  空字符串 
	@Override
	public PageObject<SysLog> findPageObject(String name, Integer pageCurrent) {
		
		
		
		//1.验证参数合法性
		//1.1验证 pageCurrent的合法性
		//不合法抛出 IllegalArgumentException异常
		if(pageCurrent ==null || pageCurrent<1)
			throw new IllegalArgumentException("当前页码不正确");
		
		//2.基于条件查询总条数
		//2.1
		int rowCount = sysLogDao.getRowCount(name);
		//2.2验证结果
		if(rowCount ==0)
			throw new IllegalArgumentException("此用户不存在！！");
		//3.基于条件查询当前页记录( )pageSize定义为2
		//3.1定义pageSize 为4
		int pageSize =pageProperties.getPageSize();
		System.out.println("pageSize:"+pageSize);
		//3.2计算startIndex  2~5 4  展现页面信息
		int startIndex =(pageCurrent-1)*pageSize;
		//3.3执行当前数据查询操作
		List<SysLog> records = sysLogDao.findPageObjects(name, startIndex, pageSize);
		//4.对页面信息用 页面对象进行封装
		//4.1构建PageObject<T>对象
		PageObject<SysLog> page =new PageObject<SysLog>();
		//4.2封装数据
		page.setPageCurrent(pageCurrent);
		page.setPageSize(pageSize);
		page.setRecords(records);
		page.setPageCount((rowCount-1)/pageSize+1);
		//int pageNo = (totalNums + PAGESIZE - 1) / PAGESIZE;
		page.setRowCount(rowCount);
		
		//5.返回结果
		
		return page;
	}
	
	@RequestLogP("doDeleteObjects") //默认为value,value默认为""  空字符串 
	@Override
	public int doDeleteObjects(Integer[] ids) {
		//1.参数基本校验  有误则抛出参数异常
		if(ids==null ||ids.length==0 ) 	
			throw new IllegalArgumentException("请选择所要删除的项！！！求你啦！！！");
		
		//2.调用方法进行删除操作
		
		
		int row;
		try {
			//若该方法删除出现故障，则提醒硬件运维人员
			row = sysLogDao.doDeleteObjects(ids);
		} catch (Exception e) {
			//3.发送警报
			throw new IllegalArgumentException("无法删除，系统故障！");
		}
		
		//4.对结果进行验证
		if(row==0)
			throw new IllegalArgumentException("日志记录已经不存在");
		//5.返回结果
		return row;
		
		
	}
	
	//@RequestLog
	 //在需要异步执行的业务方法上，使用@Async方法进行异步声明。  //@Async
	@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW)  //为何要写这里  影响具体的方法
	@Override
	public void saveSysLog(SysLog sysLog) {
		
		
		sysLogDao.insertOject(sysLog);
		//String tName11 = Thread.currentThread().getName();
		//System.out.println(" ******** saveSysLog tName22:"+tName11);
		
	}

}












