package com.yg.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yg.pj.sys.entity.SysLog;

/*DAO数据访问对象
 * 1）此对象的实现类由mybatis框架创建
 * 2）此类的实现类会自动注入SqlSessionFactory对象
 * 
 * 说明：
1)当DAO中方法参数多余一个时尽量使用@Param注解进行修饰并指定名字，
然后再Mapper文件中便可以通过类似#{username}方式进行获取，
否则只能通过#{arg0}，#{arg1}或者#{param1}，#{param2}等方式进行获取。

2)当DAO方法中的参数应用在动态SQL中时无论多少个参数，
尽量使用@Param注解进行修饰并定义。
 * 
 * */
@Mapper
public interface SysLogDao {
	
	/**基于条件统计用户行为日志 有多少行
	 * @param username
	 * @return 查询到的数据
	 */
	int getRowCount(@Param("username") String username);
	
	/**基于条件username,从指定位置startIndex，查询pageSize条数据
	 * @param username 用户名
	 * @param startIndex 当前起始页
	 * @param pageSize 页面大小
	 * @return List<SysLog> 返回查询的结果
	 */
	List<SysLog> findPageObjects(
			@Param("username")String username, //param1
			@Param("startIndex")Integer startIndex, //param2
			@Param("pageSize")Integer pageSize //param3
			);
	/*
	 * @Param("startIndex")Integer startIndex
	 * 若不添加@Param注解  Integer startIndex
	 * 那么startIndex 一旦改名成 Index  ==》 Integer Index  
	 * -----则在mapper.xml 将取不到对应的值。
	 * Integer startIndex 对应的值中名字是 k param1 param2 param3  v是 startIndex 。。。
	 * Param("startIndex")Integer 对应的kv  k是 startIndex   v是startIndex
	 * 
	 * 旧版本需要些！！！！！！！！！！！！！！！
	 * */

	/****处理日志删除请求的方法
	 * @param ids
	 * @return  变动的行数
	 */
	int doDeleteObjects(@Param("ids") Integer...ids);
	
	/**用于实现日志信息持久化的方法
	 * @param sysLog
	 * @return
	 */
	int insertOject(SysLog sysLog);
	
	
	
	
	
}
