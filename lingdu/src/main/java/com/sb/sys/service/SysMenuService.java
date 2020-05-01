package com.sb.sys.service;

import java.util.List;
import java.util.Map;

import com.sb.sys.entity.SysMenu;
import com.sb.sys.entity.vo.Node;

/**
 * 本接口用于 菜单业务层的继承  规划业务方法方法
 * 供控制层调用
 * */
public interface SysMenuService {

	/**页面请求菜单数据，用于展示到页面
	 * @return
	 */
	List<Map<String, Object>> findObjects();

	/**根据用户提交的菜单id 删除菜单信息
	 * 
	 * @param id
	 */
	void deleteObject(Integer id);

	
	/**查询获得树形菜单节点
	 * @return
	 */
	List<Node> findZtreeMenuNodes();

	
	/**用户修改 菜单数据
	 * @param entity
	 */
	int updateObject(SysMenu entity);

	/**保存新添加的菜单信息
	 * @param entity
	 * @return
	 */
	int  saveObject(SysMenu entity);

	
}
