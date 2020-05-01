package com.yg.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleUserDao {

	// 删除角色-用户关系  直接根据角色ID删除
	int deleteObjectsByRoleId(Integer id);

	/**根据用户id 用户角色id(多个) 添加进 用户角色关系
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	int insert(Integer userId, Integer[] roleIds);

	/**基于用户id获取用户拥有的角色(sys_user_roles)
	 * @param id
	 * @return
	 */
	List<Integer> findRoleIdsByUserId(Integer id);


}
