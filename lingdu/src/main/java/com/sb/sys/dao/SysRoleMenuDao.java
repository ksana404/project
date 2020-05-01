package com.sb.sys.dao;

import org.apache.ibatis.annotations.Mapper;

/**这是菜单-角色的持久层接口
 * 
 * */
@Mapper
public interface SysRoleMenuDao {
	
	//根据菜单id 删除 菜单-角色 关系
	int deleteObjectBymenuId(Integer id);

}
