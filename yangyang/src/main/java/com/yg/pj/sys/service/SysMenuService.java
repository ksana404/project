package com.yg.pj.sys.service;

import java.rmi.ServerException;
import java.util.List;
import java.util.Map;

import com.yg.pj.sys.common.vo.Node;
import com.yg.pj.sys.entity.SysMenu;

public interface SysMenuService {
	
	 /**定义菜单业务接口及方法，暴露外界对菜单业务数据的访问
	 * @return
	 * @throws ServerException 
	 */
	List<Map<String,Object>> findObjects() throws ServerException;
	
	
	/**添加基于id进行菜单删除的方法
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	
	//添加查询菜单信息的方法
	List<Node> findZtreeMenuNodes();
	
	//添加用于保存菜单对象的方法
	int saveObject(SysMenu entity);


	/**根据菜单id修改，菜单信息
	 * @param entity
	 */
	int updateObject(SysMenu entity);

}
