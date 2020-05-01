package com.sb.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sb.sys.entity.SysMenu;
import com.sb.sys.entity.vo.Node;

/*本接口是菜单数据层操作接口
 * 用于菜单与数据库操作，供业务层调用
 * */
@Mapper
public interface SysMenuDao {

	/**用于查询数据库中的菜单数据到页面展示
	 * sys_menus +paranseName
	 * @return
	 */
	List<Map<String, Object>> findObjects();
//获取子菜单数量
	int getChildCount(Integer id);
	
	//根据菜单id 删除菜单信息
	int deleteObject(Integer id);
	
	
	/**查询获得树形菜单节点
	 * @return
	 */
	List<Node> findZtreeMenuNodes();
	
	//用户修改 菜单数据
	int updateObject(SysMenu entity);
	
	//用户添加菜单数据
	int saveObject(SysMenu entity);

}
