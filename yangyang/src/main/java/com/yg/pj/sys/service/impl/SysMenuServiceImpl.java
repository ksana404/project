package com.yg.pj.sys.service.impl;

import java.rmi.ServerException;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yg.pj.common.exception.ServiceException;
import com.yg.pj.sys.common.vo.Node;
import com.yg.pj.sys.dao.SysMenuDao;
import com.yg.pj.sys.dao.SysRoleMenuDao;
import com.yg.pj.sys.entity.SysMenu;
import com.yg.pj.sys.service.SysMenuService;


/*定义菜单业务接口实现类，并添加菜单业务数据对应的查询操作实现
 * */
@Service
public class SysMenuServiceImpl implements SysMenuService {

	
	@Autowired
	private SysMenuDao sysMenuDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	
	
	@Cacheable(value ="menuCache")
	@Override
	public List<Map<String, Object>> findObjects() throws ServerException {
		
		List<Map<String, Object>> listMap = sysMenuDao.findObjects();
		if(listMap ==null|| listMap.size()==0)
			throw new ServerException("没有对应的菜单数据");
		
		System.out.println("----------菜单管理 从查询数据 findObjects ");
		return listMap;
	}

/**@RequiresPermissions 
 * 描述的方法显示 访问该方法需要授权，没有授权标识则抛出异常，不能访问。
 * 没有这个注解标识，可以直接访问。
 * 
 * */
	@RequiresPermissions("sys:menu:delete")
//将改动的数据  从缓存中删除
	@CacheEvict(value="users", beforeInvocation=true)
	@Override
	public int deleteObject(Integer id) {
		//1.验证数据的合法性
		if(id==null || id<=0)
			throw new IllegalArgumentException("请先选择，所需要的删除项");
		//2.基于id进行子元素查询
		int childCount = sysMenuDao.getChildCount(id);
		if(childCount>0)
			throw new IllegalArgumentException("请先删除子 菜单");
		//3.删除角色-菜单 关系数据
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		
		//4..删除菜单数据
		int rows = sysMenuDao.deleteObject(id);
		if(rows==0)
			throw new IllegalArgumentException("该菜单可能已经不存在");
		
		//5.返回数据
		return rows;
	}



	@Cacheable(value ="menuNodes")
	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes();
		
	}


// 保存后清除所有缓存
	@CacheEvict(value="menuCache", allEntries = true)
	@Override
	public int saveObject(SysMenu entity) {
		//1.合法验证
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("菜单名不能为空");
			
		//2.保存数据
		int rows;
		
		try {
			 rows = sysMenuDao.insertObject(entity);
		} catch (Exception e) {
			throw new ServiceException("保存失败");
		}
		
		//
		return rows;
	}



	@CacheEvict(value="menuCache", allEntries = true)
	@Override
	public int updateObject(SysMenu entity) {

		//1.参数校验
				if(entity==null)
					throw new IllegalArgumentException("保存对象不能为空");
				if(StringUtils.isEmpty(entity.getName()))
					throw new IllegalArgumentException("菜单名不能为空");
				//2.保存数据
				int rows;
				
				try {
					 rows = sysMenuDao.updateObject(entity);
				} catch (Exception e) {
					throw new ServiceException("保存失败");
				}
				
				//
				return rows;
	}

}
