package com.sb.sys.service;

import com.sb.sys.entity.SysLog;
import com.sb.sys.entity.vo.PageObject;

/**定义日志业务接口及方法，暴露外界对日志业务数据的访问
 * */
public interface SysLogService {
	
	
	 /**根据用户名，当前页码值，页面大小查询页面信息
	 * @param username  用户名
	 * @param currentPage  当前页码值
	 * @param pageSize  页面大小
	 * @return  返回页面信息
	 */
	PageObject<SysLog>  findPageObjects(
			 String username,
			 Integer currentPage
			 );

	
	/**
	 * 在日志业务层定义用于执行删除业务的方法，首先通过方法参数接收控制层传递的多个记录的id，
	 * 并对参数id进行校验。然后基于日志记录id执行删除业务实现
	 * @param ids
	 * @return row 
	 */
	int deleteObjects(Integer...ids);

}
