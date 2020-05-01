package com.sb.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sb.exception.ServiceException;
import com.sb.sys.dao.SysMenuDao;
import com.sb.sys.dao.SysRoleMenuDao;
import com.sb.sys.entity.SysMenu;
import com.sb.sys.entity.vo.Node;
import com.sb.sys.service.SysMenuService;
@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuDao sysMenuDao;
	
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	
	
	
	
	@Override
	public List<Map<String, Object>> findObjects() {
			//1.
		List<Map<String, Object>> list =sysMenuDao.findObjects();
		
		
		return list;
	}

	@Override
	public void deleteObject(Integer id) {
		//1.参数校验
		if(id==null || id<=0)
			throw new IllegalArgumentException("需要选择一个删除");
			//2.调用方法删除
			//2.1删除前  需要删除子菜单
			// 规划  查询子菜单数量  删除子菜单 
		// 2.1.1查询子菜单数据  
		 int count =sysMenuDao.getChildCount(id);
		//2.1.2 当子菜单数据大于0 的时候  抛出请先删除子菜单提示
		 if(count>0)
			 throw new IllegalArgumentException("请先删除子菜单!");
			//2.2删除前 需要删除菜单-角色 关系表
		 sysRoleMenuDao.deleteObjectBymenuId(id);
		
			//3.掉用删除菜单方法
		 int rows = sysMenuDao.deleteObject(id);
			//4.变动行数 为0 则抛出本方法已经不存在异常
		 if(rows==0)
			 throw new ServiceException("此菜单已经不存在！");
			//2.2删除前 需要删除菜单-角色 关系表
		 
			//5.返回数据 以便以后需要时使用  ？？？
		
	}

	@Override
	public List<Node> findZtreeMenuNodes() {
		
		List<Node> list =sysMenuDao.findZtreeMenuNodes();
		
		return list;
	}

	@Override
	public int updateObject(SysMenu entity) {
		//1.参数校验
		if(entity==null || StringUtils.isEmpty(entity.getName()))
			throw new  IllegalArgumentException("提交菜单不能为空！");
		//2.
		//修改时间改为现在
		entity.setModifiedTime(new Date());
		//修改人改为用户名  ???
		int rows=0;
		try {
			 rows = sysMenuDao.updateObject(entity);
			
		} catch (Exception e) {
			throw new ServiceException("数据保存失败！");
		}
		
		//3.
		if(rows==0)
			throw new IllegalArgumentException("该记录可能已经不存在！");
			
		//
		//
		//
		return rows;
	}

	@Override
	public int saveObject(SysMenu entity) {
		
		//1.参数校验
				if(entity==null || StringUtils.isEmpty(entity.getName()))
					throw new  IllegalArgumentException("提交菜单不能为空！");
				
				int rows=0;
				try {
					 rows = sysMenuDao.saveObject(entity);
					
				} catch (Exception e) {
					throw new ServiceException("数据添加失败！");
				}
				
				//3.
				if(rows==0)
					throw new IllegalArgumentException("添加不成功！");
					
				//
				//
				//
				return rows;
		
		
	}

}
