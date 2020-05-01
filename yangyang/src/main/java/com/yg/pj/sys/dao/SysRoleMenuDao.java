package com.yg.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMenuDao {
	
	//
	/**基于菜单id 删除角色 菜单关系 数据()
	 * @param menuId
	 * @return
	 */
	int deleteObjectsByMenuId(Integer menuId);
// 
	/**删除角色-菜单关系   直接根据角色ID删除
	 * @param id
	 * @return
	 */
	int deleteObjectsByRoleId(Integer id);
	
	/**保存角色-菜单 关系数据 roleId - menuId
	 * @param id
	 * @param menuIds
	 */
	int insert(Integer roleId, Integer[] menuIds);
	
	/**通过角色id 查询角色-菜单关系信息 菜单id 
	 * @param id
	 * @return
	 */
	List<Integer> findMenuIdByRoleId(Integer id);
	
	/**根据角色id 修改角色-菜单 信息
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	int updateByRoleId(Integer roleId, List<Integer> menuIds);
	

	/**DAO接口 方法中 参数为数组 必须添加@Param 标识变量名字
	 * @Param("roleIds")Integer[] roleIds)
	 * 
	 * */
	List<Integer> findMenuIdsByRoleIds(@Param("roleIds")List<Integer> roleIds);
	
	

}
