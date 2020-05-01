package com.yg.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yg.pj.sys.common.vo.Node;
import com.yg.pj.sys.entity.SysMenu;

@Mapper
public interface SysMenuDao {
	
	//List<Map<String,Object>> findObjects();
	List<Map<String,Object>> findObjects();
	
	//获取子菜单数量
	int getChildCount(Integer id);
	
	//基于菜单id删除菜单记录
	int deleteObject(Integer id);
	
	//用于查询上级菜单相关信息
	List<Node> findZtreeMenuNodes();
	
	//定义数据持久化方法：
	int insertObject(SysMenu entity);

	//根据菜单id修改，菜单信息
	int updateObject(SysMenu entity);

	/**通过菜单 id 查询菜单的授权信息
	 * @param menusIds
	 * @return
	 */
	List<String> findPermission(@Param("menusIds") List<Integer> menusIds);


}
