package com.yg.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yg.pj.sys.common.vo.SysRoleMenuVo;
import com.yg.pj.sys.entity.SysRole;

@Mapper
public interface SysRoleDao {

	
	/**通过用户名 查询用户角色数量
	 * @param name
	 * @return
	 */
	int getRowCount(String name);

	/**Dao层 通过用户名  起始页  页面大小  获取角色列表信息 
	 * @param name
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<SysRole> findPageObjects(
			@Param("name") String name, 
			@Param("startIndex")  Integer startIndex, 
			@Param("pageSize")  Integer pageSize);

	/**删除角色信息 直接根据角色ID删除
	 * @param id
	 * @return
	 */
	int deleteObjects(Integer id);

	/**保存 角色信息sysRole   
	 * @param sysRole
	 * @return
	 */
	int insert(SysRole sysRole);

	/**通过角色id 查询角色信息
	 * @param id
	 * @return
	 */
	SysRoleMenuVo findObjectById(Integer id);

	/**修改角色信息
	 * @param sysRole
	 * @return
	 */
	int updateObject(SysRole sysRole);

	

	/**直接查询角色信息到新用户添加页面
	 * @return
	 */
	List<SysRole> finds();

}
