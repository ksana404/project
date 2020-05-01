package com.yg.pj.sys.service;

import com.yg.pj.sys.common.vo.PageObject;
import com.yg.pj.sys.entity.SysLog;

/*定义日志业务接口及方法，暴露外界对日志业务数据的访问*/

public interface SysLogService {
	   /**
     * 通过此方法实现分页查询操作
     * @param name 基于条件查询时的参数名
     * @param pageCurrent 当前的页码值
     * @return 当前页记录+分页信息
     */
	PageObject<SysLog> findPageObject(
			String name,
			Integer pageCurrent
			);

	
	/**处理日志删除请求的方法
	 * @param ids
	 * @return 变动行数
	 */
	int doDeleteObjects(Integer[] ids);
	
	/**日志持久化方法
	 * @param sysLog
	 * @return 
	 */
	void saveSysLog(SysLog sysLog);

}
