package com.sb.sys.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sb.sys.entity.SysLog;

@Mapper
public interface SysLogDao extends Serializable, BaseMapper<SysLog> {
	
	/*根据用户名查询用户日志记录总数*/
//	@Select("select count(*) from sys_logs")
	Integer doFindCount(String  username);
	
	 /**根据用户名字，当前页码数量，页面大小，查询页面中
		日志记录详细信息
	 * @param username
	 * @param indexPage 起始页
	 * @param endPage  结尾页
	 * @return  返回若干日志信息
	 */
	List<SysLog> doFindSysLogs(String username,Integer startIndex,Integer pageSize);

	//
	/**
	 * @param ids
	 * @return 删除的行数
	 * @Param("ids") Integer...ids 表示xxxMapper.xml中的方法对应  参数名必须保持一致
	 */
	int deleteObjects(@Param("ids") Integer...ids);
	
	//
	/** 日志保存方法
	 * @param sysLog
	 */
	void insertObject(SysLog sysLog);

}
