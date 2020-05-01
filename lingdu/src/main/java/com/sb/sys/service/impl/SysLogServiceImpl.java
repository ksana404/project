package com.sb.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.sys.anno.AnnoSysLog;
import com.sb.sys.config.PageProperties;
import com.sb.sys.dao.SysLogDao;
import com.sb.sys.entity.SysLog;
import com.sb.sys.entity.vo.PageObject;
import com.sb.sys.service.SysLogService;

import lombok.extern.slf4j.Slf4j;

/**定义日志业务接口实现类，
 * 完成业务逻辑的实现
 * */

@Slf4j
@Service
public class SysLogServiceImpl implements SysLogService{

	@Autowired
	private SysLogDao sysLogDao;
	
	@Autowired
	private PageProperties pageProperties;
	
	
	
	@AnnoSysLog
	@Override
	public PageObject<SysLog> findPageObjects(String username, Integer currentPage) {
		//1.参数校验 
		//1.1分析 用户名通过获取总数来校验  页面大小是从配置文件读取
		//1.2 currentPage从用户传输过来
		if(currentPage==null ||currentPage<1)
			throw new IllegalArgumentException("当前页码值不正确！！！");
		//1.3 用户名 username
		Integer countRow = sysLogDao.doFindCount(username);
		if(countRow==null ||countRow<1)
			throw new IllegalArgumentException("用户日志信息可能已经删除！！！");
		
		//1.4 pageSize 从配置文件读取  配置文件的类 写法类似thymeleaf  ThymeleafProperties
		Integer pageSize = pageProperties.getPageSize();
		log.info("----------  pageSize:"+pageSize);
		
		//2. 调用持久层获取数据 获取页面需要展示的日志数据
		Integer startIndex =(currentPage-1)*pageSize;
		List<SysLog> records = sysLogDao.doFindSysLogs(username, startIndex, pageSize);
		//3.把数据填充到页面对象中
		 PageObject<SysLog> pageObject =new  PageObject<SysLog>();
		 pageObject.setPageCurrent(currentPage);
		 pageObject.setPageSize(pageSize);
		 pageObject.setRowCount(countRow);
		 //总页面数  
		 Integer pageCount= (countRow-1)/pageSize +1;
		 pageObject.setPageCount(pageCount);
		 pageObject.setRecords(records);
		
		
		return pageObject;
	}




	@Override
	public int deleteObjects(Integer...ids) {
		
		//1.参数校验
		if(ids ==null ||ids.length <=0)
			throw new  IllegalArgumentException("至少选择一个");
		//2.
		int row =sysLogDao.deleteObjects(ids);
		
		//3.
		//
		
		//4.
		
		return row;
	}

}
